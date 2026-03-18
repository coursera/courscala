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
import play.api.libs.json._

class RequiringFormatsTest extends AssertionsForJUnit {

  import RequiringFormatsTest._

  @Test
  def requiringReads_success(): Unit = {
    val reads = RequiringFormats.requiringReads(positiveFormat)
    assertResult(JsSuccess(Positive(1)))(reads.reads(Json.obj("value" -> 1)))
  }

  @Test
  def requiringReads_illegalArgument_returnsJsError(): Unit = {
    val reads = RequiringFormats.requiringReads(positiveFormat)
    assert(reads.reads(Json.obj("value" -> -1)).isError)
  }

  @Test
  def requiringFormat_roundtrip(): Unit = {
    val fmt = RequiringFormats.requiringFormat(positiveFormat)
    assertResult(JsSuccess(Positive(5)))(fmt.reads(fmt.writes(Positive(5))))
  }

  @Test
  def requiringFormat_invalidReturnsError(): Unit = {
    val fmt = RequiringFormats.requiringFormat(positiveFormat)
    assert(fmt.reads(Json.obj("value" -> 0)).isError)
  }

  @Test
  def requiringOFormat_roundtrip(): Unit = {
    val fmt = RequiringFormats.requiringOFormat(positiveFormat)
    assertResult(JsSuccess(Positive(7)))(fmt.reads(fmt.writes(Positive(7))))
  }

  @Test
  def requiringOFormat_invalidReturnsError(): Unit = {
    val fmt = RequiringFormats.requiringOFormat(positiveFormat)
    assert(fmt.reads(Json.obj("value" -> 0)).isError)
  }
}

object RequiringFormatsTest {
  case class Positive(value: Int) {
    require(value > 0, "value must be positive")
  }

  implicit val positiveFormat: OFormat[Positive] = Json.format[Positive]
}
