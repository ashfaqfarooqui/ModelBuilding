name := "Model Building"

version := "2.0-alpha"
ThisBuild / scalaVersion := "2.12.12"
                                                                       //

lazy val global = project
  .in(file("."))
  .settings(settings)
  .aggregate(midesLib, models, mides)


lazy val midesLib = (project in file("MidesLib")).settings(
  settings,
  deps,
  unmanagedJars in Compile ++= Seq(
    new java.io.File("lib/Supremica.jar"),
    new java.io.File("lib/SupremicaLib.jar")
  ).classpath,
  unmanagedJars in Runtime ++= Seq(
    new java.io.File("lib/Supremica.Jar"),
    new java.io.File("lib/SupremicaLib.jar")
  ).classpath
)
lazy val mides =
  (project in file("Mides")).settings(settings, deps).dependsOn(midesLib, models)
lazy val models = (project in file("Models")).settings(settings, deps).dependsOn(midesLib)



val deps = libraryDependencies ++= Seq(
  "org.scala-graph"         %% "graph-core"               % "1.13.1",
  "org.scala-graph"         %% "graph-dot"                % "1.13.0",
  "org.eclipse.milo"         % "sdk-client"               % "0.6.9",
  "org.eclipse.milo"         % "stack-client"             % "0.6.9",
  "com.github.nscala-time"  %% "nscala-time"              % "2.32.0",
  "org.clapper"             %% "grizzled-slf4j"           % "1.3.4",
  "org.slf4j"                % "slf4j-api"                % "1.7.25",
  "org.apache.logging.log4j" % "log4j-slf4j-impl"         % "2.12.1",
  "org.apache.logging.log4j" % "log4j-api"                % "2.12.1",
  "org.apache.logging.log4j" % "log4j-core"               % "2.12.1",
  "com.github.tototoshi"    %% "scala-csv"                % "1.3.10",
  "com.github.andr83"       %% "scalaconfig"              % "0.7",
  "com.typesafe"             % "config"                   % "1.4.0",
  "com.stephenn"            %% "scala-datatable"          % "0.9.0",
  "org.scalactic"           %% "scalactic"                % "3.2.15",
  "org.scalatest"           %% "scalatest"                % "3.2.15" % "test",
  "org.scala-lang.modules"  %% "scala-parser-combinators" % "1.1.2"
)

lazy val settings =
  commonSettings ++
    scalafmtSettings

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
)
lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true
  )

lazy val commonSettings = Seq(
  scalacOptions ++= compilerOptions,
  resolvers ++= Seq(
    "Sonatype OSS Snapshots" at "https://oss.sonatype.org/Releases",
    "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/",
    "sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
    Resolver.mavenLocal
  )
)
