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

import java.util.UUID

import org.coursera.common.collection.Enum
import org.coursera.common.collection.EnumSymbol
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Instant
import org.junit.Test
import org.scalatestplus.junit.AssertionsForJUnit

import scala.collection.immutable

class StringKeyFormatTest extends AssertionsForJUnit {

  import StringKeyFormatTest._

  @Test
  def testStringKeyRead(): Unit = {
    assert(Some(StringKey("test")) ===
      StringKeyFormat.stringKeyStringKeyFormat.reads(StringKey("test")))
  }

  @Test
  def testStringKeyWrite(): Unit = {
    assert(StringKey("test") === StringKeyFormat.stringKeyStringKeyFormat.writes(StringKey("test")))
  }

  @Test
  def testPrefixWrite(): Unit = {
    val format = StringKeyFormat.prefixFormat[String]("pre", implicitly[StringKeyFormat[String]])
    assert(format.writes("hello") === StringKey("pre~hello"))
  }

  @Test
  def testPrefixRead(): Unit = {
    val format = StringKeyFormat.prefixFormat[String]("pre", implicitly[StringKeyFormat[String]])
    assert(format.reads(StringKey("pre~hello")) === Some("hello"))
  }

  @Test
  def testPrefixReadFail(): Unit = {
    val format = StringKeyFormat.prefixFormat[String]("pre", implicitly[StringKeyFormat[String]])
    assert(format.reads(StringKey("badpre~hello")) === None)
  }

  @Test
  def testEmptyFormat(): Unit = {
    case class Empty()

    val format = StringKeyFormat.emptyFormat("empty", Empty())

    assert(format.reads(StringKey("empty")) === Some(Empty()))
    assert(format.reads(StringKey("empty~garbage")) === None)
    assert(format.reads(StringKey("")) === None)
    assert(format.writes(Empty()) === StringKey("empty"))
  }

  @Test
  def testTuple2ReadSimple(): Unit = {
    val key = StringKey(s"coursera${SEPARATOR}awesome")
    val parsed = format2.reads(key)

    assert(parsed === Some("coursera", "awesome"))
  }

  @Test
  def testTuple2ReadEscaped(): Unit = {
    val key = StringKey(s"coursera!${SEPARATOR}awesome${SEPARATOR}Really")
    val parsed = format2.reads(key)

    assert(parsed === Some(s"coursera${SEPARATOR}awesome", "Really"))
  }

  @Test
  def testTuple2WriteSimple(): Unit = {
    val data = ("abc", "123")
    val key = format2.writes(data)

    assert(key.key === s"abc${SEPARATOR}123")
  }

  @Test
  def testTuple2WriteEscaped(): Unit = {
    val data = ("abc", "123~456~789")
    val key = format2.writes(data)

    assert(key.key === s"abc${SEPARATOR}123!${SEPARATOR}456!${SEPARATOR}789")
  }

  @Test
  def testTuple3ReadSimple(): Unit = {
    val key = StringKey(s"coursera${SEPARATOR}really${SEPARATOR}awesome")
    val parsed = format3.reads(key)

    assert(parsed === Some("coursera", "really", "awesome"))
  }

  @Test
  def testTuple3WriteSimple(): Unit = {
    val data = ("abc", "123", "xyz")
    val key = format3.writes(data)

    assert(key.key === s"abc${SEPARATOR}123${SEPARATOR}xyz")
  }

  @Test
  def nestedTuples(): Unit = {
    val data = (("abd123", "asdf"), ("aDO_-", "asdf123"))
    val dataConvertedAndBack = format22.reads(StringKey.toStringKey(StringKey.stringify(data)))

    assert(dataConvertedAndBack.get === data)
  }

  @Test
  def separatorIsLiteralNotRegex(): Unit = {
    val format = StringKeyFormat.tupleWithSeparator(".").tuple2Format[String, String]
    val key = StringKey("coursera.awesome?")
    val parsed = format.reads(key)
    assert(parsed === Some("coursera", "awesome?"))


    val data = ("abc", "123")
    val outputkey = format.writes(data)
    assert(outputkey.key === "abc.123")
  }

  @Test
  def separatorIsLiteralNotRegexAndExcapedProperly(): Unit = {
    val format = StringKeyFormat.tupleWithSeparator(".").tuple2Format[String, String]

    val key = StringKey("coursera.awesome?!.bla")
    val parsed = format.reads(key)
    assert(parsed === Some("coursera", "awesome?.bla"))

    val data = ("abc", "1.23")
    val outputkey = format.writes(data)
    assert(outputkey.key === "abc.1!.23")
  }

