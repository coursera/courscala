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

import org.junit.Test
import org.scalatestplus.junit.AssertionsForJUnit

class CompanionReflectorTest extends AssertionsForJUnit {

  import CompanionReflectorTest._

  /**
   * findCompanionClassOfScalaClass: given the Scala class (not the companion `$` class),
   * locate the companion object's class. This path was previously 0% covered.
   */
  @Test
  def findCompanionClassOfScalaClass_returnsCompanionClass(): Unit = {
    val companionClass =
      CompanionReflector.findCompanionClassOfScalaClass[Widget.type](classOf[Widget])
    // The companion object singleton lives in Widget$
    assert(companionClass.getName.endsWith("Widget$"))
  }

  /**
   * findCompanionInstanceOfScalaClass: given the Scala class, find and return the
   * live companion object instance. This path was previously 0% covered.
   */
  @Test
  def findCompanionInstanceOfScalaClass_returnsInstance(): Unit = {
    val instance =
      CompanionReflector.findCompanionInstanceOfScalaClass[Widget.type](classOf[Widget])
    // The returned instance must be the same singleton as Widget
    assertResult(Widget)(instance)
  }

  /**
   * Verify that findCompanionClassOfScalaClass throws when no companion exists.
   */
  @Test
  def findCompanionClassOfScalaClass_noCompanion_throws(): Unit = {
    intercept[ClassNotFoundException] {
      CompanionReflector.findCompanionClassOfScalaClass[Any](classOf[NoCompanion])
    }
  }
}

object CompanionReflectorTest {

  /** A plain case class with a companion object — the companion class is Widget$. */
  case class Widget(id: Int)

  object Widget {
    val label: String = "widget"
  }

  /** A class with no companion object — used to test the ClassNotFoundException path. */
  class NoCompanion(val x: Int)
}
