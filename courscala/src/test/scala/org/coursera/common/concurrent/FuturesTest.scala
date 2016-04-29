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

package org.coursera.common.concurrent

import org.junit.Test
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.junit.AssertionsForJUnit
import org.scalatest.time.Millis
import org.scalatest.time.Seconds
import org.scalatest.time.Span

import scala.concurrent.Future

class FuturesTest extends AssertionsForJUnit with ScalaFutures {

  implicit override val patienceConfig =
    PatienceConfig(timeout = scaled(Span(1, Seconds)), interval = scaled(Span(20, Millis)))

  import scala.concurrent.ExecutionContext.Implicits.global

  @Test
  def map(): Unit = {
    val raw = Map("a" -> Future.successful(1), "b" -> Future.successful(2))
    val expected = Map("a" -> 1, "b" -> 2)

    assertResult(expected)(Futures.map(raw).futureValue)
  }

  @Test
  def extract(): Unit = {
    val Futures.Extract(future1, future2) = Futures.immediate((1, 2))

    assertResult(1)(future1.futureValue)
    assertResult(2)(future2.futureValue)
  }

}
