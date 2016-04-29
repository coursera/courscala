/*
 Copyright 2015 Coursera Inc.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package org.coursera.common.collection

import org.coursera.common.collection
import org.coursera.common.collection.Container.Direction
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit

import scala.collection.immutable.SortedSet

class EnumTest extends AssertionsForJUnit {

  @Test
  def withName(): Unit = {
    assertResult(Color.withName("Red"))(Color.Red)
    intercept[NoSuchElementException](Color.withName("unknown"))
  }

  @Test
  def topLevel(): Unit = {
    assertResult(Color.symbols)(Set(Color.Red, Color.Amber, Color.Green))
    assertResult(Direction.withName("North"))(Direction.North)
  }

  @Test
  def contained(): Unit = {
    assertResult(Direction.symbols)(Set(
      Direction.North, Direction.South, Direction.East, Direction.West))
    assertResult(Direction.withName("North"))(Direction.North)
  }

  @Test
  def aliased(): Unit = {
    assertResult(Aliased.symbols)(Set(Aliased.BackRub, Aliased.TypeSafe))
    assertResult(Aliased.withName("Lightbend"))(Aliased.TypeSafe)
    assertResult(Aliased.TypeSafe.name)("Lightbend")
    assertResult(Aliased.TypeSafe.toString)("Lightbend")
  }

  @Test
  def indexed(): Unit = {
    assert(Indexed.Zero < Indexed.One)
    assert(Indexed.Zero <= Indexed.One)
    assert(Indexed.One >= Indexed.Zero)
    assert(Indexed.One > Indexed.Zero)

    // symbols are ordered for IndexedEnum
    assertResult(Indexed.symbols)(Set(Indexed.Zero, Indexed.One, Indexed.Two))

    assertResult(Indexed.withName("Zero"))(Indexed.Zero)
    assertResult(Indexed.Zero.name)("Zero")
    assertResult(Indexed.Zero.toString)("Zero")
    assertResult(Indexed.Zero.id)(0)
    assertResult(Indexed(0))(Indexed.Zero)

    assertResult(Indexed.One.id)(1)
    assertResult(Indexed(1))(Indexed.One)
  }

  @Test
  def aliasedAndIndexed(): Unit = {
    assert(AliasedIndexed.Zero < AliasedIndexed.One)
    assert(AliasedIndexed.Zero <= AliasedIndexed.One)
    assert(AliasedIndexed.One >= AliasedIndexed.Zero)
    assert(AliasedIndexed.One > AliasedIndexed.Zero)

    assertResult(AliasedIndexed.symbols.toList)(List(AliasedIndexed.Zero, AliasedIndexed.One))
    assertResult(AliasedIndexed.ids)(SortedSet(0, 1))
    assertResult(AliasedIndexed.withName("zero"))(AliasedIndexed.Zero)
    assertResult(AliasedIndexed.Zero.name)("zero")
    assertResult(AliasedIndexed.Zero.toString)("zero")
    assertResult(AliasedIndexed.Zero.id)(0)
    assertResult(AliasedIndexed(0))(AliasedIndexed.Zero)

    assertResult(AliasedIndexed.One.id)(1)
    assertResult(AliasedIndexed(1))(AliasedIndexed.One)
  }
}

sealed trait Color extends EnumSymbol

object Color extends collection.Enum[Color] {
  case object Red extends Color
  case object Amber extends Color
  case object Green extends Color

  /**
   * Regression test for bug in [[Enum.withName]] failing on non-[[EnumSymbol]] `object`s.
   */
  object Unrelated
}

object Container {
  sealed trait Direction extends EnumSymbol

  object Direction extends collection.Enum[Direction] {
    case object North extends Direction
    case object South extends Direction
    case object East extends Direction
    case object West extends Direction
  }
}

sealed abstract class Aliased(replacement: String) extends AliasedEnumSymbol(replacement)

object Aliased extends collection.Enum[Aliased] {
  case object BackRub extends Aliased("Google")
  case object TypeSafe extends Aliased("Lightbend")
}

sealed abstract class Indexed(id: Int) extends IndexedEnumSymbol(id)

object Indexed extends IndexedEnum[Indexed] {
  case object Zero extends Indexed(0)
  case object Two extends Indexed(2)
  case object One extends Indexed(1)
}

sealed abstract class AliasedIndexed(id: Int, name: String)
  extends AliasedIndexedEnumSymbol(id, name)

object AliasedIndexed extends IndexedEnum[AliasedIndexed] {
  case object Zero extends AliasedIndexed(0, "zero")
  case object One extends AliasedIndexed(1, "one")
}