  @Test
  def enum(): Unit = {
    val key = StringKey("Amber")
    val parsed = Color.stringKeyFormat.reads(key)
    assert(parsed === Some(Color.Amber))
  }

  @Test
  def testUuidFormat(): Unit = {
    val uuid1 = UUID.randomUUID()

    val stringKey = StringKey.toStringKey(uuid1)
    val decoded = stringKey.asOpt[UUID]

    assert(decoded.get === uuid1)
  }

  @Test
  def read_caseClassFormat_validReturns_Some(): Unit = {
    assertResult(Some(TestId("aBaC9"))) {
      new StringKey("aBaC9").asOpt[TestId]
    }
  }

  @Test
  def read_caseClassFormat_invalidReturns_None(): Unit = {
    assertResult(None) {
      new StringKey("").asOpt[TestId]
    }
  }

  @Test
  def write_caseClassFormat_writesId(): Unit = {

    assertResult("aBaC9") {
      StringKey.stringify(new TestId("aBaC9"))
    }
  }

  @Test
  def intFormat_roundtrip(): Unit = {
    assertResult(Some(42))(StringKey("42").asOpt[Int])
    assertResult(StringKey("42"))(StringKey.toStringKey(42))
    assertResult(None)(StringKey("notanint").asOpt[Int])
  }

  @Test
  def longFormat_roundtrip(): Unit = {
    assertResult(Some(123456789012345L))(StringKey("123456789012345").asOpt[Long])
    assertResult(StringKey("123456789012345"))(StringKey.toStringKey(123456789012345L))
    assertResult(None)(StringKey("notalong").asOpt[Long])
  }

  @Test
  def booleanFormat_roundtrip(): Unit = {
    assertResult(Some(true))(StringKey("true").asOpt[Boolean])
    assertResult(Some(false))(StringKey("false").asOpt[Boolean])
    assertResult(StringKey("true"))(StringKey.toStringKey(true))
    assertResult(None)(StringKey("yes").asOpt[Boolean])
  }

  @Test
  def doubleFormat_roundtrip(): Unit = {
    assertResult(Some(3.14))(StringKey("3.14").asOpt[Double])
    assertResult(StringKey("3.14"))(StringKey.toStringKey(3.14))
    assertResult(None)(StringKey("notadouble").asOpt[Double])
  }

  @Test
  def floatFormat_roundtrip(): Unit = {
    assertResult(Some(1.5f))(StringKey("1.5").asOpt[Float])
    assertResult(StringKey("1.5"))(StringKey.toStringKey(1.5f))
  }

  @Test
  def shortFormat_roundtrip(): Unit = {
    assertResult(Some(7.toShort))(StringKey("7").asOpt[Short])
    assertResult(StringKey("7"))(StringKey.toStringKey(7.toShort))
    assertResult(None)(StringKey("99999999").asOpt[Short])
  }

  @Test
  def dateTimeFormat_roundtrip(): Unit = {
    val millis = 1592217600000L
    val dt = new DateTime(millis)
    val key = StringKey.toStringKey(dt)
    assertResult(millis)(key.asOpt[DateTime].get.getMillis)
  }

  @Test
  def instantFormat_roundtrip(): Unit = {
    val instant = new Instant(1592222400000L)
    val key = StringKey.toStringKey(instant)
    assertResult(Some(instant))(key.asOpt[Instant])
  }

  @Test
  def seqFormat_roundtrip(): Unit = {
    val fmt = implicitly[StringKeyFormat[immutable.Seq[Int]]]
    assertResult(StringKey("1,2,3"))(fmt.writes(immutable.Seq(1, 2, 3)))
    assertResult(Some(immutable.Seq(1, 2, 3)))(fmt.reads(StringKey("1,2,3")))
  }

  @Test
  def seqFormat_empty(): Unit = {
    val fmt = implicitly[StringKeyFormat[immutable.Seq[Int]]]
    assertResult(Some(immutable.Seq.empty[Int]))(fmt.reads(StringKey("")))
    assertResult(StringKey(""))(fmt.writes(immutable.Seq.empty))
  }

  @Test
  def seqFormat_escapedSeparator(): Unit = {
    val fmt = implicitly[StringKeyFormat[immutable.Seq[String]]]
    val items = immutable.Seq("a,b", "c")
    val key = fmt.writes(items)
    assertResult(Some(items))(fmt.reads(key))
  }

  @Test
  def setFormat_roundtrip(): Unit = {
    val fmt = implicitly[StringKeyFormat[Set[Int]]]
    assertResult(Some(Set(1, 2, 3)))(fmt.reads(StringKey("1,2,3")))
  }

