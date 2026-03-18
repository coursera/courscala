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
import org.scalatestplus.junit.AssertionsForJUnit
import org.scalatest.time.Millis
import org.scalatest.time.Seconds
import org.scalatest.time.Span

import scala.concurrent.Future
import scala.util.Failure
import scala.util.Success

class FuturesTest extends AssertionsForJUnit with ScalaFutures {

  implicit override val patienceConfig: PatienceConfig =
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

  @Test
  def extract_tuple3(): Unit = {
    val Futures.Extract(f1, f2, f3) = Futures.immediate((1, "hello", true))
    assertResult(1)(f1.futureValue)
    assertResult("hello")(f2.futureValue)
    assertResult(true)(f3.futureValue)
  }

  @Test
  def immediate_success(): Unit = {
    assertResult(42)(Futures.immediate(42).futureValue)
  }

  @Test
  def immediate_exception(): Unit = {
    val ex = new RuntimeException("boom")
    val fut = Futures.immediate[Int](throw ex)
    assertResult(ex)(fut.failed.futureValue)
  }

  @Test
  def safelyCall_success(): Unit = {
    assertResult(1)(Futures.safelyCall(Future.successful(1)).futureValue)
  }

  @Test
  def safelyCall_exception(): Unit = {
    val ex = new RuntimeException("boom")
    val fut = Futures.safelyCall[Int](throw ex)
    assertResult(ex)(fut.failed.futureValue)
  }

  @Test
  def findMatch_found(): Unit = {
    val futures = List(Future.successful("hello"), Future.successful("world"))
    val result = Futures.findMatch(futures) { case s if s.startsWith("w") => s.length }
    assertResult(Some(5))(result.futureValue)
  }

  @Test
  def findMatch_notFound(): Unit = {
    val futures = List(Future.successful("hello"), Future.successful("world"))
    val result = Futures.findMatch(futures) { case s if s.startsWith("z") => s.length }
    assertResult(None)(result.futureValue)
  }

  @Test
  def option_some(): Unit = {
    val result = Futures.option(Some(Future.successful(42)))
    assertResult(Some(42))(result.futureValue)
  }

  @Test
  def option_none(): Unit = {
    val result = Futures.option(None: Option[Future[Int]])
    assertResult(None)(result.futureValue)
  }

  @Test
  def toTry_success(): Unit = {
    import Futures.Implicits._
    val result = Future.successful(42).toTry
    assertResult(Success(42))(result.futureValue)
  }

  @Test
  def toTry_failure(): Unit = {
    import Futures.Implicits._
    val ex = new RuntimeException("boom")
    val result = Future.failed[Int](ex).toTry
    assertResult(Failure(ex))(result.futureValue)
  }

}
