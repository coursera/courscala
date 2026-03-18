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

import org.junit.Test
import org.scalatestplus.junit.AssertionsForJUnit
import play.api.libs.json.JsSuccess
import play.api.libs.json.Json
import play.api.libs.json.OFormat
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

  @Test
  def copyingFormat_writes(): Unit = {
    val fmt = CopyingFormats.copyingFormat(Json.format[NewType], "oldField" -> "newField")
    val obj = NewType("hi", "old", 1)
    assertResult(Json.toJson(obj)(Json.writes[NewType]))(fmt.writes(obj))
  }

  @Test
  def copyingOFormat_roundtrip(): Unit = {
    val fmt: OFormat[NewType] = CopyingFormats.copyingOFormat(Json.format[NewType], "oldField" -> "newField")
    assertResult(JsSuccess(NewType("hi", "", 1))) {
      fmt.reads(Json.obj("newField" -> "hi", "oldField" -> "", "unrelated" -> 1))
    }
    assertResult(JsSuccess(NewType("", "", 1))) {
      fmt.reads(Json.obj("oldField" -> "", "unrelated" -> 1))
    }
  }

}

object CopyingFormatsTest {

  case class NewType(newField: String, oldField: String, unrelated: Int)
  implicit val copyingReads: Reads[NewType] = CopyingFormats.copyingReads(
    Json.reads[NewType], "oldField" -> "newField")

}
