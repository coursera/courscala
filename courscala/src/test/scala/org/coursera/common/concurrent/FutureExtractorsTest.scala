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

package org.coursera.common.concurrent

import org.junit.Test
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.Millis
import org.scalatest.time.Seconds
import org.scalatest.time.Span
import org.scalatestplus.junit.AssertionsForJUnit

import scala.concurrent.Future

/**
 * Tests for [[FutureExtractors]] arities 4 through 22.
 *
 * Arities 2 and 3 are already covered by [[FuturesTest]].
 * Each test calls `Futures.Extract(...)` in a pattern-match to exercise the
 * corresponding `unapply` overload, which is the primary coverage gap.
 */
class FutureExtractorsTest extends AssertionsForJUnit with ScalaFutures {

  implicit override val patienceConfig: PatienceConfig =
    PatienceConfig(timeout = scaled(Span(2, Seconds)), interval = scaled(Span(20, Millis)))

  import scala.concurrent.ExecutionContext.Implicits.global

  @Test
  def extract_tuple4(): Unit = {
    val Futures.Extract(f1, f2, f3, f4) = Futures.immediate((1, "a", true, 2.0))
    assertResult(1)(f1.futureValue)
    assertResult("a")(f2.futureValue)
    assertResult(true)(f3.futureValue)
    assertResult(2.0)(f4.futureValue)
  }

  @Test
  def extract_tuple5(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5) = Futures.immediate((1, 2, 3, 4, 5))
    assertResult(1)(f1.futureValue)
    assertResult(2)(f2.futureValue)
    assertResult(3)(f3.futureValue)
    assertResult(4)(f4.futureValue)
    assertResult(5)(f5.futureValue)
  }

  @Test
  def extract_tuple6(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6) = Futures.immediate((1, 2, 3, 4, 5, 6))
    assertResult(1)(f1.futureValue)
    assertResult(6)(f6.futureValue)
  }

  @Test
  def extract_tuple7(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7) = Futures.immediate((1, 2, 3, 4, 5, 6, 7))
    assertResult(1)(f1.futureValue)
    assertResult(7)(f7.futureValue)
  }

  @Test
  def extract_tuple8(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8) = Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8))
    assertResult(1)(f1.futureValue)
    assertResult(8)(f8.futureValue)
  }

  @Test
  def extract_tuple9(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9) = Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9))
    assertResult(1)(f1.futureValue)
    assertResult(9)(f9.futureValue)
  }

  @Test
  def extract_tuple10(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    assertResult(1)(f1.futureValue)
    assertResult(10)(f10.futureValue)
  }

  @Test
  def extract_tuple11(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))
    assertResult(1)(f1.futureValue)
    assertResult(11)(f11.futureValue)
  }

  @Test
  def extract_tuple12(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
    assertResult(1)(f1.futureValue)
    assertResult(12)(f12.futureValue)
  }

  @Test
  def extract_tuple13(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13))
    assertResult(1)(f1.futureValue)
    assertResult(13)(f13.futureValue)
  }

  @Test
  def extract_tuple14(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14))
    assertResult(1)(f1.futureValue)
    assertResult(14)(f14.futureValue)
  }

  @Test
  def extract_tuple15(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))
    assertResult(1)(f1.futureValue)
    assertResult(15)(f15.futureValue)
  }

  @Test
  def extract_tuple16(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16))
    assertResult(1)(f1.futureValue)
    assertResult(16)(f16.futureValue)
  }

  @Test
  def extract_tuple17(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17))
    assertResult(1)(f1.futureValue)
    assertResult(17)(f17.futureValue)
  }

  @Test
  def extract_tuple18(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18))
    assertResult(1)(f1.futureValue)
    assertResult(18)(f18.futureValue)
  }

  @Test
  def extract_tuple19(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19))
    assertResult(1)(f1.futureValue)
    assertResult(19)(f19.futureValue)
  }

  @Test
  def extract_tuple20(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20))
    assertResult(1)(f1.futureValue)
    assertResult(20)(f20.futureValue)
  }

  @Test
  def extract_tuple21(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20, f21) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21))
    assertResult(1)(f1.futureValue)
    assertResult(21)(f21.futureValue)
  }

  @Test
  def extract_tuple22(): Unit = {
    val Futures.Extract(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20, f21, f22) =
      Futures.immediate((1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22))
    assertResult(1)(f1.futureValue)
    assertResult(22)(f22.futureValue)
  }

  @Test
  def extract_tuple4_failedFuture_propagatesToAllComponents(): Unit = {
    val ex = new RuntimeException("upstream failure")
    val failed = Future.failed[(Int, String, Boolean, Double)](ex)
    val Futures.Extract(f1, f2, f3, f4) = failed
    assertResult(ex)(f1.failed.futureValue)
    assertResult(ex)(f4.failed.futureValue)
  }
}