  @Test
  def setFormat_empty(): Unit = {
    val fmt = implicitly[StringKeyFormat[Set[Int]]]
    assertResult(Some(Set.empty[Int]))(fmt.reads(StringKey("")))
    assertResult(StringKey(""))(fmt.writes(Set.empty))
  }

  @Test
  def setFormat_writtenSorted(): Unit = {
    val fmt = implicitly[StringKeyFormat[Set[String]]]
    assertResult(StringKey("a,b,c"))(fmt.writes(Set("c", "a", "b")))
  }

  @Test
  def delegateFormat_roundtrip(): Unit = {
    val fmt = StringKeyFormat.delegateFormat[Int, String](
      s => scala.util.Try(s.toInt).toOption,
      _.toString)
    assertResult(Some(42))(fmt.reads(StringKey("42")))
    assertResult(StringKey("42"))(fmt.writes(42))
    assertResult(None)(fmt.reads(StringKey("bad")))
  }

  @Test
  def enumerationFormat_roundtrip(): Unit = {
    val fmt = StringKeyFormat.enumerationFormat(Weekday)
    assertResult(Some(Weekday.Mon))(fmt.reads(StringKey("Mon")))
    assertResult(StringKey("Tue"))(fmt.writes(Weekday.Tue))
    assertResult(None)(fmt.reads(StringKey("Invalid")))
  }

  @Test
  def typedEmptyStringFormat_roundtrip(): Unit = {
    val fmt = StringKeyFormat.typedEmptyStringFormat("marker", "canonical")
    assertResult(Some("canonical"))(fmt.reads(StringKey("marker")))
    assertResult(StringKey("marker"))(fmt.writes("canonical"))
    assertResult(None)(fmt.reads(StringKey("wrong")))
  }

  @Test
  def typedCaseFormat_roundtrip(): Unit = {
    val fmt = StringKeyFormat.typedCaseFormat[TestId, String]("testid", TestId.apply, TestId.unapply)
    val id = new TestId("abc")
    val key = fmt.writes(id)
    assertResult(Some(id))(fmt.reads(key))
    assertResult(None)(fmt.reads(StringKey("wrong~abc")))
  }

  @Test
  def unimplementedFormat_readsReturnsNone(): Unit = {
    val fmt = StringKeyFormat.unimplementedFormat[Int]
    assertResult(None)(fmt.reads(StringKey("anything")))
  }

  @Test
  def unimplementedFormat_writesThrows(): Unit = {
    val fmt = StringKeyFormat.unimplementedFormat[Int]
    intercept[UnsupportedOperationException](fmt.writes(1))
  }

  @Test
  def orFormat_combinesFormats(): Unit = {
    import StringKeyFormat.Implicits._
    // Only matches key "special" → "SPECIAL", returns None for anything else
    val specialFormat = StringKeyFormat.typedEmptyStringFormat[String]("special", "SPECIAL")
    val baseFormat = implicitly[StringKeyFormat[String]]
    // Pass uFormat explicitly to avoid ambient implicit ambiguity
    val combined = baseFormat.orFormat[String](specialFormat, scala.reflect.classTag[String])
    assertResult(Some("SPECIAL"))(combined.reads(StringKey("special")))
    assertResult(Some("other"))(combined.reads(StringKey("other")))
  }

  @Test
  def orFormat_writes_nonUSubtype_delegatesToBase(): Unit = {
    // Exercises the `case _ => baseFormat.writes(obj)` branch in OrFormat.writes.
    // Shape is a sealed trait; Circle is U <: Shape; Square is another subtype of Shape.
    // When writes is called with a Square (not a Circle), it must fall through to baseFormat.
    import StringKeyFormat.Implicits._
    import StringKeyFormatTest.ShapeFormats._

    val combined: StringKeyFormat[Shape] =
      squareFormat.orFormat[Circle](circleFormat, scala.reflect.classTag[Circle])

    // writes(Circle) → Circle matches `case u: Circle` → uFormat used
    assertResult(StringKey("circle:5"))(combined.writes(Circle(5)))

    // writes(Square) → Square is not a Circle → falls through to baseFormat.writes
    assertResult(StringKey("square:3"))(combined.writes(Square(3)))
  }

  @Test
  def seqFormat_elementParseFailure_returnsNone(): Unit = {
    // Exercises the `case "" => None` (empty-item) branch in SeqFormat.reads.
    val fmt = implicitly[StringKeyFormat[immutable.Seq[Int]]]
    // "1,,3" splits into ["1", "", "3"]; the empty string becomes None → whole result is None
    assertResult(None)(fmt.reads(StringKey("1,,3")))
  }

