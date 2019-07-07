name := "starcrossed-valkyrie"

version := "0.1"



import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val valkyrie =
// select supported platforms
  crossProject(JSPlatform, JVMPlatform, NativePlatform)
    .crossType(CrossType.Full).in(file(".")) // [Pure, Full, Dummy], default: CrossType.Full
    .settings(Seq(
    scalaVersion := "2.11.12",
    libraryDependencies ++= Seq(
      "io.suzaku" %% "boopickle" % "1.3.1",
      "org.portable-scala" %% "portable-scala-reflect" % "0.1.0"
    )
  ))
    .jsSettings(
      libraryDependencies ++= Seq(
        "org.scala-js" %%% "scalajs-dom" % "0.9.7",
        "io.suzaku" %%% "boopickle" % "1.3.1",
        "org.portable-scala" %%% "portable-scala-reflect" % "0.1.0"

      )) // defined in sbt-scalajs-crossproject
    .jvmSettings(
    libraryDependencies ++= Seq(
      "org.java-websocket" % "Java-WebSocket" % "1.4.0"
    )
  )

//.nativeSettings(/* ... */) // defined in sbt-scala-native

// Optional in sbt 1.x (mandatory in sbt 0.13.x)
lazy val valkyrieJS     = valkyrie.js.enablePlugins(WorkbenchPlugin)

lazy val valkyrieJVM    = valkyrie.jvm
lazy val valkyrieNative = valkyrie.native