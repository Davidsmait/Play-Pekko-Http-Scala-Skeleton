ThisBuild / version := "0.1.0-SNAPSHOT"

Compile / mainClass := Some("com.skeleton.pekkohttp")

enablePlugins(ScalafmtPlugin)

// Opcional: formatear automáticamente al compilar
scalafmtOnCompile := true

resolvers += "Apache Snapshots" at "https://repository.apache.org/content/repositories/snapshots/"

Common.playSettings

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    Common.settings,
    name := "Pekko HTTP Scala Skeleton",
  )
