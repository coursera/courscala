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
import play.api.libs.json.Json
import play.api.libs.json.OFormat

class TypedFormatsTest extends AssertionsForJUnit {

  import TypedFormatsTest._

  @Test
  def typedDefinitionReads(): Unit = {
    val plainJs = Json.obj("a" -> 1)
    val testJs = Json.obj(
      "typeName" -> "T1",
      "definition" -> plainJs)

    val reads = TypedFormats.typedDefinitionReads("T1", format1)

    assertResult(format1.reads(plainJs))(reads.reads(testJs))
  }

  @Test
  def typedDefinitionWrites(): Unit = {
    val writes = TypedFormats.typedDefinitionWrites("T1", format1)

    val expectedJs = Json.obj(
      "typeName" -> "T1",
      "definition" -> Json.obj("a" -> 1))

    assertResult(expectedJs)(writes.writes(T1(1)))
  }

  @Test
  def typedDefinitionFormat_roundtrip(): Unit = {
    // Exercises the combined OFormat entry point (typedDefinitionFormat) which was
    // previously at 0% coverage because tests only called reads/writes directly.
    val fmt = TypedFormats.typedDefinitionFormat("T1", format1)
    val original = T1(99)
    val json = fmt.writes(original)
    assertResult(Some(original))(fmt.reads(json).asOpt)
  }

  @Test
  def typedDefinitionFormat_wrongTypeName_returnsError(): Unit = {
    val fmt = TypedFormats.typedDefinitionFormat("T1", format1)
    val jsonForT2 = TypedFormats.typedDefinitionFormat("T2", format2).writes(T2(5))
    assert(fmt.reads(jsonForT2).isError)
  }

}

object TypedFormatsTest {

  case class T1(a: Int)
  case class T2(b: Int)

  implicit val format1: OFormat[T1] = Json.format[T1]
  implicit val format2: OFormat[T2] = Json.format[T2]

}
