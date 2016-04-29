package org.coursera.common.jsonformat

import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit
import play.api.libs.json.JsSuccess
import play.api.libs.json.Json
import play.api.libs.json.Reads

class CopyingFormatsTest extends AssertionsForJUnit {

  import CopyingFormatsTest._

  @Test
  def readNew(): Unit = {
    assertResult(JsSuccess(NewType("hi", "", 1))) {
      Json.fromJson[NewType](Json.obj("newField" -> "hi", "oldField" -> "", "unrelated" -> 1))
    }
  }

  @Test
  def readOld(): Unit = {
    assertResult(JsSuccess(NewType("", "", 1))) {
      Json.fromJson[NewType](Json.obj("oldField" -> "", "unrelated" -> 1))
    }
  }

}

object CopyingFormatsTest {

  case class NewType(newField: String, oldField: String, unrelated: Int)
  implicit val copyingReads: Reads[NewType] = CopyingFormats.copyingReads(
    Json.reads[NewType], "oldField" -> "newField")

}