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

package org.coursera.common.collection

import org.coursera.common.reflect.CompanionReflector

import scala.collection.immutable.SortedSet

/**
 * Provides a convenient way to define "ADT" based Scala enumerations (enumerations defined with
 * case objects extending a sealed trait) while still retaining the ability to:
 *
 * - Look up all the symbols of the enumeration
 * - Look up a particular enumeration symbol given its string name
 *
 * Using case objects for enumerations has some important advantages over using
 * [[scala.Enumeration]]:
 *
 * - Exhaustive pattern matching
 * - The types used to declare enumeration symbols are 1st class scala types
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
 * scala> Direction.withName("SOUTH")
 * res2: Direction = Direction.SOUTH
 * ```
 *
 */
trait Enum[SymbolType <: EnumSymbol] { self =>

  /**
   * All the symbols of the enumeration.
   */
  lazy val symbols: Set[SymbolType] = findSymbols

  protected[collection] def findSymbols: Set[SymbolType] = {
    val enumObject = self.getClass
    val symbolType = CompanionReflector.findScalaClassOfCompanionClass[SymbolType](enumObject)

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
    subclasses.collect {
      case c if symbolType.isAssignableFrom(c) =>
        CompanionReflector.findCompanionInstanceOfCompanionClass(c.asInstanceOf[Class[SymbolType]])
    }.collect {
      // Due to type erasure we can't check that the symbols are actually of
      // type SymbolType at runtime.
      case t: EnumSymbol => t.asInstanceOf[SymbolType]
    }.to[Set]
  }

  private[this] lazy val byName = symbols.map { s => s.name -> s }.toMap

  /**
   * Gets a Enum symbol by its name.
   *
   * @param name provide the enum symbol's name.
   * @return the Enum symbol matching the given name.
   * @throws NoSuchElementException if no enum symbol is found for the given name
   */
  def withName(name: String): SymbolType = {
    byName
      .get(name)
      .orElse(defaultValue)
      .getOrElse(throw new NoSuchElementException(s"No value found for '$name'"))
  }

  /**
   * Override to provide a default value when [[withName()]] is called and no element is found
   * @return
   */
  protected def defaultValue: Option[SymbolType] = None
}

/**
 * An [[Enum]] symbol.
 *
 * See [[Enum]] for usage details.
 *
 * For case objects, the symbol name will be the case object's "simple name" (unqualified Scala
 * type name). For case objects requiring a different symbol name than their Scala name,
 * see [[AliasedEnumSymbol]].
 */
trait EnumSymbol {
  def name: String = toString
}

/**
 * An [[Enum]] symbol with a symbol name that can be explicitly set and which may be different
 * than its corresponding Scala case object's name.
 *
 * Example usage:
 *
 * ```
 * abstract class RenamedCompany(currentName: String) extends AliasedEnumSymbol(currentName)
 * object RenamedCompany extends Enum[Aliased] {
 *  case object BackRub extends RenamedCompany("Google")
 *  case object TypeSafe extends RenamedCompany("Lightbend")
 * }
 *
 * scala> RenamedCompany.BackRub.name
 * res1: String = "Google"
 *
 * scala RenamedCompany.withName("Lightbend")
 * res2: RenamedCompany = RenamedCompany.TypeSafe
 * ```
 *
 * @param name provides the symbol name.
 */
abstract class AliasedEnumSymbol(name: String) extends EnumSymbol {
  override def toString: String = name
}

/**
 * An [[IndexedEnum]] symbol indexed by an Int 'id'.
 *
 * See [[IndexedEnum]] for usage details.
 *
 * @param id provides the symbol index.
 */
// TODO(jbetz): Ideally we would use a self type here, e.g.:
// abstract class IndexedEnumSymbol[T <: IndexedEnumSymbol[T]] ... with Ordered[T]
// but we've run into https://issues.scala-lang.org/browse/SI-8541 so in the interest
// of stability, we've loosened up our type precision, at least temporarily.
abstract class IndexedEnumSymbol(val id: Int)
  extends EnumSymbol with Ordered[IndexedEnumSymbol] {

  def compare(that: IndexedEnumSymbol): Int = {
    this.id.compareTo(that.id)
  }
}

/**
 * An extension of [[Enum]] supporting [[IndexedEnumSymbol]] symbols.
 *
 * This is indented to provide functionality very close to that of [[scala.Enumeration]] to help
 * ease conversions of types using [[scala.Enumeration]]'s ids.
 *
 * Example usage:
 *
 * ```
 * sealed abstract class Indexed(id: Int) extends IndexedEnumSymbol(id)
 * object Indexed extends IndexedEnum[Indexed] {
 *   case object Zero extends Indexed(0)
 *   case object One extends Indexed(1)
 * }
 * ```
 */
trait IndexedEnum[SymbolType <: IndexedEnumSymbol] extends Enum[SymbolType] {
  lazy val ids: SortedSet[Int] = byId.keys.to[SortedSet]

  /**
   * All the symbols of the enumeration, sorted by id.
   */
  override lazy val symbols: Set[SymbolType] = {
    // TODO(jbetz): Remove ordering here if/when we reestablish self type for IndexedEnumSymbol
    // above.
    // TODO(jbetz): Can we safely make the declared return type SortedSet[SymbolType] here?
    // We get "implicit divergent expansion" if we don't make it SortedSet and developers
    // do simple things like `symbols.map(s => s.id -> s)`.
    implicit val ordering = Ordering.by { s: SymbolType => s.id }
    findSymbols.to[SortedSet]
  }

  private[this] lazy val byId: Map[Int, SymbolType] = {
    symbols.toSet[SymbolType].map { s =>
      s.id -> s
    }.toMap
  }

  def apply(id: Int) = {
    byId(id)
  }
}

/**
 * Both a [[IndexedEnumSymbol]] and a [[AliasedEnumSymbol]]. Should be used in conjunction with
 * [[IndexedEnum]].
 *
 * Example usage:
 *
 * ```
 * sealed abstract class AliasedIndexed(id: Int, name: String)
 *   extends AliasedIndexedEnumSymbol(id, name)
 *
 * object AliasedIndexed extends IndexedEnum[AliasedIndexed] {
 *   case object Zero extends AliasedIndexed(0, "zero")
 *   case object One extends AliasedIndexed(1, "one")
 * }
 * ```
 *
 * @param id provides the symbol index.
 * @param name provides the symbol name
 */
abstract class AliasedIndexedEnumSymbol(id: Int, name: String)
  extends IndexedEnumSymbol(id) {
  override def toString: String = name
}
