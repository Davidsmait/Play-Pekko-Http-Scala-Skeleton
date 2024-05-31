import sbt.*
import Keys.*
import play.sbt.PlayImport.{guice, specs2}

object Common {
  val pekkoVersion      = "1.0.2"
  val pekkoHttpVersion  = "1.0.1"
  val logbackV          = "1.5.6"


  val commonDependencies: Seq[ModuleID] = Seq(
    "org.apache.pekko"      %% "pekko-http"                % pekkoHttpVersion,
    "org.apache.pekko"      %% "pekko-http-spray-json"     % pekkoHttpVersion,
    "org.apache.pekko"      %% "pekko-actor-typed"         % pekkoVersion,
    "org.apache.pekko"      %% "pekko-stream"              % pekkoVersion,
    "org.apache.pekko"      %% "pekko-slf4j"               % pekkoVersion,
    "ch.qos.logback"        %  "logback-classic"           % logbackV,
  )

  val settings: Seq[Setting[_]] = Seq(
    libraryDependencies ++= commonDependencies,
    libraryDependencies += guice,
    scalaVersion := "2.13.14",
  )

  val playSettings: Seq[sbt.Setting[_]] = settings ++ Seq(
    libraryDependencies += specs2 % Test,
    resolvers += Resolver.ApacheMavenSnapshotsRepo
  )
}
