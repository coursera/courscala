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
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Duration
import org.joda.time.Instant
import org.junit.Test
import org.scalatestplus.junit.AssertionsForJUnit
import play.api.libs.json.Format
import play.api.libs.json.JsError
import play.api.libs.json.JsNull
import play.api.libs.json.JsNumber
import play.api.libs.json.JsObject
import play.api.libs.json.JsString
import play.api.libs.json.JsSuccess
import play.api.libs.json.Json
import play.api.libs.json.OFormat
import play.api.libs.json.OWrites
import play.api.libs.json.Reads
import play.api.libs.json.__

class JsonFormatsTest extends AssertionsForJUnit {

  import JsonFormatsTest._

  @Test
  def stringKey(): Unit = {
    val id = TestId(2, "test")
    val idString = StringKey.stringify(id)
    assert(JsString(idString) === Json.toJson(id))
    assert(JsSuccess(id) === Json.fromJson[TestId](JsString(idString)))
    assert(JsString(s"invalid stuff $idString").validate[TestId].isError)
  }

  @Test
  def enums(): Unit = {
    assertResult(Color.Amber)(JsString("Amber").as[Color])

    assertResult(JsString("Green"))(Json.toJson[Color](Color.Green))
  }

  @Test
  def instant(): Unit = {
    import JsonFormats.Implicits.instantFormat
    val testInstant = new Instant(137)
    assertResult(JsNumber(137))(Json.toJson(testInstant))
    assertResult(Some(testInstant))(Json.parse("137").asOpt[Instant])
  }


  @Test
  def duration(): Unit = {
    import JsonFormats.Implicits.durationFormat
    val testDuration = Duration.millis(137L)
    assertResult(JsNumber(137))(Json.toJson(testDuration))
    assertResult(Some(testDuration))(Json.parse("137").asOpt[Duration])
  }


  @Test
  def emptyFormat(): Unit = {
    val fmt = JsonFormats.emptyFormat(42)
    assertResult(JsSuccess(42))(fmt.reads(JsNull))
    assertResult(Json.obj())(fmt.writes(42))
  }

  @Test
  def delegateFormat_roundtrip(): Unit = {
    val fmt = JsonFormats.delegateFormat[TestId, (Int, String)](
      { case (p1, p2) => TestId(p1, p2) },
      id => (id.part1, id.part2))
    val id = TestId(1, "test")
    assertResult(JsSuccess(id))(fmt.reads(fmt.writes(id)))
  }

  @Test
  def delegateOWrites(): Unit = {
    val innerWrites: OWrites[TestId] = Json.writes[TestId]
    case class Container(id: TestId)
    val writes = JsonFormats.delegateOWrites[Container, TestId](_.id)(innerWrites)
    assertResult(innerWrites.writes(TestId(1, "x")))(writes.writes(Container(TestId(1, "x"))))
  }

  @Test
  def delegateOFormat_roundtrip(): Unit = {
    val innerFmt: OFormat[TestId] = Json.format[TestId]
    case class Wrapper(inner: TestId)
    val fmt = JsonFormats.delegateOFormat[Wrapper, TestId](Wrapper.apply, _.inner)(innerFmt)
    val w = Wrapper(TestId(2, "y"))
    assertResult(JsSuccess(w))(fmt.reads(fmt.writes(w)))
  }

  @Test
  def caseClassOFormat_roundtrip(): Unit = {
    implicit val idFmt: OFormat[TestId] = Json.format[TestId]
    case class Outer(id: TestId)
    val fmt = JsonFormats.caseClassOFormat[Outer, TestId](Outer.apply, Outer.unapply)
    val o = Outer(TestId(3, "z"))
    assertResult(JsSuccess(o))(fmt.reads(fmt.writes(o)))
  }

  @Test
  def enumerationFormat_reads(): Unit = {
    val fmt = JsonFormats.enumerationFormat(Weekday)
    assertResult(JsSuccess(Weekday.Mon))(fmt.reads(JsString("Mon")))
    assert(fmt.reads(JsString("Invalid")).isError)
  }

  @Test
  def enumerationFormat_writes(): Unit = {
    val fmt = JsonFormats.enumerationFormat(Weekday)
    assertResult(JsString("Tue"))(fmt.writes(Weekday.Tue))
  }

  @Test
  def formatWithDefaults_missingFieldUsesDefault(): Unit = {
    val fmt = JsonFormats.formatWithDefaults(
      Json.format[Config],
      Json.obj("host" -> "localhost"))
    assertResult(JsSuccess(Config("localhost", 8080)))(fmt.reads(Json.obj("port" -> 8080)))
  }

  @Test
  def formatWithDefaults_explicitValueOverridesDefault(): Unit = {
    val fmt = JsonFormats.formatWithDefaults(
      Json.format[Config],
      Json.obj("host" -> "localhost"))
    assertResult(JsSuccess(Config("example.com", 8080)))(
      fmt.reads(Json.obj("host" -> "example.com", "port" -> 8080)))
  }

