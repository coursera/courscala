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

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.Promise
import scala.util.Failure
import scala.util.Success
import scala.util.Try
import scala.util.control.NonFatal

object Futures extends FutureExtractors {

  /**
   * Executes `f` immediately without an [[ExecutionContext]].
   *
   * Returns a successful future if `f` completes or a failed one if `f` throws an exception.
   */
  def immediate[T](f: => T): Future[T] = {
    try {
      Future.successful(f)
    } catch {
      case NonFatal(e) =>
        Future.failed(e)
    }
  }

  /**
   * Executes `f` immediately. Returns `f`'s future (either successful or not) if `f` completes
   * or a failed one if `f` throws an exception.
   */
  def safelyCall[T](f: => Future[T]): Future[T] = {
    Try(f).recover {
      case e: Throwable => Future.failed(e)
    }.get
  }

  /**
   * Variant of [[Future.find]] that applies a [[PartialFunction]], if it's defined, rather than
   * checking a predicate. This allows type safe transformation of matched values.
   *
   * For example, if you wanted to find the first string that represented a non-negative integer:
   * {{{
   *   val nonNegativeIntRegex = """[0-9]+""".r
   *   val futures: immutable.Seq[Future[String]] = startComputation()
   *
   *   val matchOptionFuture: Future[Option[Int]] = Futures.findMatch(futures) {
   *     case nonNegativeIntRegex(s) => s.toInt
   *   }
   * }}}
   * (Types added for clarity where they would normally be inferred.)
   *
   * With [[Future.find]], you'd need to use something more verbose instead:
   * {{{
   *   val matchOptionFuture: Future[Option[Int]] = Futures.find(futures) {
   *     case nonNegativeIntRegex(s) => true
   *     case _ => false
   *   }.map(_.toInt)
   * }}}
   */
  def findMatch[T, U](
      futures: TraversableOnce[Future[T]])
      (pf: PartialFunction[T, U])
      (implicit ec: ExecutionContext): Future[Option[U]] = {

    Future.find(futures)(pf.isDefinedAt).map(_.map(pf))
  }

  def option[T](option: Option[Future[T]])(implicit ec: ExecutionContext): Future[Option[T]] =
    option.map(_.map(Some(_))).getOrElse(Future.successful(None))

  def map[K, V](m: Map[K, Future[V]])(implicit ec: ExecutionContext): Future[Map[K, V]] = {
    val elementFutures = m.map { case (key, valueFuture) =>
      valueFuture.map(key -> _)
    }
    Future.sequence(elementFutures).map(_.toMap)
  }

  object Implicits {

    implicit class FutureOps[T](future: Future[T]) {

      def toTry(implicit ec: ExecutionContext): Future[Try[T]] = {
        future
          .map(Success.apply)
          .recover(PartialFunction(Failure.apply))
      }

    }

  }

}
