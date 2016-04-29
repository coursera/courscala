package org.coursera.common.stringkey

import java.util.regex.Pattern

/**
 * [[StringKeyFormat]]s for tuple types with a fixed element separator.
 */
trait DefaultTupleFormats extends TupleFormats {

  protected[this] override val tupleFormatSeparator = "~"

  def tupleWithSeparator(s: String): TupleFormats = new TupleFormats {
    protected[this] override val tupleFormatSeparator: String = s
  }

}

/**
 * [[StringKeyFormat]]s for tuple types with a configuration element separator.
 */
trait TupleFormats {

  protected[this] val tupleFormatSeparator: String

  // GENERATED CODE BELOW

  implicit def tuple2Format[T1, T2](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2]):
    StringKeyFormat[Tuple2[T1, T2]] = {

    new TupleFormats.Tuple2Format[T1, T2](tupleFormatSeparator)
  }

  implicit def tuple3Format[T1, T2, T3](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3]):
    StringKeyFormat[Tuple3[T1, T2, T3]] = {

    new TupleFormats.Tuple3Format[T1, T2, T3](tupleFormatSeparator)
  }

  implicit def tuple4Format[T1, T2, T3, T4](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4]):
    StringKeyFormat[Tuple4[T1, T2, T3, T4]] = {

    new TupleFormats.Tuple4Format[T1, T2, T3, T4](tupleFormatSeparator)
  }

  implicit def tuple5Format[T1, T2, T3, T4, T5](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5]):
    StringKeyFormat[Tuple5[T1, T2, T3, T4, T5]] = {

    new TupleFormats.Tuple5Format[T1, T2, T3, T4, T5](tupleFormatSeparator)
  }

  implicit def tuple6Format[T1, T2, T3, T4, T5, T6](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6]):
    StringKeyFormat[Tuple6[T1, T2, T3, T4, T5, T6]] = {

    new TupleFormats.Tuple6Format[T1, T2, T3, T4, T5, T6](tupleFormatSeparator)
  }

  implicit def tuple7Format[T1, T2, T3, T4, T5, T6, T7](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7]):
    StringKeyFormat[Tuple7[T1, T2, T3, T4, T5, T6, T7]] = {

    new TupleFormats.Tuple7Format[T1, T2, T3, T4, T5, T6, T7](tupleFormatSeparator)
  }

  implicit def tuple8Format[T1, T2, T3, T4, T5, T6, T7, T8](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8]):
    StringKeyFormat[Tuple8[T1, T2, T3, T4, T5, T6, T7, T8]] = {

    new TupleFormats.Tuple8Format[T1, T2, T3, T4, T5, T6, T7, T8](tupleFormatSeparator)
  }

  implicit def tuple9Format[T1, T2, T3, T4, T5, T6, T7, T8, T9](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9]):
    StringKeyFormat[Tuple9[T1, T2, T3, T4, T5, T6, T7, T8, T9]] = {

    new TupleFormats.Tuple9Format[T1, T2, T3, T4, T5, T6, T7, T8, T9](tupleFormatSeparator)
  }

  implicit def tuple10Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10]):
    StringKeyFormat[Tuple10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]] = {

    new TupleFormats.Tuple10Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](tupleFormatSeparator)
  }

  implicit def tuple11Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11]):
    StringKeyFormat[Tuple11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]] = {

    new TupleFormats.Tuple11Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](tupleFormatSeparator)
  }

  implicit def tuple12Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12]):
    StringKeyFormat[Tuple12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]] = {

    new TupleFormats.Tuple12Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](tupleFormatSeparator)
  }

  implicit def tuple13Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13]):
    StringKeyFormat[Tuple13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]] = {

    new TupleFormats.Tuple13Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](tupleFormatSeparator)
  }

  implicit def tuple14Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14]):
    StringKeyFormat[Tuple14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]] = {

    new TupleFormats.Tuple14Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](tupleFormatSeparator)
  }

  implicit def tuple15Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15]):
    StringKeyFormat[Tuple15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]] = {

    new TupleFormats.Tuple15Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](tupleFormatSeparator)
  }

  implicit def tuple16Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16]):
    StringKeyFormat[Tuple16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]] = {

    new TupleFormats.Tuple16Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](tupleFormatSeparator)
  }

  implicit def tuple17Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17]):
    StringKeyFormat[Tuple17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]] = {

    new TupleFormats.Tuple17Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](tupleFormatSeparator)
  }

  implicit def tuple18Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17],
      format18: StringKeyFormat[T18]):
    StringKeyFormat[Tuple18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]] = {

    new TupleFormats.Tuple18Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](tupleFormatSeparator)
  }

  implicit def tuple19Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17],
      format18: StringKeyFormat[T18],
      format19: StringKeyFormat[T19]):
    StringKeyFormat[Tuple19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]] = {

    new TupleFormats.Tuple19Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](tupleFormatSeparator)
  }

  implicit def tuple20Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17],
      format18: StringKeyFormat[T18],
      format19: StringKeyFormat[T19],
      format20: StringKeyFormat[T20]):
    StringKeyFormat[Tuple20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]] = {

    new TupleFormats.Tuple20Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](tupleFormatSeparator)
  }

  implicit def tuple21Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17],
      format18: StringKeyFormat[T18],
      format19: StringKeyFormat[T19],
      format20: StringKeyFormat[T20],
      format21: StringKeyFormat[T21]):
    StringKeyFormat[Tuple21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]] = {

    new TupleFormats.Tuple21Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](tupleFormatSeparator)
  }

  implicit def tuple22Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](
      implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17],
      format18: StringKeyFormat[T18],
      format19: StringKeyFormat[T19],
      format20: StringKeyFormat[T20],
      format21: StringKeyFormat[T21],
      format22: StringKeyFormat[T22]):
    StringKeyFormat[Tuple22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22]] = {

    new TupleFormats.Tuple22Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](tupleFormatSeparator)
  }

}

