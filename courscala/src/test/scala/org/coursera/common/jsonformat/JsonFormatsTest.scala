package org.coursera.common.jsonformat

import org.coursera.common.collection.Enum
import org.coursera.common.collection.EnumSymbol
import org.coursera.common.stringkey.StringKey
import org.coursera.common.stringkey.StringKeyFormat
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit
import play.api.libs.json.Format
import play.api.libs.json.JsString
import play.api.libs.json.JsSuccess
import play.api.libs.json.Json

class JsonFormatsTest extends AssertionsForJUnit {

  import JsonFormatsTest._

  @Test
  def stringKey(): Unit = {
    val id = TestId(2, "test")
    val idString = StringKey(id).key
    assert(JsString(idString) === Json.toJson(id))
    assert(JsSuccess(id) === Json.fromJson[TestId](JsString(idString)))
    assert(JsString(s"invalid stuff $idString").validate[TestId].isError)
  }

  @Test
  def enums(): Unit = {
    assertResult(Color.Amber)(JsString("Amber").as[Color])

    assertResult(JsString("Green"))(Json.toJson(Color.Green))
  }

}

object JsonFormatsTest {

  case class TestId(part1: Int, part2: String)

  object TestId {
    implicit val stringKeyFormat: StringKeyFormat[TestId] =
      StringKeyFormat.caseClassFormat((apply _).tupled, unapply)
    implicit val format: Format[TestId] = JsonFormats.stringKeyFormat[TestId]
  }

  sealed trait Color extends EnumSymbol

  object Color extends Enum[Color] {
    case object Red extends Color
    case object Amber extends Color
    case object Green extends Color

    implicit val format: Format[Color] = JsonFormats.enumFormat(Color)
  }

}
