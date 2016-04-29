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

import play.api.libs.json.Format
import play.api.libs.json.JsError
import play.api.libs.json.OFormat
import play.api.libs.json.Reads

/**
 * Formats for `case class`s constructed with `Json.format` can fail with an exception if the
 * constructor checks a condition with [[require]]. These formats catch the exception and return
 * the appropriate [[JsError]].
 */
object RequiringFormats {

  def requiringReads[T](delegate: Reads[T]): Reads[T] = Reads { json =>
    try {
      delegate.reads(json)
    } catch {
      case e: IllegalArgumentException => JsError(e.getMessage)
    }
  }

  def requiringFormat[T](delegate: Format[T]): Format[T] = {
    Format(requiringReads(delegate), delegate)
  }

  def requiringOFormat[T](delegate: OFormat[T]): OFormat[T] = {
    OFormat(requiringReads(delegate), delegate)
  }

}
