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
