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

package org.coursera.common.stringkey

import java.nio.ByteBuffer
import java.util.Base64
import java.util.UUID

import org.coursera.common.collection.Enum
import org.coursera.common.collection.EnumSymbol

import scala.annotation.implicitNotFound
import scala.util.Try

/**
 * Conversion from [[T]] to [[StringKey]] for use in datastores, URLs, etc. The format must
 * be stable. Changes must be backwards- and forwards-compatible so the string form of the key
 * can be stored in databases and passed on the wire by different client versions.
 *
 * These conversions must be invertible; that is, for `val t: T`, `reads(writes(t)) == Some(t)`.
 */
@implicitNotFound("Implicit `StringKeyFormat[${T}]` required. See `StringKeyFormat` for examples.")
trait StringKeyFormat[T] {

  /**
   * Attempt to parse `key` and create a `T`. Returns `None` on failure.
   */
  def reads(key: StringKey): Option[T]

  /**
   * Converts `t` to string form, wrapped in `StringKey`.
   */
  def writes(t: T): StringKey

}

object StringKeyFormat extends CommonStringKeyFormats {

  implicit val stringKeyStringKeyFormat: StringKeyFormat[StringKey] =
    delegateFormat[StringKey, StringKey](key => Some(key), identity _)

  def apply[T](from: StringKey => Option[T], to: T => StringKey): StringKeyFormat[T] = {
    new StringKeyFormat[T] {
      def reads(key: StringKey) = from(key)
      def writes(t: T) = to(t)
    }
  }

  /**
   * Format [[T]] by first converting it to [[U]].
   */
  def delegateFormat[T, U](
      from: U => Option[T],
      to: T => U)
      (implicit otherFormat: StringKeyFormat[U]): StringKeyFormat[T] = {
    StringKeyFormat[T](key => otherFormat.reads(key).flatMap(from), t => otherFormat.writes(to(t)))
  }

  /**
   * Conveniently construct a format for a `case class` type.
   *
   * For example:
   * {{{
   *   case class StorageKey(distributionKey: String, sortKey: String)
   *
   *   object StorageKey {
   *     implicit val stringKeyFormat: StringKeyFormat[StorageKey] =
   *       StringKeyFormat.caseClassFormat((apply _).tupled, unapply)
   *   }
   * }}}
   */
  def caseClassFormat[T, U](
      apply: U => T,
      unapply: T => Option[U])
      (implicit otherFormat: StringKeyFormat[U]): StringKeyFormat[T] = {
    delegateFormat[T, U](apply.andThen(Some.apply), unapply.andThen(_.get))
  }

  def enumerationFormat(enumeration: Enumeration): StringKeyFormat[enumeration.Value] = {
    StringKeyFormat[enumeration.Value](
      k => Try(enumeration.withName(k.key)).toOption,
      v => StringKey(v.toString))
  }

  def enumFormat[SymbolType <: EnumSymbol](enumeration: Enum[SymbolType]):
    StringKeyFormat[SymbolType] = {
    StringKeyFormat[SymbolType](
      k => Try(enumeration.withName(k.key)).toOption,
      v => StringKey(v.name))
  }

  /**
   * Conveniently construct a format for an empty class.
   *
   * This should NEVER be used for non-empty case classes.
   *
   * For example:
   * {{{
   *   case object Marker {
   *     implicit val stringKeyFormat: StringKeyFormat[Marker.type] =
   *       StringKeyFormat.emptyFormat("marker", Marker)
   *   }
   * }}}
   */
  def emptyFormat[T](key: String, canonical: => T): StringKeyFormat[T] = {
    StringKeyFormat(
      _.asOpt[String].filter(_ == key).map(_ => canonical),
      _ => StringKey(key))
  }

  /**
   * Serializes [[T]] with a prefix so its string form is easily identifiable.
   *
   * For example:
   * {{{
   *   case class AuthenticatedUserId(id: Int)
   *
   *   object AuthenticatedUserId {
   *     implicit val stringKeyFormat: StringKeyFormat[AuthenticatedUserId] = {
   *       StringKeyFormat.prefixFormat(
   *         "authenticatedUser",
   *         StringKeyFormat.caseClass((apply _).tupled, unapply))
   *     }
   *   }
   * }}}
   * This format will convert `AuthenticatedUserId(1)` to `"authenticatedUser~1"`.
   */
  def prefixFormat[T](prefix: String, originalFormat: StringKeyFormat[T]): StringKeyFormat[T] = {
    implicit val implicitOriginalFormat = originalFormat
    StringKeyFormat(
      stringKey => stringKey.asOpt[(String, T)] match {
        case Some((`prefix`, key)) => Some(key)
        case _ => None
      },
      key => StringKey((prefix, key)))
  }

}

sealed trait CommonStringKeyFormats extends DefaultTupleFormats {

  implicit val stringFormat: StringKeyFormat[String] = PrimitiveFormat(identity, identity)

  implicit val intFormat: StringKeyFormat[Int] = PrimitiveFormat(_.toInt)

  implicit val longFormat: StringKeyFormat[Long] = PrimitiveFormat(_.toLong)

  implicit val booleanFormat: StringKeyFormat[Boolean] = PrimitiveFormat(_.toBoolean)

  implicit val doubleFormat: StringKeyFormat[Double] = PrimitiveFormat(_.toDouble)

  implicit val floatFormat: StringKeyFormat[Float] = PrimitiveFormat(_.toFloat)

  implicit val shortFormat: StringKeyFormat[Short] = PrimitiveFormat(_.toShort)

  implicit val uuidFormat: StringKeyFormat[UUID] = UuidFormat

}

private case class PrimitiveFormat[T](from: String => T, to: T => String = (t: T) => t.toString)
  extends StringKeyFormat[T] {

  override def reads(key: StringKey): Option[T] = {
    Try(Some(from(key.key))).recover {
      case _: NumberFormatException | _: IllegalArgumentException => None
    }.get
  }

  override def writes(t: T): StringKey = StringKey(to(t))
}

private case object UuidFormat extends StringKeyFormat[UUID] {

  override def reads(key: StringKey): Option[UUID] = {
    Try {
      val bytes = Base64.getUrlDecoder.decode(key.key)
      require(bytes.length == 16, "Incorrect length")

      val byteBuffer = ByteBuffer.wrap(bytes)
      val msb = byteBuffer.getLong
      val lsb = byteBuffer.getLong

      new UUID(msb, lsb)
    }.toOption
  }

  override def writes(uuid: UUID): StringKey = {
    val bytes = ByteBuffer.allocate(16)
      .putLong(uuid.getMostSignificantBits)
      .putLong(uuid.getLeastSignificantBits)
      .array()
    StringKey(Base64.getUrlEncoder.withoutPadding().encodeToString(bytes))
  }

}
