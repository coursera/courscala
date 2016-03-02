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
import sbt.Keys._
import sbt._

object Sonatype {

  val Settings: Seq[Def.Setting[_]] = Seq(

    publishMavenStyle := true,
    publishTo := {
      val nexus = "https://oss.sonatype.org"
      if (version.value.trim.endsWith("SNAPSHOT")) {
        Some("snapshots" at s"$nexus/content/repositories/snapshots")
      } else {
        Some("releases" at s"$nexus/service/local/staging/deploy/maven2")
      }
    },
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },

    pomExtra := {
      <url>http://github.com/coursera/courier</url>
        <licenses>
          <license>
            <name>Apache 2.0</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:coursera/courscala.git</url>
          <connection>scm:git:git@github.com:coursera/courscala.git</connection>
        </scm>
        <developers>
          <developer>
            <id>jpbetz</id>
            <name>Joe Betz</name>
          </developer>
        </developers>
    }
  )

}