  @Test
  def formatWithDefaults_writes(): Unit = {
    val delegate = Json.format[Config]
    val fmt = JsonFormats.formatWithDefaults(delegate, Json.obj())
    assertResult(delegate.writes(Config("h", 1)))(fmt.writes(Config("h", 1)))
  }

  @Test
  def optionalReads_presentField(): Unit = {
    import JsonFormats.Implicits.optionalReads
    val reads = implicitly[Reads[Option[Int]]]
    assertResult(JsSuccess(Some(42)))(reads.reads(JsNumber(42)))
  }

  @Test
  def optionalReads_invalidField_returnsNone(): Unit = {
    import JsonFormats.Implicits.optionalReads
    val reads = implicitly[Reads[Option[Int]]]
    assertResult(JsSuccess(None))(reads.reads(JsString("not-an-int")))
  }

  @Test
  def withRootPath_stripsPath(): Unit = {
    import JsonFormats.Implicits.ReadsPathMethods
    val json = Json.obj("a" -> 1, "b" -> 2)
    val pruned = (__ \ "b").json.prune.withRootPath.reads(json)
    assertResult(JsSuccess(Json.obj("a" -> 1)))(pruned)
  }

  @Test
  def withRootPath_preservesFailure(): Unit = {
    // Exercises the `otherwise` branch in withRootPath — a failing Reads must pass through as-is.
    import JsonFormats.Implicits.ReadsPathMethods
    val failingReads: Reads[Int] = Reads(_ => JsError("always fails")).withRootPath
    assert(failingReads.reads(Json.obj("x" -> 1)).isError)
  }

  @Test
  def mapReads_invalidKeyFails(): Unit = {
    // Exercises the JsError branch in mapReads when a JSON key cannot be parsed as K.
    // TestId requires non-empty part2; "~" alone yields an empty second component → None from reads.
    import JsonFormats.Implicits.mapReads
    val reads = implicitly[Reads[Map[TestId, Int]]]
    // JSON keys are strings; "notAnId" won't parse as (Int, String) via TestId's StringKeyFormat
    val json = Json.obj("notAnId" -> 1)
    assert(reads.reads(json).isError)
  }

  @Test
  def mapFormat_roundtrip(): Unit = {
    import JsonFormats.Implicits.mapFormat
    val fmt = implicitly[play.api.libs.json.OFormat[Map[String, Int]]]
    val m = Map("a" -> 1, "b" -> 2)
    assertResult(JsSuccess(m))(fmt.reads(fmt.writes(m)))
  }

  @Test
  def enumFormat_reads_invalidValue_returnsError(): Unit = {
    // Exercises the JsError orElse branch in enumFormat.reads (Enum[T] variant) — the
    // `jsTry(enum.withName(name))` fails and orElse returns JsError.
    val fmt = JsonFormats.enumFormat(Color)
    assert(fmt.reads(JsString("Purple")).isError)
  }

  @Test
  def extract_matchingJson(): Unit = {
    val id = TestId(1, "hello")
    val json = Json.toJson(id)  // uses TestId.format = stringKeyFormat → JsString("1~hello")
    assertResult(Some(id))(TestId.Extract.unapply(json))
  }

  @Test
  def extract_nonMatchingJson(): Unit = {
    assertResult(None)(TestId.Extract.unapply(JsString("bad")))
  }

  @Test
  def dateTime(): Unit = {
    import JsonFormats.Implicits.dateTimeFormat
    val testDatetime = new DateTime(2010, 1, 1, 0, 0, 0, 0, DateTimeZone.UTC)
    assertResult(JsNumber(1262304000000L))(Json.toJson(testDatetime))
    assertResult(Some(testDatetime))(Json.parse("1262304000000").asOpt[DateTime].map(_.withZone(DateTimeZone.UTC)))
  }
}

object JsonFormatsTest {

  case class TestId(part1: Int, part2: String)

  object TestId {
    implicit val stringKeyFormat: StringKeyFormat[TestId] =
      StringKeyFormat.caseClassFormat((apply _).tupled, unapply)
    implicit val format: Format[TestId] = JsonFormats.stringKeyFormat[TestId]
    object Extract extends JsonFormats.Extract[TestId]
  }

  sealed trait Color extends EnumSymbol

  object Color extends Enum[Color] {
    case object Red extends Color
    case object Amber extends Color
    case object Green extends Color

    implicit val format: Format[Color] = JsonFormats.enumFormat(Color)
  }

  object Weekday extends Enumeration {
    val Mon, Tue, Wed = Value
  }

  case class Config(host: String, port: Int)
  implicit val configFormat: OFormat[Config] = Json.format[Config]

}