  @Test
  def seqFormat_invalidElement_returnsNone(): Unit = {
    // Exercises format.reads returning None for a non-empty but unparseable item.
    val fmt = implicitly[StringKeyFormat[immutable.Seq[Int]]]
    assertResult(None)(fmt.reads(StringKey("1,notanint,3")))
  }

  @Test
  def setFormat_invalidElement_returnsNone(): Unit = {
    // Exercises the failure branch in SetFormat.reads when an element cannot be parsed.
    val fmt = implicitly[StringKeyFormat[Set[Int]]]
    assertResult(None)(fmt.reads(StringKey("1,notanint,3")))
  }

  @Test
  def setFormat_emptyItem_returnsNone(): Unit = {
    // Exercises the `case "" => None` branch in SetFormat.reads.
    val fmt = implicitly[StringKeyFormat[Set[Int]]]
    assertResult(None)(fmt.reads(StringKey("1,,3")))
  }

  @Test
  @deprecated("tests deprecated StringKey.unapply to keep coverage", "2017-04-05")
  def stringKey_unapply_deprecated(): Unit = {
    // Exercises the deprecated StringKey.unapply extractor (0% coverage before).
    val Some(key) = StringKey.unapply("hello")
    assertResult(StringKey("hello"))(key)
  }

  @Test
  @deprecated("tests deprecated StringKey.apply[T] to keep coverage", "2017-04-05")
  def stringKey_apply_deprecated(): Unit = {
    // Exercises the deprecated StringKey.apply[T: StringKeyFormat](t: T) overload (0% coverage).
    val key = StringKey[Int](42)
    assertResult(StringKey("42"))(key)
  }

  @Test
  def uuidFormat_reads_wrongLengthBase64_returnsNone(): Unit = {
    // Exercises the `require(bytes.length == 16)` guard in UuidFormat.reads: a valid base64
    // string that decodes to fewer than 16 bytes must return None.
    import java.util.Base64
    val shortBytes  = Base64.getUrlEncoder.withoutPadding().encodeToString(Array[Byte](1, 2, 3))
    assertResult(None)(StringKey(shortBytes).asOpt[java.util.UUID])
  }

  @Test
  def enumFormat_writes_serialisesName(): Unit = {
    // Exercises the `v => StringKey(v.name)` writes lambda in StringKeyFormat.enumFormat.
    val fmt = StringKeyFormat.enumFormat(Color)
    assertResult(StringKey("Green"))(fmt.writes(Color.Green))
  }
}

object StringKeyFormatTest {

  val format2 = implicitly[StringKeyFormat[(String, String)]]
  val format3 = implicitly[StringKeyFormat[(String, String, String)]]
  val format22 = implicitly[StringKeyFormat[((String, String), (String, String))]]
  val SEPARATOR = "~"

  sealed trait Color extends EnumSymbol

  object Color extends Enum[Color] {
    case object Red extends Color
    case object Amber extends Color
    case object Green extends Color

    implicit val stringKeyFormat: StringKeyFormat[Color] = StringKeyFormat.enumFormat(Color)

  }

  case class TestId(value: String) {
    require(value.nonEmpty, "value must be non-empty")
  }

  object TestId {
    implicit val stringKeyFormat: StringKeyFormat[TestId] = StringKeyFormat.caseClassFormat(apply, unapply)
  }

  object Weekday extends Enumeration {
    val Mon, Tue, Wed = Value
  }

  // ─── Hierarchy used to test OrFormat.writes non-U branch ───────────────────

  object ShapeFormats {
    sealed trait Shape
    case class Circle(radius: Int) extends Shape
    case class Square(side: Int)   extends Shape

    val circleFormat: StringKeyFormat[Circle] = StringKeyFormat(
      sk => sk.key match {
        case s if s.startsWith("circle:") => scala.util.Try(s.drop(7).toInt).toOption.map(Circle.apply)
        case _ => None
      },
      c => StringKey(s"circle:${c.radius}")
    )

    val squareFormat: StringKeyFormat[Shape] = StringKeyFormat(
      sk => sk.key match {
        case s if s.startsWith("square:") => scala.util.Try(s.drop(7).toInt).toOption.map(Square.apply)
        case _ => None
      },
      {
        case Square(s) => StringKey(s"square:$s")
        case Circle(r) => StringKey(s"circle:$r")
      }
    )
  }

}
