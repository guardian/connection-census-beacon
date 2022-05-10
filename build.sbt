name := "connection_census_beacon"
 
version := "1.0" 
      
lazy val `connection_census_beacon` = (project in file(".")).enablePlugins(PlayScala)

      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.13.5"

libraryDependencies ++= Seq( ws , specs2 % Test , guice )

enablePlugins(UniversalPlugin, DebianPlugin, RpmPlugin, SystemdPlugin)

Universal / name := name.value

Rpm / packageName := name.value
Rpm / version := version.value
rpmRelease := sys.props.getOrElse("build.number", "1")
Linux / packageSummary := "Simple upload & download server for connection testing"
Linux / packageDescription := "Simple upload & download server for connection testing"
rpmVendor := "Guardian News & Media"
rpmUrl := Some("https://github.com/guardian/connection-census-beacon")
rpmLicense := Some("GPL")

maintainer := "Andy Gallagher <andy.gallagher@theguardian.com>"
debianPackageDependencies := Seq("openjdk-11-jre-headless")