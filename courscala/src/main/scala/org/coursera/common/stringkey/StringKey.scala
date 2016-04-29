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

  def unapply(key: String): Some[StringKey] = Some(StringKey(key))

  def apply[T: StringKeyFormat](t: T): StringKey = implicitly[StringKeyFormat[T]].writes(t)

}
