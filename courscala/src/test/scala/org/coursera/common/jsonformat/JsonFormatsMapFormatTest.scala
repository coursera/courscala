package org.coursera.common.jsonformat

import org.coursera.common.stringkey.StringKey
import org.coursera.common.stringkey.StringKeyFormat
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit
import play.api.libs.json.Json

class JsonFormatsMapFormatTest extends AssertionsForJUnit {

  import JsonFormatsMapFormatTest._
  import JsonFormats.Implicits.mapFormat

  @Test
  def serialize(): Unit = {
    assert(jsMap === Json.toJson(map))
  }

  @Test
  def unserialize(): Unit = {
    assert(map === Json.fromJson[Map[K, Int]](jsMap).get)
  }

}

object JsonFormatsMapFormatTest {

  case class K(value: String)

  implicit val stringKeyFormat: StringKeyFormat[K] = StringKeyFormat(
    stringKey => Some(K(stringKey.key)), k => StringKey(k.value))

  val map = Map(K("a") -> 1, K("b") -> 2)

  val jsMap = Json.obj("a" -> 1, "b" -> 2)

}
