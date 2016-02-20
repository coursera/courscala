package org.coursera.reflect

/**
 * Provides reflection methods for looking up and accessing Scala companion classes
 * given only Class types.
 */
object CompanionReflector {

  /**
   * Lookup a companion class given a Scala class.
   */
  def lookupCompanion[T](clazz: Class[_]): Class[T] = {
    val name = clazz.getName
    Class.forName(
      name + "$",
      true,
      clazz.getClassLoader).asInstanceOf[Class[T]]
  }

  /**
   * Lookup a Scala class given a companion class.
   */
  def lookupClass[T](companion: Class[_]): Class[T] = {
    val name = companion.getName
    Class.forName(
      name.substring(0, name.length-1),
      true,
      companion.getClassLoader).asInstanceOf[Class[T]]
  }

  /**
   * Access the instance of a companion class given it's Class.
   */
  def companionInstance[T](companion: Class[T]): T = {
    companion.getDeclaredField(MODULE_FIELD).get(null).asInstanceOf[T]
  }

  private[this] val MODULE_FIELD = "MODULE$"
}
