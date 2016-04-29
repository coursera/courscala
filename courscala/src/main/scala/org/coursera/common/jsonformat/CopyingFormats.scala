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