object TupleFormats {

  // GENERATED CODE BELOW

  class Tuple2Format[T1, T2](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2])
    extends StringKeyFormat[Tuple2[T1, T2]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple2[T1, T2]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
          } yield {
            Tuple2(
              parsed1,
              parsed2)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple2[T1, T2]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key)).mkString(separator))
    }

  }

  class Tuple3Format[T1, T2, T3](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3])
    extends StringKeyFormat[Tuple3[T1, T2, T3]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple3[T1, T2, T3]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
          } yield {
            Tuple3(
              parsed1,
              parsed2,
              parsed3)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple3[T1, T2, T3]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key)).mkString(separator))
    }

  }

  class Tuple4Format[T1, T2, T3, T4](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4])
    extends StringKeyFormat[Tuple4[T1, T2, T3, T4]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple4[T1, T2, T3, T4]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
          } yield {
            Tuple4(
              parsed1,
              parsed2,
              parsed3,
              parsed4)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple4[T1, T2, T3, T4]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key)).mkString(separator))
    }

  }

  class Tuple5Format[T1, T2, T3, T4, T5](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5])
    extends StringKeyFormat[Tuple5[T1, T2, T3, T4, T5]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple5[T1, T2, T3, T4, T5]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
          } yield {
            Tuple5(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple5[T1, T2, T3, T4, T5]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key)).mkString(separator))
    }

  }

  class Tuple6Format[T1, T2, T3, T4, T5, T6](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6])
    extends StringKeyFormat[Tuple6[T1, T2, T3, T4, T5, T6]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple6[T1, T2, T3, T4, T5, T6]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
          } yield {
            Tuple6(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple6[T1, T2, T3, T4, T5, T6]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key)).mkString(separator))
    }

  }

  class Tuple7Format[T1, T2, T3, T4, T5, T6, T7](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7])
    extends StringKeyFormat[Tuple7[T1, T2, T3, T4, T5, T6, T7]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple7[T1, T2, T3, T4, T5, T6, T7]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
          } yield {
            Tuple7(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple7[T1, T2, T3, T4, T5, T6, T7]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key)).mkString(separator))
    }

  }

  class Tuple8Format[T1, T2, T3, T4, T5, T6, T7, T8](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8])
    extends StringKeyFormat[Tuple8[T1, T2, T3, T4, T5, T6, T7, T8]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple8[T1, T2, T3, T4, T5, T6, T7, T8]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
          } yield {
            Tuple8(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple8[T1, T2, T3, T4, T5, T6, T7, T8]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key)).mkString(separator))
    }

  }

  class Tuple9Format[T1, T2, T3, T4, T5, T6, T7, T8, T9](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9])
    extends StringKeyFormat[Tuple9[T1, T2, T3, T4, T5, T6, T7, T8, T9]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple9[T1, T2, T3, T4, T5, T6, T7, T8, T9]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
          } yield {
            Tuple9(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key)).mkString(separator))
    }

  }

  class Tuple10Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10])
    extends StringKeyFormat[Tuple10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
          } yield {
            Tuple10(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key)).mkString(separator))
    }

  }

  class Tuple11Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11])
    extends StringKeyFormat[Tuple11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
          } yield {
            Tuple11(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key)).mkString(separator))
    }

  }

  class Tuple12Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12])
    extends StringKeyFormat[Tuple12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
            parsed12 <- format12.reads(StringKey(unescapeSeparator(part12)))
          } yield {
            Tuple12(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11,
              parsed12)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key),
        escapeSeparator(format12.writes(t._12).key)).mkString(separator))
    }

  }

  class Tuple13Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13])
    extends StringKeyFormat[Tuple13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12, part13) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
            parsed12 <- format12.reads(StringKey(unescapeSeparator(part12)))
            parsed13 <- format13.reads(StringKey(unescapeSeparator(part13)))
          } yield {
            Tuple13(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11,
              parsed12,
              parsed13)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key),
        escapeSeparator(format12.writes(t._12).key),
        escapeSeparator(format13.writes(t._13).key)).mkString(separator))
    }

  }

  class Tuple14Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14])
    extends StringKeyFormat[Tuple14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12, part13, part14) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
            parsed12 <- format12.reads(StringKey(unescapeSeparator(part12)))
            parsed13 <- format13.reads(StringKey(unescapeSeparator(part13)))
            parsed14 <- format14.reads(StringKey(unescapeSeparator(part14)))
          } yield {
            Tuple14(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11,
              parsed12,
              parsed13,
              parsed14)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key),
        escapeSeparator(format12.writes(t._12).key),
        escapeSeparator(format13.writes(t._13).key),
        escapeSeparator(format14.writes(t._14).key)).mkString(separator))
    }

  }

  class Tuple15Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15])
    extends StringKeyFormat[Tuple15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12, part13, part14, part15) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
            parsed12 <- format12.reads(StringKey(unescapeSeparator(part12)))
            parsed13 <- format13.reads(StringKey(unescapeSeparator(part13)))
            parsed14 <- format14.reads(StringKey(unescapeSeparator(part14)))
            parsed15 <- format15.reads(StringKey(unescapeSeparator(part15)))
          } yield {
            Tuple15(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11,
              parsed12,
              parsed13,
              parsed14,
              parsed15)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key),
        escapeSeparator(format12.writes(t._12).key),
        escapeSeparator(format13.writes(t._13).key),
        escapeSeparator(format14.writes(t._14).key),
        escapeSeparator(format15.writes(t._15).key)).mkString(separator))
    }

  }

  class Tuple16Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16])
    extends StringKeyFormat[Tuple16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12, part13, part14, part15, part16) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
            parsed12 <- format12.reads(StringKey(unescapeSeparator(part12)))
            parsed13 <- format13.reads(StringKey(unescapeSeparator(part13)))
            parsed14 <- format14.reads(StringKey(unescapeSeparator(part14)))
            parsed15 <- format15.reads(StringKey(unescapeSeparator(part15)))
            parsed16 <- format16.reads(StringKey(unescapeSeparator(part16)))
          } yield {
            Tuple16(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11,
              parsed12,
              parsed13,
              parsed14,
              parsed15,
              parsed16)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key),
        escapeSeparator(format12.writes(t._12).key),
        escapeSeparator(format13.writes(t._13).key),
        escapeSeparator(format14.writes(t._14).key),
        escapeSeparator(format15.writes(t._15).key),
        escapeSeparator(format16.writes(t._16).key)).mkString(separator))
    }

  }

  class Tuple17Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17])
    extends StringKeyFormat[Tuple17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12, part13, part14, part15, part16, part17) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
            parsed12 <- format12.reads(StringKey(unescapeSeparator(part12)))
            parsed13 <- format13.reads(StringKey(unescapeSeparator(part13)))
            parsed14 <- format14.reads(StringKey(unescapeSeparator(part14)))
            parsed15 <- format15.reads(StringKey(unescapeSeparator(part15)))
            parsed16 <- format16.reads(StringKey(unescapeSeparator(part16)))
            parsed17 <- format17.reads(StringKey(unescapeSeparator(part17)))
          } yield {
            Tuple17(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11,
              parsed12,
              parsed13,
              parsed14,
              parsed15,
              parsed16,
              parsed17)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key),
        escapeSeparator(format12.writes(t._12).key),
        escapeSeparator(format13.writes(t._13).key),
        escapeSeparator(format14.writes(t._14).key),
        escapeSeparator(format15.writes(t._15).key),
        escapeSeparator(format16.writes(t._16).key),
        escapeSeparator(format17.writes(t._17).key)).mkString(separator))
    }

  }

  class Tuple18Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17],
      format18: StringKeyFormat[T18])
    extends StringKeyFormat[Tuple18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12, part13, part14, part15, part16, part17, part18) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
            parsed12 <- format12.reads(StringKey(unescapeSeparator(part12)))
            parsed13 <- format13.reads(StringKey(unescapeSeparator(part13)))
            parsed14 <- format14.reads(StringKey(unescapeSeparator(part14)))
            parsed15 <- format15.reads(StringKey(unescapeSeparator(part15)))
            parsed16 <- format16.reads(StringKey(unescapeSeparator(part16)))
            parsed17 <- format17.reads(StringKey(unescapeSeparator(part17)))
            parsed18 <- format18.reads(StringKey(unescapeSeparator(part18)))
          } yield {
            Tuple18(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11,
              parsed12,
              parsed13,
              parsed14,
              parsed15,
              parsed16,
              parsed17,
              parsed18)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key),
        escapeSeparator(format12.writes(t._12).key),
        escapeSeparator(format13.writes(t._13).key),
        escapeSeparator(format14.writes(t._14).key),
        escapeSeparator(format15.writes(t._15).key),
        escapeSeparator(format16.writes(t._16).key),
        escapeSeparator(format17.writes(t._17).key),
        escapeSeparator(format18.writes(t._18).key)).mkString(separator))
    }

  }

  class Tuple19Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17],
      format18: StringKeyFormat[T18],
      format19: StringKeyFormat[T19])
    extends StringKeyFormat[Tuple19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12, part13, part14, part15, part16, part17, part18, part19) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
            parsed12 <- format12.reads(StringKey(unescapeSeparator(part12)))
            parsed13 <- format13.reads(StringKey(unescapeSeparator(part13)))
            parsed14 <- format14.reads(StringKey(unescapeSeparator(part14)))
            parsed15 <- format15.reads(StringKey(unescapeSeparator(part15)))
            parsed16 <- format16.reads(StringKey(unescapeSeparator(part16)))
            parsed17 <- format17.reads(StringKey(unescapeSeparator(part17)))
            parsed18 <- format18.reads(StringKey(unescapeSeparator(part18)))
            parsed19 <- format19.reads(StringKey(unescapeSeparator(part19)))
          } yield {
            Tuple19(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11,
              parsed12,
              parsed13,
              parsed14,
              parsed15,
              parsed16,
              parsed17,
              parsed18,
              parsed19)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key),
        escapeSeparator(format12.writes(t._12).key),
        escapeSeparator(format13.writes(t._13).key),
        escapeSeparator(format14.writes(t._14).key),
        escapeSeparator(format15.writes(t._15).key),
        escapeSeparator(format16.writes(t._16).key),
        escapeSeparator(format17.writes(t._17).key),
        escapeSeparator(format18.writes(t._18).key),
        escapeSeparator(format19.writes(t._19).key)).mkString(separator))
    }

  }

  class Tuple20Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17],
      format18: StringKeyFormat[T18],
      format19: StringKeyFormat[T19],
      format20: StringKeyFormat[T20])
    extends StringKeyFormat[Tuple20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12, part13, part14, part15, part16, part17, part18, part19, part20) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
            parsed12 <- format12.reads(StringKey(unescapeSeparator(part12)))
            parsed13 <- format13.reads(StringKey(unescapeSeparator(part13)))
            parsed14 <- format14.reads(StringKey(unescapeSeparator(part14)))
            parsed15 <- format15.reads(StringKey(unescapeSeparator(part15)))
            parsed16 <- format16.reads(StringKey(unescapeSeparator(part16)))
            parsed17 <- format17.reads(StringKey(unescapeSeparator(part17)))
            parsed18 <- format18.reads(StringKey(unescapeSeparator(part18)))
            parsed19 <- format19.reads(StringKey(unescapeSeparator(part19)))
            parsed20 <- format20.reads(StringKey(unescapeSeparator(part20)))
          } yield {
            Tuple20(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11,
              parsed12,
              parsed13,
              parsed14,
              parsed15,
              parsed16,
              parsed17,
              parsed18,
              parsed19,
              parsed20)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key),
        escapeSeparator(format12.writes(t._12).key),
        escapeSeparator(format13.writes(t._13).key),
        escapeSeparator(format14.writes(t._14).key),
        escapeSeparator(format15.writes(t._15).key),
        escapeSeparator(format16.writes(t._16).key),
        escapeSeparator(format17.writes(t._17).key),
        escapeSeparator(format18.writes(t._18).key),
        escapeSeparator(format19.writes(t._19).key),
        escapeSeparator(format20.writes(t._20).key)).mkString(separator))
    }

  }

  class Tuple21Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17],
      format18: StringKeyFormat[T18],
      format19: StringKeyFormat[T19],
      format20: StringKeyFormat[T20],
      format21: StringKeyFormat[T21])
    extends StringKeyFormat[Tuple21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12, part13, part14, part15, part16, part17, part18, part19, part20, part21) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
            parsed12 <- format12.reads(StringKey(unescapeSeparator(part12)))
            parsed13 <- format13.reads(StringKey(unescapeSeparator(part13)))
            parsed14 <- format14.reads(StringKey(unescapeSeparator(part14)))
            parsed15 <- format15.reads(StringKey(unescapeSeparator(part15)))
            parsed16 <- format16.reads(StringKey(unescapeSeparator(part16)))
            parsed17 <- format17.reads(StringKey(unescapeSeparator(part17)))
            parsed18 <- format18.reads(StringKey(unescapeSeparator(part18)))
            parsed19 <- format19.reads(StringKey(unescapeSeparator(part19)))
            parsed20 <- format20.reads(StringKey(unescapeSeparator(part20)))
            parsed21 <- format21.reads(StringKey(unescapeSeparator(part21)))
          } yield {
            Tuple21(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11,
              parsed12,
              parsed13,
              parsed14,
              parsed15,
              parsed16,
              parsed17,
              parsed18,
              parsed19,
              parsed20,
              parsed21)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key),
        escapeSeparator(format12.writes(t._12).key),
        escapeSeparator(format13.writes(t._13).key),
        escapeSeparator(format14.writes(t._14).key),
        escapeSeparator(format15.writes(t._15).key),
        escapeSeparator(format16.writes(t._16).key),
        escapeSeparator(format17.writes(t._17).key),
        escapeSeparator(format18.writes(t._18).key),
        escapeSeparator(format19.writes(t._19).key),
        escapeSeparator(format20.writes(t._20).key),
        escapeSeparator(format21.writes(t._21).key)).mkString(separator))
    }

  }

  class Tuple22Format[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](separator: String)
      (implicit format1: StringKeyFormat[T1],
      format2: StringKeyFormat[T2],
      format3: StringKeyFormat[T3],
      format4: StringKeyFormat[T4],
      format5: StringKeyFormat[T5],
      format6: StringKeyFormat[T6],
      format7: StringKeyFormat[T7],
      format8: StringKeyFormat[T8],
      format9: StringKeyFormat[T9],
      format10: StringKeyFormat[T10],
      format11: StringKeyFormat[T11],
      format12: StringKeyFormat[T12],
      format13: StringKeyFormat[T13],
      format14: StringKeyFormat[T14],
      format15: StringKeyFormat[T15],
      format16: StringKeyFormat[T16],
      format17: StringKeyFormat[T17],
      format18: StringKeyFormat[T18],
      format19: StringKeyFormat[T19],
      format20: StringKeyFormat[T20],
      format21: StringKeyFormat[T21],
      format22: StringKeyFormat[T22])
    extends StringKeyFormat[Tuple22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22]] {

    // Since the string is a regex string, we need to explicitly make separator
    // (e.g., '.' or '*') literal.
    // uses negative lookbehind
    private[this] val splitRegex = s"(?<!\\!)${Pattern.quote(separator)}".r

    private[this] def escapeSeparator(s: String) = s.replace(s"$separator", s"!$separator")
    private[this] def unescapeSeparator(s: String) = s.replace(s"!$separator", s"$separator")

    override def reads(key: StringKey): Option[Tuple22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22]] = {
      splitRegex.split(key.key) match {
        case Array(part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12, part13, part14, part15, part16, part17, part18, part19, part20, part21, part22) =>
          for {
            parsed1 <- format1.reads(StringKey(unescapeSeparator(part1)))
            parsed2 <- format2.reads(StringKey(unescapeSeparator(part2)))
            parsed3 <- format3.reads(StringKey(unescapeSeparator(part3)))
            parsed4 <- format4.reads(StringKey(unescapeSeparator(part4)))
            parsed5 <- format5.reads(StringKey(unescapeSeparator(part5)))
            parsed6 <- format6.reads(StringKey(unescapeSeparator(part6)))
            parsed7 <- format7.reads(StringKey(unescapeSeparator(part7)))
            parsed8 <- format8.reads(StringKey(unescapeSeparator(part8)))
            parsed9 <- format9.reads(StringKey(unescapeSeparator(part9)))
            parsed10 <- format10.reads(StringKey(unescapeSeparator(part10)))
            parsed11 <- format11.reads(StringKey(unescapeSeparator(part11)))
            parsed12 <- format12.reads(StringKey(unescapeSeparator(part12)))
            parsed13 <- format13.reads(StringKey(unescapeSeparator(part13)))
            parsed14 <- format14.reads(StringKey(unescapeSeparator(part14)))
            parsed15 <- format15.reads(StringKey(unescapeSeparator(part15)))
            parsed16 <- format16.reads(StringKey(unescapeSeparator(part16)))
            parsed17 <- format17.reads(StringKey(unescapeSeparator(part17)))
            parsed18 <- format18.reads(StringKey(unescapeSeparator(part18)))
            parsed19 <- format19.reads(StringKey(unescapeSeparator(part19)))
            parsed20 <- format20.reads(StringKey(unescapeSeparator(part20)))
            parsed21 <- format21.reads(StringKey(unescapeSeparator(part21)))
            parsed22 <- format22.reads(StringKey(unescapeSeparator(part22)))
          } yield {
            Tuple22(
              parsed1,
              parsed2,
              parsed3,
              parsed4,
              parsed5,
              parsed6,
              parsed7,
              parsed8,
              parsed9,
              parsed10,
              parsed11,
              parsed12,
              parsed13,
              parsed14,
              parsed15,
              parsed16,
              parsed17,
              parsed18,
              parsed19,
              parsed20,
              parsed21,
              parsed22)
          }

        case _ => None
      }
    }

    override def writes(t: Tuple22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22]): StringKey = {
      StringKey(List(
        escapeSeparator(format1.writes(t._1).key),
        escapeSeparator(format2.writes(t._2).key),
        escapeSeparator(format3.writes(t._3).key),
        escapeSeparator(format4.writes(t._4).key),
        escapeSeparator(format5.writes(t._5).key),
        escapeSeparator(format6.writes(t._6).key),
        escapeSeparator(format7.writes(t._7).key),
        escapeSeparator(format8.writes(t._8).key),
        escapeSeparator(format9.writes(t._9).key),
        escapeSeparator(format10.writes(t._10).key),
        escapeSeparator(format11.writes(t._11).key),
        escapeSeparator(format12.writes(t._12).key),
        escapeSeparator(format13.writes(t._13).key),
        escapeSeparator(format14.writes(t._14).key),
        escapeSeparator(format15.writes(t._15).key),
        escapeSeparator(format16.writes(t._16).key),
        escapeSeparator(format17.writes(t._17).key),
        escapeSeparator(format18.writes(t._18).key),
        escapeSeparator(format19.writes(t._19).key),
        escapeSeparator(format20.writes(t._20).key),
        escapeSeparator(format21.writes(t._21).key),
        escapeSeparator(format22.writes(t._22).key)).mkString(separator))
    }

  }
}
