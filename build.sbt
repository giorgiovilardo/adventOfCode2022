ThisBuild / scalaVersion := "2.13.10"
ThisBuild / version := "1.0.0"
ThisBuild / organization := "eu.vilardo"
ThisBuild / organizationName := "aoc2022"

lazy val root = (project in file("."))
  .settings(
    name := "aoc2022",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test
  )

scalacOptions ++= Seq(
  "-encoding",
  "utf8",
  "-feature",
  "-unchecked",
  "-Wdead-code",
  "-Xfatal-warnings",
  "-Xlint",
  "-Ybackend-parallelism",
  "8"
)

wartremoverErrors ++= Warts.unsafe
