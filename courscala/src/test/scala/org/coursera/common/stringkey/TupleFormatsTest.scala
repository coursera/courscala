/*
 * Copyright 2024 Coursera Inc.
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

import org.junit.Test
import org.scalatestplus.junit.AssertionsForJUnit

/**
 * Roundtrip read/write tests for [[TupleFormats]] arities 4 through 22.
 *
 * Arities 2 and 3 are already covered by [[StringKeyFormatTest]].
 * This file covers the remaining generated formats to bring code coverage
 * of TupleFormats.scala close to 100%.
 */
class TupleFormatsTest extends AssertionsForJUnit {

  import TupleFormatsTest._

  // ─── Tuple4 ────────────────────────────────────────────────────────────────

  @Test def tuple4_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d")
    val key = format4.writes(data)
    assertResult(Some(data))(format4.reads(key))
  }

  @Test def tuple4_key_containsSeparators(): Unit = {
    val data = ("1", "2", "3", "4")
    assertResult(s"1${SEP}2${SEP}3${SEP}4")(format4.writes(data).key)
  }

  @Test def tuple4_read_badArity_returnsNone(): Unit = {
    assertResult(None)(format4.reads(StringKey(s"a${SEP}b${SEP}c")))
  }

  // ─── Tuple5 ────────────────────────────────────────────────────────────────

  @Test def tuple5_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e")
    val key = format5.writes(data)
    assertResult(Some(data))(format5.reads(key))
  }

  @Test def tuple5_key_containsSeparators(): Unit = {
    val data = ("1", "2", "3", "4", "5")
    assertResult(s"1${SEP}2${SEP}3${SEP}4${SEP}5")(format5.writes(data).key)
  }

  // ─── Tuple6 ────────────────────────────────────────────────────────────────

  @Test def tuple6_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f")
    val key = format6.writes(data)
    assertResult(Some(data))(format6.reads(key))
  }

  @Test def tuple6_key_containsSeparators(): Unit = {
    val data = ("1", "2", "3", "4", "5", "6")
    assertResult(s"1${SEP}2${SEP}3${SEP}4${SEP}5${SEP}6")(format6.writes(data).key)
  }

  // ─── Tuple7 ────────────────────────────────────────────────────────────────

  @Test def tuple7_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g")
    val key = format7.writes(data)
    assertResult(Some(data))(format7.reads(key))
  }

  @Test def tuple7_key_containsSeparators(): Unit = {
    val data = ("1", "2", "3", "4", "5", "6", "7")
    assertResult(s"1${SEP}2${SEP}3${SEP}4${SEP}5${SEP}6${SEP}7")(format7.writes(data).key)
  }

  // ─── Tuple8 ────────────────────────────────────────────────────────────────

  @Test def tuple8_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h")
    val key = format8.writes(data)
    assertResult(Some(data))(format8.reads(key))
  }

  @Test def tuple8_key_containsSeparators(): Unit = {
    val data = ("1", "2", "3", "4", "5", "6", "7", "8")
    assertResult(s"1${SEP}2${SEP}3${SEP}4${SEP}5${SEP}6${SEP}7${SEP}8")(format8.writes(data).key)
  }

  // ─── Tuple9 ────────────────────────────────────────────────────────────────

  @Test def tuple9_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i")
    val key = format9.writes(data)
    assertResult(Some(data))(format9.reads(key))
  }

  // ─── Tuple10 ───────────────────────────────────────────────────────────────

  @Test def tuple10_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")
    val key = format10.writes(data)
    assertResult(Some(data))(format10.reads(key))
  }

  // ─── Tuple11 ───────────────────────────────────────────────────────────────

  @Test def tuple11_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k")
    val key = format11.writes(data)
    assertResult(Some(data))(format11.reads(key))
  }

  // ─── Tuple12 ───────────────────────────────────────────────────────────────

  @Test def tuple12_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l")
    val key = format12.writes(data)
    assertResult(Some(data))(format12.reads(key))
  }

  // ─── Tuple13 ───────────────────────────────────────────────────────────────

  @Test def tuple13_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m")
    val key = format13.writes(data)
    assertResult(Some(data))(format13.reads(key))
  }

  // ─── Tuple14 ───────────────────────────────────────────────────────────────

  @Test def tuple14_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n")
    val key = format14.writes(data)
    assertResult(Some(data))(format14.reads(key))
  }

  // ─── Tuple15 ───────────────────────────────────────────────────────────────

  @Test def tuple15_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o")
    val key = format15.writes(data)
    assertResult(Some(data))(format15.reads(key))
  }

  // ─── Tuple16 ───────────────────────────────────────────────────────────────

  @Test def tuple16_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p")
    val key = format16.writes(data)
    assertResult(Some(data))(format16.reads(key))
  }

  // ─── Tuple17 ───────────────────────────────────────────────────────────────

  @Test def tuple17_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q")
    val key = format17.writes(data)
    assertResult(Some(data))(format17.reads(key))
  }

  // ─── Tuple18 ───────────────────────────────────────────────────────────────

  @Test def tuple18_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r")
    val key = format18.writes(data)
    assertResult(Some(data))(format18.reads(key))
  }

  // ─── Tuple19 ───────────────────────────────────────────────────────────────

  @Test def tuple19_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s")
    val key = format19.writes(data)
    assertResult(Some(data))(format19.reads(key))
  }

  // ─── Tuple20 ───────────────────────────────────────────────────────────────

  @Test def tuple20_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t")
    val key = format20.writes(data)
    assertResult(Some(data))(format20.reads(key))
  }

  // ─── Tuple21 ───────────────────────────────────────────────────────────────

  @Test def tuple21_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u")
    val key = format21.writes(data)
    assertResult(Some(data))(format21.reads(key))
  }

  // ─── Tuple22 ───────────────────────────────────────────────────────────────

  @Test def tuple22_writeAndRead_roundtrip(): Unit = {
    val data = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v")
    val key = format22.writes(data)
    assertResult(Some(data))(format22.reads(key))
  }

  @Test def tuple22_key_containsSeparators(): Unit = {
    val data = ("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22")
    val key = format22.writes(data)
    assertResult(s"1${SEP}2${SEP}3${SEP}4${SEP}5${SEP}6${SEP}7${SEP}8${SEP}9${SEP}10${SEP}11${SEP}12${SEP}13${SEP}14${SEP}15${SEP}16${SEP}17${SEP}18${SEP}19${SEP}20${SEP}21${SEP}22")(key.key)
  }

  // ─── Separator escaping (verify consistency with Tuple4) ───────────────────

  @Test def tuple4_withEscapedSeparatorInValue_roundtrips(): Unit = {
    val data = (s"a${SEP}b", "c", "d", "e")
    val key = format4.writes(data)
    assertResult(Some(data))(format4.reads(key))
  }

  // ─── Bad-arity reads return None ───────────────────────────────────────────

  @Test def tuple5_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format5.reads(StringKey(s"a${SEP}b${SEP}c${SEP}d")))
  }

  @Test def tuple10_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format10.reads(StringKey(s"a${SEP}b")))
  }

  // ─── Bad-arity reads return None for remaining arities ─────────────────────
  // Each TupleNFormat has a branch: Array(N parts) vs _ => None.
  // The `_ => None` path was 0% for arities 3, 6–9, 11–22.

  @Test def tuple3_read_tooFewSegments_returnsNone(): Unit = {
    // Arities 2 and 3 live in StringKeyFormatTest; this covers the Tuple3 `_ => None` branch.
    val fmt3 = implicitly[StringKeyFormat[(String, String, String)]]
    assertResult(None)(fmt3.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple6_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format6.reads(StringKey(s"a${SEP}b${SEP}c")))
  }

  @Test def tuple7_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format7.reads(StringKey(s"a${SEP}b${SEP}c")))
  }

  @Test def tuple8_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format8.reads(StringKey(s"a${SEP}b${SEP}c")))
  }

  @Test def tuple9_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format9.reads(StringKey(s"a${SEP}b${SEP}c")))
  }

  @Test def tuple11_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format11.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple12_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format12.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple13_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format13.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple14_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format14.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple15_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format15.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple16_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format16.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple17_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format17.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple18_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format18.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple19_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format19.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple20_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format20.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple21_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format21.reads(StringKey(s"a${SEP}b")))
  }

  @Test def tuple22_read_tooFewSegments_returnsNone(): Unit = {
    assertResult(None)(format22.reads(StringKey(s"a${SEP}b")))
  }
}

object TupleFormatsTest {

  val SEP = "~"

  val format4  = implicitly[StringKeyFormat[(String, String, String, String)]]
  val format5  = implicitly[StringKeyFormat[(String, String, String, String, String)]]
  val format6  = implicitly[StringKeyFormat[(String, String, String, String, String, String)]]
  val format7  = implicitly[StringKeyFormat[(String, String, String, String, String, String, String)]]
  val format8  = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String)]]
  val format9  = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String)]]
  val format10 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String)]]
  val format11 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String)]]
  val format12 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String, String)]]
  val format13 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String, String, String)]]
  val format14 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String, String, String, String)]]
  val format15 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)]]
  val format16 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)]]
  val format17 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)]]
  val format18 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)]]
  val format19 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)]]
  val format20 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)]]
  val format21 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)]]
  val format22 = implicitly[StringKeyFormat[(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)]]
}
