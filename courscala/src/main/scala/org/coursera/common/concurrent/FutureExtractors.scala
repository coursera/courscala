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

/**
 * Makes it easy to extract components from futures of tuples. For example,
 * {{{
 *   def computation(): Future[(Int, String)]
 *   val Futures.Extract(number, message) = computation()
 * }}}
 * creates two separate futures `number: Future[Int]` and `message: Future[String]` that contain
 * the two components of the computation result.
 *
 * Note that all component futures fail if the future of the original tuple fails.
 */
private[concurrent] trait FutureExtractors {

  // GENERATED CODE BELOW.

  object Extract {
    def unapply[T1, T2](
        futureTuple: Future[(T1, T2)])
        (implicit ec: ExecutionContext):
      Option[(Future[T1], Future[T2])] = {

      Some(Tuple2(
        futureTuple.map(_._1),
        futureTuple.map(_._2)))
    }

    def unapply[T1, T2, T3](
        futureTuple: Future[(T1, T2, T3)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3])] = {

      Some(Tuple3(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3)))
    }

    def unapply[T1, T2, T3, T4](
        futureTuple: Future[(T1, T2, T3, T4)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4])] = {

      Some(Tuple4(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4)))
    }

    def unapply[T1, T2, T3, T4, T5](
        futureTuple: Future[(T1, T2, T3, T4, T5)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5])] = {

      Some(Tuple5(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5)))
    }

    def unapply[T1, T2, T3, T4, T5, T6](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6])] = {

      Some(Tuple6(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7])] = {

      Some(Tuple7(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8])] = {

      Some(Tuple8(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9])] = {

      Some(Tuple9(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10])] = {

      Some(Tuple10(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11])] = {

      Some(Tuple11(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit,
        dummyImplicit10: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11], Future[T12])] = {

      Some(Tuple12(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11),
        futureTuple.map(_._12)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit,
        dummyImplicit10: DummyImplicit,
        dummyImplicit11: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11], Future[T12], Future[T13])] = {

      Some(Tuple13(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11),
        futureTuple.map(_._12),
        futureTuple.map(_._13)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit,
        dummyImplicit10: DummyImplicit,
        dummyImplicit11: DummyImplicit,
        dummyImplicit12: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11], Future[T12], Future[T13], Future[T14])] = {

      Some(Tuple14(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11),
        futureTuple.map(_._12),
        futureTuple.map(_._13),
        futureTuple.map(_._14)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit,
        dummyImplicit10: DummyImplicit,
        dummyImplicit11: DummyImplicit,
        dummyImplicit12: DummyImplicit,
        dummyImplicit13: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11], Future[T12], Future[T13], Future[T14], Future[T15])] = {

      Some(Tuple15(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11),
        futureTuple.map(_._12),
        futureTuple.map(_._13),
        futureTuple.map(_._14),
        futureTuple.map(_._15)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit,
        dummyImplicit10: DummyImplicit,
        dummyImplicit11: DummyImplicit,
        dummyImplicit12: DummyImplicit,
        dummyImplicit13: DummyImplicit,
        dummyImplicit14: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11], Future[T12], Future[T13], Future[T14], Future[T15], Future[T16])] = {

      Some(Tuple16(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11),
        futureTuple.map(_._12),
        futureTuple.map(_._13),
        futureTuple.map(_._14),
        futureTuple.map(_._15),
        futureTuple.map(_._16)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit,
        dummyImplicit10: DummyImplicit,
        dummyImplicit11: DummyImplicit,
        dummyImplicit12: DummyImplicit,
        dummyImplicit13: DummyImplicit,
        dummyImplicit14: DummyImplicit,
        dummyImplicit15: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11], Future[T12], Future[T13], Future[T14], Future[T15], Future[T16], Future[T17])] = {

      Some(Tuple17(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11),
        futureTuple.map(_._12),
        futureTuple.map(_._13),
        futureTuple.map(_._14),
        futureTuple.map(_._15),
        futureTuple.map(_._16),
        futureTuple.map(_._17)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit,
        dummyImplicit10: DummyImplicit,
        dummyImplicit11: DummyImplicit,
        dummyImplicit12: DummyImplicit,
        dummyImplicit13: DummyImplicit,
        dummyImplicit14: DummyImplicit,
        dummyImplicit15: DummyImplicit,
        dummyImplicit16: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11], Future[T12], Future[T13], Future[T14], Future[T15], Future[T16], Future[T17], Future[T18])] = {

      Some(Tuple18(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11),
        futureTuple.map(_._12),
        futureTuple.map(_._13),
        futureTuple.map(_._14),
        futureTuple.map(_._15),
        futureTuple.map(_._16),
        futureTuple.map(_._17),
        futureTuple.map(_._18)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit,
        dummyImplicit10: DummyImplicit,
        dummyImplicit11: DummyImplicit,
        dummyImplicit12: DummyImplicit,
        dummyImplicit13: DummyImplicit,
        dummyImplicit14: DummyImplicit,
        dummyImplicit15: DummyImplicit,
        dummyImplicit16: DummyImplicit,
        dummyImplicit17: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11], Future[T12], Future[T13], Future[T14], Future[T15], Future[T16], Future[T17], Future[T18], Future[T19])] = {

      Some(Tuple19(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11),
        futureTuple.map(_._12),
        futureTuple.map(_._13),
        futureTuple.map(_._14),
        futureTuple.map(_._15),
        futureTuple.map(_._16),
        futureTuple.map(_._17),
        futureTuple.map(_._18),
        futureTuple.map(_._19)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit,
        dummyImplicit10: DummyImplicit,
        dummyImplicit11: DummyImplicit,
        dummyImplicit12: DummyImplicit,
        dummyImplicit13: DummyImplicit,
        dummyImplicit14: DummyImplicit,
        dummyImplicit15: DummyImplicit,
        dummyImplicit16: DummyImplicit,
        dummyImplicit17: DummyImplicit,
        dummyImplicit18: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11], Future[T12], Future[T13], Future[T14], Future[T15], Future[T16], Future[T17], Future[T18], Future[T19], Future[T20])] = {

      Some(Tuple20(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11),
        futureTuple.map(_._12),
        futureTuple.map(_._13),
        futureTuple.map(_._14),
        futureTuple.map(_._15),
        futureTuple.map(_._16),
        futureTuple.map(_._17),
        futureTuple.map(_._18),
        futureTuple.map(_._19),
        futureTuple.map(_._20)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit,
        dummyImplicit10: DummyImplicit,
        dummyImplicit11: DummyImplicit,
        dummyImplicit12: DummyImplicit,
        dummyImplicit13: DummyImplicit,
        dummyImplicit14: DummyImplicit,
        dummyImplicit15: DummyImplicit,
        dummyImplicit16: DummyImplicit,
        dummyImplicit17: DummyImplicit,
        dummyImplicit18: DummyImplicit,
        dummyImplicit19: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11], Future[T12], Future[T13], Future[T14], Future[T15], Future[T16], Future[T17], Future[T18], Future[T19], Future[T20], Future[T21])] = {

      Some(Tuple21(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11),
        futureTuple.map(_._12),
        futureTuple.map(_._13),
        futureTuple.map(_._14),
        futureTuple.map(_._15),
        futureTuple.map(_._16),
        futureTuple.map(_._17),
        futureTuple.map(_._18),
        futureTuple.map(_._19),
        futureTuple.map(_._20),
        futureTuple.map(_._21)))
    }

    def unapply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](
        futureTuple: Future[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])
        (implicit ec: ExecutionContext,
        dummyImplicit1: DummyImplicit,
        dummyImplicit2: DummyImplicit,
        dummyImplicit3: DummyImplicit,
        dummyImplicit4: DummyImplicit,
        dummyImplicit5: DummyImplicit,
        dummyImplicit6: DummyImplicit,
        dummyImplicit7: DummyImplicit,
        dummyImplicit8: DummyImplicit,
        dummyImplicit9: DummyImplicit,
        dummyImplicit10: DummyImplicit,
        dummyImplicit11: DummyImplicit,
        dummyImplicit12: DummyImplicit,
        dummyImplicit13: DummyImplicit,
        dummyImplicit14: DummyImplicit,
        dummyImplicit15: DummyImplicit,
        dummyImplicit16: DummyImplicit,
        dummyImplicit17: DummyImplicit,
        dummyImplicit18: DummyImplicit,
        dummyImplicit19: DummyImplicit,
        dummyImplicit20: DummyImplicit):
      Option[(Future[T1], Future[T2], Future[T3], Future[T4], Future[T5], Future[T6], Future[T7], Future[T8], Future[T9], Future[T10], Future[T11], Future[T12], Future[T13], Future[T14], Future[T15], Future[T16], Future[T17], Future[T18], Future[T19], Future[T20], Future[T21], Future[T22])] = {

      Some(Tuple22(
        futureTuple.map(_._1),
        futureTuple.map(_._2),
        futureTuple.map(_._3),
        futureTuple.map(_._4),
        futureTuple.map(_._5),
        futureTuple.map(_._6),
        futureTuple.map(_._7),
        futureTuple.map(_._8),
        futureTuple.map(_._9),
        futureTuple.map(_._10),
        futureTuple.map(_._11),
        futureTuple.map(_._12),
        futureTuple.map(_._13),
        futureTuple.map(_._14),
        futureTuple.map(_._15),
        futureTuple.map(_._16),
        futureTuple.map(_._17),
        futureTuple.map(_._18),
        futureTuple.map(_._19),
        futureTuple.map(_._20),
        futureTuple.map(_._21),
        futureTuple.map(_._22)))
    }
  }

}
