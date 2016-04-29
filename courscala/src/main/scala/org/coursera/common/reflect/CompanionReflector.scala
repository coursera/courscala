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

package org.coursera.common.reflect

/**
 * Provides reflection methods for finding Scala companion classes
 * given only Class types.
 */
object CompanionReflector {

  /**
   * Find a companion class given its corresponding Scala class.
   *
   * @throws ClassNotFoundException if the companion class cannot be found.
   */
  def findCompanionClassOfScalaClass[T](clazz: Class[_]): Class[T] = {
    val name = clazz.getName
    Class.forName(
      name + "$",
      true,
      clazz.getClassLoader).asInstanceOf[Class[T]]
  }

  /**
   * Find a Scala class given its corresponding companion class.
   *
   * @throws ClassNotFoundException if the Scala class cannot be found.
   */
  def findScalaClassOfCompanionClass[T](companion: Class[_]): Class[T] = {
    val name = companion.getName
    Class.forName(
      name.dropRight(1),
      true,
      companion.getClassLoader).asInstanceOf[Class[T]]
  }

  /**
   * Find the instance of a companion class given its corresponding scala class.
   *
   * @throws ClassNotFoundException if the companion class cannot be found.
   * @throws NoSuchFieldException if the companion class is not a scala object.
   */
  def findCompanionInstanceOfScalaClass[T](clazz: Class[_]): T = {
    val companionClass = findCompanionClassOfScalaClass(clazz)
    findCompanionInstanceOfCompanionClass(companionClass)
  }

  /**
   * Find the instance of a companion class given the companion class.
   *
   * @throws NoSuchFieldException if the provided class is not a scala object.
   */
  def findCompanionInstanceOfCompanionClass[T](companion: Class[T]): T = {
    companion.getDeclaredField(MODULE_FIELD).get(null).asInstanceOf[T]
  }

  private[this] val MODULE_FIELD = "MODULE$"
}
