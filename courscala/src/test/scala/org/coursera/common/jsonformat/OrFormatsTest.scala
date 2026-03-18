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

class OrFormatsTest extends AssertionsForJUnit {

  import OrFormats._
  import OrFormatsTest._

  @Test
  def unimplementedReads_returnsJsError(): Unit = {
    val reads = OrFormats.unimplementedReads[String]
    assert(reads.reads(JsString("x")).isError)
  }

  @Test
  def unimplementedWrites_throwsUnsupported(): Unit = {
    val writes = OrFormats.unimplementedWrites[String]
    intercept[UnsupportedOperationException](writes.writes("x"))
  }

  @Test
  def unimplementedOWrites_throwsUnsupported(): Unit = {
    val writes = OrFormats.unimplementedOWrites[String]
    intercept[UnsupportedOperationException](writes.writes("x"))
  }

  @Test
  def unimplementedFormat_readsReturnsError(): Unit = {
    val fmt = OrFormats.unimplementedFormat[String]
    assert(fmt.reads(JsString("x")).isError)
  }

  @Test
  def unimplementedFormat_writesThrows(): Unit = {
    val fmt = OrFormats.unimplementedFormat[String]
    intercept[UnsupportedOperationException](fmt.writes("x"))
  }

  @Test
  def unimplementedOFormat_readsReturnsError(): Unit = {
    val fmt = OrFormats.unimplementedOFormat[String]
    assert(fmt.reads(JsString("x")).isError)
  }

  @Test
  def orReads_firstSucceeds(): Unit = {
    val numReads: Reads[Any] = Reads { case JsNumber(n) => JsSuccess(n: Any); case _ => JsError("not a number") }
    implicit val strReads: Reads[String] = Reads { case JsString(s) => JsSuccess(s); case _ => JsError("not a string") }
    val combined = numReads.orReads[String]
    assertResult(JsSuccess(BigDecimal(3): Any))(combined.reads(JsNumber(3)))
  }

  @Test
  def orReads_fallsBackToSecond(): Unit = {
    val numReads: Reads[Any] = Reads { case JsNumber(n) => JsSuccess(n: Any); case _ => JsError("not a number") }
    implicit val strReads: Reads[String] = Reads { case JsString(s) => JsSuccess(s); case _ => JsError("not a string") }
    val combined = numReads.orReads[String]
    assertResult(JsSuccess("hello": Any))(combined.reads(JsString("hello")))
  }

  @Test
  def orWrites_dispatchesBySubtype(): Unit = {
    implicit val numWrites: Writes[NumVal] = Writes(n => JsNumber(n.n))
    implicit val strWrites: Writes[StrVal] = Writes(s => JsString(s.s))
    val combined = OrFormats.unimplementedWrites[BaseVal].orWrites[NumVal].orWrites[StrVal]
    assertResult(JsNumber(42))(combined.writes(NumVal(42)))
    assertResult(JsString("hi"))(combined.writes(StrVal("hi")))
  }

  @Test
  def orOWrites_dispatchesBySubtype(): Unit = {
    implicit val numWrites: OWrites[NumVal] = OWrites(n => Json.obj("n" -> n.n))
    implicit val strWrites: OWrites[StrVal] = OWrites(s => Json.obj("s" -> s.s))
    val combined = OrFormats.unimplementedOWrites[BaseVal].orOWrites[NumVal].orOWrites[StrVal]
    assertResult(Json.obj("n" -> 42))(combined.writes(NumVal(42)))
    assertResult(Json.obj("s" -> "hi"))(combined.writes(StrVal("hi")))
  }

  @Test
  def orFormat_readsAndWrites(): Unit = {
    implicit val circleFormat: Format[Circle] = Json.format[Circle]
    implicit val squareFormat: Format[Square] = Json.format[Square]
    val combined = OrFormats.unimplementedFormat[Shape].orFormat[Circle].orFormat[Square]
    val circle: Shape = Circle(5)
    val square: Shape = Square(3)
    assertResult(JsSuccess(circle))(combined.reads(combined.writes(circle)))
    assertResult(JsSuccess(square))(combined.reads(combined.writes(square)))
  }

  @Test
  def orOFormat_readsAndWrites(): Unit = {
    implicit val circleFormat: OFormat[Circle] = Json.format[Circle]
    implicit val squareFormat: OFormat[Square] = Json.format[Square]
    val combined = OrFormats.unimplementedOFormat[Shape].orOFormat[Circle].orOFormat[Square]
    val circle: Shape = Circle(5)
    val square: Shape = Square(3)
    assertResult(JsSuccess(circle))(combined.reads(combined.writes(circle)))
    assertResult(JsSuccess(square))(combined.reads(combined.writes(square)))
  }
}

object OrFormatsTest {
  sealed trait BaseVal
  case class NumVal(n: Int) extends BaseVal
  case class StrVal(s: String) extends BaseVal

  sealed trait Shape
  case class Circle(radius: Int) extends Shape
  case class Square(side: Int) extends Shape
}
