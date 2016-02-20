package org.coursera.courscala

import org.coursera.reflect.CompanionReflector

import scala.language.experimental.macros

/**
 * Provides a convenient way to define "ADT" based Scala enums (enums defined with
 * case objects extending a sealed trait) while still retaining the ability to:
 *
 * - Look up all the symbols of the Enum
 * - Look up a particular symbol given it's string name
 *
 * Using case objects for enums has some important advantages over using scala.Enumeration:
 *
 * - Exhaustive pattern matching
 * - The type used to declare the Enum symbols can be full customized
 *
 * Example usage:
 *
 * ```
 * sealed trait Direction extends EnumSymbol
 *
 * object Direction extends Enum[Direction] {
 *   case object NORTH extends Direction
 *   case object SOUTH extends Direction
 *   case object EAST extends Direction
 *   case object WEST extends Direction
 * }
 *
 * scala> Direction.symbols
 * res1: Set[Direction] = Set(NORTH, SOUTH, EAST, WEST)
 *
 * scala> println(Direction.withName("SOUTH")
 * res2: Direction = Direction.SOUTH
 * ```
 *
 */
trait Enum[SymbolType <: EnumSymbol] { self =>

  /**
   * All the symbols of the enum.
   */
  lazy val symbols: Set[SymbolType] = {
    val enumObject = self.getClass
    val symbolType = CompanionReflector.lookupClass[SymbolType](enumObject)

    // For reasons I don't understand,
    // depending on depth of subclass nesting, the enum symbol classes may either appear
    // under the enum's type or the enum symbol's type when locating them via reflection.
    // E.g. under "org.example.Color" for top level declarations and
    // "org.example.Container$Color$" for nested declarations.
    val subclasses = if (enumObject.getEnclosingClass != null) {
      enumObject.getDeclaredClasses
    } else {
      symbolType.getDeclaredClasses
    }
    subclasses.map { c =>
      CompanionReflector.companionInstance(c.asInstanceOf[Class[SymbolType]])
    }.collect {
      // Due to type erasure we can't check that the symbols are actually of
      // type SymbolType at runtime.
      case t: EnumSymbol => t.asInstanceOf[SymbolType]
    }.to[Set]
  }

  private[this] lazy val byName = symbols.map { s => s.name -> s }.toMap

  /**
   * Gets a Enum symbol by it's name.
   *
   * @param name provide the enum symbol's name.
   * @return the Enum symbol matching the given name.
   * @throws NoSuchElementException if no enum symbol is found for the given name
   */
  def withName(name: String): SymbolType = {
    byName.getOrElse(name, throw new NoSuchElementException(s"No value found for '$name'"))
  }
}

/**
 * A symbol of an Enum.
 */
trait EnumSymbol {
  def name: String = toString
}
