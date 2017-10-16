name := "endpointmatch"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test
libraryDependencies += "org.typelevel" %% "cats-core" % "1.0.0-MF"
libraryDependencies += "org.rogach" %% "scallop" % "3.1.0"
libraryDependencies += "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.1.2"
libraryDependencies += "com.typesafe.play" %% "play-ws-standalone-json" % "1.1.2"
        