import Dependencies._

val scala212 = "2.12.19"
val scala213 = "2.13.12"

ThisBuild / organization := "org.coursera"
ThisBuild / scalaVersion := scala212
ThisBuild / crossScalaVersions := Seq(scala212, scala213)

lazy val root = (project in file("."))
  .aggregate(courscala)
  .settings(
    publish / skip := true,
    packagedArtifacts := Map.empty
  )

lazy val courscala = (project in file("courscala"))
  .settings(OverridablePublishSettings.settings: _*)
