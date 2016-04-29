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
