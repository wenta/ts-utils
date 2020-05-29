name := "ts-utils"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  "io.github.carldata" %% "timeseries" % "0.7.0",
  // Test dependencies
  "org.scalatest" %% "scalatest" % "3.1.2" % "test"
)