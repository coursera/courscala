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

import org.coursera.common.jsonformat.JsonFormats
import play.api.libs.json.Format

/**
 * Represents an arbitrary string-typed key.
 *
 * Classes should explicitly convert to this (rather than letting clients use `toString`
 * to generate string versions of keys) as a signal that the key format must be stable and
 * is not simply for debugging convenience.
 *
 * See [[StringKeyFormat]] for convenient conversion definitions.
 */
case class StringKey(key: String) {
  def asOpt[T: StringKeyFormat]: Option[T] = implicitly[StringKeyFormat[T]].reads(this)
}

object StringKey {

  implicit val format: Format[StringKey] = JsonFormats.caseClassFormat(apply, unapply)

  @deprecated(message = "This is just confusing.", since = "2017-04-05")
  def unapply(key: String): Some[StringKey] = Some(StringKey(key))

  @deprecated(message = "Use [[toStringKey]] instead.", since = "2017-04-05")
  def apply[T: StringKeyFormat](t: T): StringKey = implicitly[StringKeyFormat[T]].writes(t)
  def toStringKey[T: StringKeyFormat](t: T): StringKey = implicitly[StringKeyFormat[T]].writes(t)

  def stringify[T: StringKeyFormat](t: T): String = implicitly[StringKeyFormat[T]].writes(t).key

}
