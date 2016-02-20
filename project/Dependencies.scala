import sbt._
import Keys._

object Dependencies {
  object Scala {
    val version = "2.11.6"
    val scalaReflect = "org.scala-lang" % "scala-reflect" % version
  }

  object Scalatest {
    val version = "2.2.3"
    val scalatest = "org.scalatest" %% "scalatest" % version % "test"
  }

  object JUnit {
    val version = "4.11"
    val junit = "junit" % "junit" % version % "test"
  }
}
