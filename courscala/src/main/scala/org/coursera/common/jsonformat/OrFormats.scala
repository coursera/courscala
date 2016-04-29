package org.coursera.common.jsonformat

import play.api.libs.json.Format
import play.api.libs.json.JsError
import play.api.libs.json.OFormat
import play.api.libs.json.OWrites
import play.api.libs.json.Reads
import play.api.libs.json.Writes

import scala.reflect.ClassTag
import scala.reflect.classTag

object OrFormats {

  def unimplementedReads[T: ClassTag]: Reads[T] = {
    Reads(_ => JsError(s"Invoked `unimplementedReads` for ${classTag[T]}"))
  }

  def unimplementedWrites[T: ClassTag]: Writes[T] = Writes { _ =>
    throw new UnsupportedOperationException(s"Invoked `unimplementedOWrites for ${classTag[T]}")
  }

  def unimplementedOWrites[T: ClassTag]: OWrites[T] = OWrites { _ =>
    throw new UnsupportedOperationException(s"Invoked `unimplementedOWrites for ${classTag[T]}")
  }

  def unimplementedFormat[T: ClassTag]: Format[T] = Format(unimplementedReads, unimplementedWrites)

  def unimplementedOFormat[T: ClassTag]: OFormat[T] =
    OFormat(unimplementedReads[T], unimplementedOWrites[T])

  implicit class OrReads[A](reads: Reads[A]) {
    def orReads[B <: A: Reads]: Reads[A] = {
      import play.api.libs.functional.syntax._
      reads or implicitly[Reads[B]].map(b => b: A)
    }
  }

  implicit class OrWrites[A](writes: Writes[A]) {
    def orWrites[B <: A: Writes: ClassTag](implicit classTag: ClassTag[A]): Writes[A] = Writes {
      case b: B => implicitly[Writes[B]].writes(b)
      case a: A => writes.writes(a)
    }
  }

  implicit class OrOWrites[A](oWrites: OWrites[A]) {
    def orOWrites[B <: A: OWrites: ClassTag](implicit classTag: ClassTag[A]): OWrites[A] = OWrites {
      case b: B => implicitly[OWrites[B]].writes(b)
      case a: A => oWrites.writes(a)
    }
  }

  implicit class OrFormat[A](format: Format[A]) {
    def orFormat[B <: A: Format: ClassTag](implicit classTag: ClassTag[A]): Format[A] = {
      Format(format.orReads[B], format.orWrites[B])
    }
  }

  implicit class OrOFormat[A](oFormat: OFormat[A]) {
    def orOFormat[B <: A: OFormat: ClassTag](implicit classTag: ClassTag[A]): OFormat[A] = {
      OFormat(oFormat.orReads[B], oFormat.orOWrites[B])
    }
  }

}
