ThisBuild / version := "0.1.0-SNAPSHOT"

Compile / mainClass := Some("com.davidsan.pekkohttp")

enablePlugins(ScalafmtPlugin)

// Opcional: formatear automáticamente al compilar
scalafmtOnCompile := true

resolvers += "Apache Snapshots" at "https://repository.apache.org/content/repositories/snapshots/"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    inThisBuild(List(scalaVersion := "2.13.14")),
    name := "Pekko HTTP Scala Skeleton",
    libraryDependencies ++= {
      val pekkoVersion      = "1.0.2"
      val pekkoHttpVersion  = "1.0.1"
      val logbackV          = "1.5.6"
      Seq(
        "org.apache.pekko"      %% "pekko-http"                % pekkoHttpVersion,
        "org.apache.pekko"      %% "pekko-http-spray-json"     % pekkoHttpVersion,
        "org.apache.pekko"      %% "pekko-actor-typed"         % pekkoVersion,
        "org.apache.pekko"      %% "pekko-stream"              % pekkoVersion,
        "org.apache.pekko"      %% "pekko-slf4j"               % pekkoVersion,
        "ch.qos.logback"        %  "logback-classic"           % logbackV,
      )
    }
  )
