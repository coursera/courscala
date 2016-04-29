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
