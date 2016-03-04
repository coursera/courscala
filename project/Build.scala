/*
 Copyright 2016 Coursera Inc.

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

import sbt.Build
import sbt.Def
import sbt.Keys._
import sbt._

object Courscala extends Build with OverridablePublishSettings {
  val currentScalaVersion = "2.11.6"
  val supportedScalaVersions = Seq("2.10.5", currentScalaVersion)
  override lazy val settings = super.settings ++ overridePublishSettings ++
    Seq(
      organization := "org.coursera",
      scalaVersion := currentScalaVersion,
      crossScalaVersions := supportedScalaVersions)

  lazy val courscala = (project in file("courscala"))
    .settings(settings: _*)

  lazy val root = (project in file("."))
    .aggregate(courscala)
    .settings(packagedArtifacts := Map.empty) // disable publish for root aggregate module

  override def defaultPublishSettings: Seq[Def.Setting[_]] = Sonatype.Settings
}
