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

import sbt.Keys._
import sbt._

/**
 * Supports publication to alternate repos via system properties. Example usage:
 *
 * ```
 * sbt \
 *   -Dsbt.override.publish.repos.release=https://<alternate-repo>/general \
 *   -Dsbt.override.publish.repos.snapshot=https://<alternate-repo>/general-snapshots \
 *   "set credentials in Global += Credentials(\"<path-to-repo-credential-file>\")" "fullpublish"
 * ```
 */
object OverridablePublishSettings {
  private val releaseKey = "sbt.override.publish.repos.release"
  private val snapshotKey = "sbt.override.publish.repos.snapshot"

  def settings: Seq[Def.Setting[_]] = {
    val overrideRelease = Option(System.getProperty(releaseKey))
    val overrideSnapshot = Option(System.getProperty(snapshotKey))

    assert(
      overrideRelease.isDefined == overrideSnapshot.isDefined,
      s"If overriding publish repos, both $releaseKey and $snapshotKey must be provided")

    (overrideRelease, overrideSnapshot) match {
      case (Some(release), Some(snapshot)) =>
        Seq(publishTo := Some(
          if (version.value.trim.endsWith("SNAPSHOT")) "snapshots" at snapshot
          else "releases" at release
        ))
      case _ => Sonatype.Settings
    }
  }
}
