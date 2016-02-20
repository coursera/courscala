lazy val commonSettings = Seq(
  organization := "org.coursera",
  version := "0.0.1",
  scalaVersion := "2.11.6")

lazy val courscala = (project in file("courscala"))
  .settings(commonSettings: _*)
