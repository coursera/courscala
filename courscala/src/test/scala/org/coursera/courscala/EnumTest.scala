package org.coursera.courscala

import org.coursera.courscala.Container.Direction
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit
import org.scalatest.junit.JUnitSuite

class EnumTest extends JUnitSuite with AssertionsForJUnit {

  @Test
  def withName(): Unit = {
    assert(Colors.withName("Red") === Colors.Red)
    intercept[NoSuchElementException](Colors.withName("unknown"))
  }

  @Test
  def topLevel(): Unit = {
    assert(Colors.symbols === Set(Colors.Red, Colors.Amber, Colors.Green))
    assert(Direction.withName("North") === Direction.North)
  }

  @Test
  def contained(): Unit = {
    assert(Direction.symbols === Set(
      Direction.North, Direction.South, Direction.East, Direction.West))
    assert(Direction.withName("North") === Direction.North)
  }
}

sealed trait Colors extends EnumSymbol

object Colors extends Enum[Colors] {
  case object Red extends Colors
  case object Amber extends Colors
  case object Green extends Colors
}

object Container {
  sealed trait Direction extends EnumSymbol

  object Direction extends Enum[Direction] {
    case object North extends Direction
    case object South extends Direction
    case object East extends Direction
    case object West extends Direction
  }
}
