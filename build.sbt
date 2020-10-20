lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-scala-quill-example""",
    version := "2.8.x",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      evolutions,
      jdbc,
       "io.getquill" %% "quill-jdbc" % "3.5.3",
      "com.h2database" % "h2" % "1.4.199",
      specs2 % Test,
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
