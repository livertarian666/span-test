import sbt.Keys.libraryDependencies

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.13"

libraryDependencies += "org.scalatest" %% "scalatest-wordspec" % "3.2.13" % "test"

lazy val root = (project in file("."))
  .settings(
    name := "SpanTest",
  )
