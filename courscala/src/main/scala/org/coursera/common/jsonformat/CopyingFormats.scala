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
import play.api.libs.json.OFormat
import play.api.libs.json.Reads
import play.api.libs.json.__

/**
 * Formats for copying one [[play.api.libs.json.JsObject]] field to another field name during read.
 */
object CopyingFormats {

  /**
   * Copy a field from one path in a JSON object to another. This may be useful when renaming a
   * field so the new format can read the field at its new path.
   *
   * Note that if the new field already exists, it will not be overwritten.
   */
  def copyingReads[T](delegate: Reads[T], copy: (String, String)): Reads[T] = {
    val (from, to) = copy

    import JsonFormats.Implicits.ReadsPathMethods
    import play.api.libs.functional.syntax._

    val toOrFromReads = ((__ \ to).json.pick or (__ \ from).json.pick).withRootPath
    val copiedReads = (__ \ to).json.copyFrom(toOrFromReads).withRootPath
    val mergedReads = __.json.update(copiedReads)

    delegate.compose(mergedReads)
  }

  def copyingFormat[T](delegate: Format[T], copy: (String, String)): Format[T] =
    Format(copyingReads(delegate, copy), delegate)

  def copyingOFormat[T](delegate: OFormat[T], copy: (String, String)): OFormat[T] =
    OFormat(copyingReads(delegate, copy), delegate)

}
