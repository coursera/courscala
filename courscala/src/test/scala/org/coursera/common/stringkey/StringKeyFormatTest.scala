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
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit

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

}
