package org.coursera.common.jsonformat

import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit
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

}

object TypedFormatsTest {

  case class T1(a: Int)
  case class T2(b: Int)

  implicit val format1: OFormat[T1] = Json.format[T1]
  implicit val format2: OFormat[T2] = Json.format[T2]

}