name := "connection_census_beacon"
 
version := "1.0" 
      
lazy val `connection_census_beacon` = (project in file(".")).enablePlugins(PlayScala)

      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.13.5"

libraryDependencies ++= Seq( ws , specs2 % Test , guice )