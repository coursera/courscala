import Dependencies._

name := "courscala"

libraryDependencies ++= Seq(
  PlayJson.playJson,
  PlayJsonJoda.playJsonJoda,
  JodaTime.jodaTime,
  JodaConvert.jodaConvert,
  JUnitInterface.junitInterface,
  Scalatest.scalatest,
  ScalatestPlusJUnit.scalatestPlusJunit,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value)

testFrameworks := Seq(sbt.TestFrameworks.JUnit)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-q", "-a")
