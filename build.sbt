ThisBuild / scalaVersion     := "3.5.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "aoc2024",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"      % "2.1.6",
      "dev.zio" %% "zio-test" % "2.1.4"
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
