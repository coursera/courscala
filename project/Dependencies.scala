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

import sbt._
import Keys._

object Dependencies {
  object PlayJson {
    val version = "2.6.7"
    val playJson = "com.typesafe.play" %% "play-json" % version
  }

  object PlayJsonJoda {
    val version = "2.6.7"
    val playJsonJoda = "com.typesafe.play" %% "play-json-joda" % version
  }

  object JodaTime {
    val version = "2.9.9"
    val jodaTime = "joda-time" % "joda-time" % version
  }

  object JodaConvert {
    val version = "1.9.2"
    val jodaConvert = "org.joda" % "joda-convert" % version
  }

  object Scalatest {
    val version = "3.0.4"
    val scalatest = "org.scalatest" %% "scalatest" % version % "test"
  }

  object JUnitInterface {
    val version = "0.11"
    val junitInterface = "com.novocode" % "junit-interface" % version % "test"
  }
}
