name := """site"""
organization := "com.silenceontheire"

version := "1.0-SNAPSHOT"
lazy val core = (project in file("modules/core")).enablePlugins(PlayJava, PlayEbean)
lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean).dependsOn(core).aggregate(core)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += evolutions
libraryDependencies += "org.flywaydb" %% "flyway-play" % "5.3.1"
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0" % Test
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.15"
libraryDependencies += "org.glassfish.jaxb" % "jaxb-core" % "2.3.0.1"
libraryDependencies += "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.2"
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.4"
libraryDependencies += "io.swagger.core.v3" % "swagger-jaxrs2" % "2.0.0"
libraryDependencies += "io.swagger.core.v3" % "swagger-jaxrs2-servlet-initializer" % "2.0.0"
testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")
javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-Werror")
playEbeanModels in Compile := Seq("models.*")
playEbeanDebugLevel := 4
playEbeanAgentArgs += ("detect" -> "false")
inConfig(Test)(PlayEbean.scopedSettings)
playEbeanModels in Test := Seq("models.*")