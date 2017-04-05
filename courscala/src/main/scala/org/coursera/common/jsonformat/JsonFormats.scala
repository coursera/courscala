/*
 * Copyright 2016 Coursera Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.coursera.common.jsonformat

import org.coursera.common.collection.Enum
import org.coursera.common.collection.EnumSymbol
import org.coursera.common.stringkey.StringKey
import org.coursera.common.stringkey.StringKeyFormat
import org.joda.time.Duration
import org.joda.time.Instant
import play.api.libs.json.Format
import play.api.libs.json.JsError
import play.api.libs.json.JsObject
import play.api.libs.json.JsResult
import play.api.libs.json.JsString
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.OFormat
import play.api.libs.json.OWrites
import play.api.libs.json.Reads
import play.api.libs.json.Writes

import scala.util.Failure
import scala.util.Success
import scala.util.Try

object JsonFormats {

  def emptyFormat[T](t: => T): OFormat[T] = new OFormat[T] {
    def reads(json: JsValue) = JsSuccess(t)
    def writes(o: T) = Json.obj()
  }

  def delegateFormat[T, S](
      fromDelegate: S => T,
      toDelegate: T => S)
      (implicit serializedFormat: Format[S]): Format[T] = new Format[T] {
    def reads(json: JsValue) = Json.fromJson[S](json).map(fromDelegate)
    def writes(obj: T) = Json.toJson(toDelegate(obj))
  }

  def delegateOWrites[T, S](toDelegate: T => S)(implicit delegateWrites: OWrites[S]) = {
    new OWrites[T] {
      override def writes(obj: T): JsObject = delegateWrites.writes(toDelegate(obj))
    }
  }

  def delegateOFormat[T, S](
      fromDelegate: S => T,
      toDelegate: T => S)
      (implicit delegateFormat: OFormat[S]): OFormat[T] = new OFormat[T] {
    override def reads(json: JsValue) = Json.fromJson[S](json).map(fromDelegate)
    override def writes(obj: T) = delegateFormat.writes(toDelegate(obj))
  }

  /**
   * Conveniently construct a format for a `case class` type.
   *
   * For example:
   * {{{
   *   case class StorageValue(value: JsObject, version: String)
   *
   *   object StorageValue {
   *     implicit val jsonFormat: Format[StorageValue] =
   *       JsonFormats.caseClassFormat((apply _).tupled, unapply)
   *   }
   * }}}
   */
  def caseClassFormat[T, S](
      apply: S => T,
      unapply: T => Option[S])
      (implicit serializedFormat: Format[S]): Format[T] = {
    delegateFormat(apply, Function.unlift(unapply))
  }

  /**
   * Like [[caseClassFormat]] but for [[OFormat]].
   */
  def caseClassOFormat[T, S](
      apply: S => T,
      unapply: T => Option[S])
      (implicit serializedFormat: OFormat[S]): OFormat[T] = {
    delegateOFormat(apply, Function.unlift(unapply))
  }

  def stringKeyFormat[T](implicit format: StringKeyFormat[T]): Format[T] = {
    val reads = Reads { json =>
      json.validate[StringKey].flatMap { stringKey =>
        stringKey.asOpt[T].map(JsSuccess(_)).getOrElse(JsError(s"Bad key: $stringKey"))
      }
    }
    val writes = Writes { t: T => Json.toJson(format.writes(t)) }
    Format(reads, writes)
  }

  def enumerationFormat(enumeration: Enumeration) = new Format[enumeration.Value] {
    def reads(json: JsValue) = {
      val jsResult = for {
        name <- json.validate[String]
        value <- JsonFormats.jsTry(enumeration.withName(name))
      } yield value

      jsResult.orElse {
        JsError(s"Unrecognized enumeration ($enumeration) value: $json")
      }
    }

    def writes(value: enumeration.Value) = JsString(value.toString)
  }

  def enumFormat[T <: EnumSymbol](enum: Enum[T]): Format[T] = new Format[T] {

    def reads(json: JsValue) = {
      val jsResult = for {
        name <- json.validate[String]
        value <- jsTry(enum.withName(name))
      } yield value

      jsResult.orElse {
        JsError(s"Unrecognized enumeration ($enum) value: $json")
      }
    }

    def writes(value: T) = JsString(value.name)
  }

  /**
   * Returns a new format that delegates to `delegate` for JSON parsing, but adds default
   * values before calling `reads`. Unfortunately `delegate` must be passed in and not constructed
   * automatically due to Play JSON library macro limitations.
   */
  def formatWithDefaults[T](delegate: OFormat[T], readsDefaults: JsObject): OFormat[T] = {
    new OFormat[T] {
      override def reads(json: JsValue): JsResult[T] = {
        for {
          jsObject <- json.validate[JsObject]
          o <- delegate.reads(readsDefaults ++ jsObject)
        } yield {
          o
        }
      }

      override def writes(o: T): JsObject = delegate.writes(o)
    }
  }

  private[jsonformat] def jsTry[T](block: => T): JsResult[T] = {
    Try { block } match {
      case Success(t) => JsSuccess(t)
      case Failure(e) => JsError(e.toString)
    }
  }

  object Implicits {

    implicit val durationFormat: Format[Duration] = {
      delegateFormat[Duration, Long](Duration.millis, _.getMillis)
    }

    implicit val instantFormat: Format[Instant] = {
      delegateFormat[Instant, Long](
        millis => new Instant(millis),
        instant => instant.getMillis)
    }

    implicit def mapReads[K, V](implicit keyFormat: StringKeyFormat[K], valueReads: Reads[V]):
      Reads[Map[K, V]] = {

      Reads { json: JsValue =>
        Reads.mapReads[V].reads(json).flatMap { stringKeyedMap =>
          stringKeyedMap.foldRight(JsSuccess(Map.empty[K, V]): JsResult[Map[K, V]]) {
            case ((stringKey, value), result) =>
              result.flatMap { map =>
                StringKey(stringKey).asOpt[K].map { key =>
                  JsSuccess(map + (key -> value))
                }.getOrElse {
                  JsError(s"Could not parse key: $stringKey")
                }
              }
          }
        }
      }
    }

    implicit def mapWrites[K, V](implicit keyFormat: StringKeyFormat[K], valueWrites: Writes[V]):
      OWrites[Map[K, V]] = {

      OWrites { typeKeyedMap: Map[K, V] =>
        val jsMap = typeKeyedMap.map { case (typeKey, value) =>
          StringKey.toStringKey(typeKey).key -> Json.toJson(value)
        }
        JsObject(jsMap.toList)
      }
    }

    implicit def mapFormat[K, V](implicit keyFormat: StringKeyFormat[K], valueFormat: Format[V]):
      OFormat[Map[K, V]] = OFormat(mapReads[K, V], mapWrites[K, V])

    /**
     * Read something optionally, handling whether something is present or not.
     */
    implicit def optionalReads[T](implicit fmt: Reads[T]): Reads[Option[T]] = new Reads[Option[T]] {
      override def reads(json: JsValue): JsResult[Option[T]] = {
        fmt.reads(json).fold(e => JsSuccess(None), v => JsSuccess(Some(v)))
      }
    }

    implicit class ReadsPathMethods[T](reads: Reads[T]) {
      /**
       * "Repaths" the [[Reads]] so that successful results have root paths. Use this to transform
       * [[Reads]]es that output successes with paths in contexts where the successes make more
       * sense at the root.
       *
       * For example, the path pruning reads outputs a pruned json with the path that it has pruned,
       * and using `withRootPath` on it strips that path:
       *
       * {{{
       *   scala> val json = Json.parse("""{"a": 1, "b": 2}""")
       *   json: play.api.libs.json.JsValue = {"a":1,"b":2}
       *
       *   scala> (__ \ "b").json.prune.reads(json)
       *   res5: play.api.libs.json.JsResult[play.api.libs.json.JsObject] = JsSuccess({"a":1},/b)

       *   scala> (__ \ "b").json.prune.withRootPath.reads(json)
       *   res6: play.api.libs.json.JsResult[play.api.libs.json.JsObject] = JsSuccess({"a":1},)
       * }}}
       */
      def withRootPath: Reads[T] = Reads { json =>
        reads.reads(json) match {
          case JsSuccess(value, _) => JsSuccess(value)
          case otherwise: Any => otherwise
        }
      }
    }

  }

  /**
   * Lets you pattern match [[JsValue]]s to [[T]].
   *
   * For example,
   * {{{
   *   case class Foo(field: Int)
   *
   *   object Foo {
   *     implicit val format: OFormat[Foo] = Json.format[Foo]
   *     object Extract extends JsonFormats.Extract[Foo]
   *   }
   *
   *   val Foo.Extract(foo) = Json.parse("""{"field": 2}""")
   * }}}
   */
  trait Extract[T] {
    def unapply(jsValue: JsValue)(implicit tReads: Reads[T]): Option[T] = jsValue.asOpt[T]
  }

}
