# Courscala

Core Scala classes used to build Coursera.

## Development

We (at Coursera) are in the process of adding our core Scala libraries to this repository.
Right now it's under active development and we don't guarantee API or binary compatibility yet.

## API Overview

Courscala includes a few packages of utilities. Here's a brief overview.

All packages live under `org.coursera.common`.

### `collection.Enum[T]`

Defines "ADT" based Scala enumerations.

Each enumeration is composed of a sealed type plus case objects for each enum symbol,
enabling exhaustive pattern matching.

Base traits are extended to provide convenience operations similar to those provided
by `scala.Enumeration`.

Example usage:

```scala
sealed trait Color extends EnumSymbol

object Color extends Enum[Color] {
  case object RED extends Color
  case object AMBER extends Color
  case object GREEN extends Color
  case object BLUE extends Color
}
```

```
scala> Color.symbols
res1: Set[Color] = Set(RED, AMBER, GREEN, BLUE)

scala> Color.withName("RED")
res2: Color.RED.type = RED
```

Additional traits such as `IndexedEnum` and `AliasedEnumSymbol` are provided
for cases where symbols need explicit ordering or alternate string names.

See the `Enum` Scaladoc for additional details.

### `concurrent.Futures`

Utilities for working with standard Scala `Future`s.

### `jsonformat`

Utilities for defining and combining Play JSON `Format`s.

### `stringkey`

Immutable model for the canonical string representation of an object along with idiomatic,
composable conversions. Useful for database keys, URLs, and other settings that require stable
serialization.
