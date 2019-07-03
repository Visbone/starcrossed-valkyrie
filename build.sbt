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
        "io.suzaku" %%% "boopickle" % "1.3.1",
        "com.chuusai" %%% "shapeless" % "2.3.3"
      )
    ))
    .jsSettings(
      libraryDependencies ++= Seq(
        "org.scala-js" %%% "scalajs-dom" % "0.9.7"
    )) // defined in sbt-scalajs-crossproject
    .jvmSettings(
      libraryDependencies ++= Seq(
        "org.java-websocket" % "Java-WebSocket" % "1.4.0"
      )
    )
    //.nativeSettings(/* ... */) // defined in sbt-scala-native

// Optional in sbt 1.x (mandatory in sbt 0.13.x)
lazy val valkyrieJS     = valkyrie.js
lazy val valkyrieJVM    = valkyrie.jvm
lazy val valkyrieNative = valkyrie.native



/*
lazy val global = project
  .in(file("."))
  //.settings(settings)
  //.disablePlugins(AssemblyPlugin)
  .aggregate(
    shared,
    jvm,
    js,
    native
  )



lazy val shared = project
  .settings(
    name := "shared",
    libraryDependencies ++= commonDependencies
  ).enablePlugins(ScalaJSPlugin)


lazy val jvm = project
  .settings(
    name := "jvm",
    libraryDependencies ++= commonDependencies ++ Seq(
      "org.java-websocket" % "Java-WebSocket" % "1.4.0"
    )
  )
  .dependsOn(
    shared
  )

lazy val native = project
  .settings(
    name := "native",
    libraryDependencies ++= commonDependencies ++ Seq(
    )
  )
  .dependsOn(
    shared
  )

lazy val js = project
  .settings(
    name := "js",
    libraryDependencies ++= commonDependencies ++ Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.7"
      ,"io.suzaku" %%% "boopickle" % "1.3.1"
      ,"com.chuusai" %%% "shapeless" % "2.3.3"


      //,"com.nativelibs4java" %% "scalaxy-streams" % "0.3.4" % "provided"
    )
  )
  .dependsOn(
    shared
  ).enablePlugins(ScalaJSPlugin)
*/

/*
enablePlugins(ScalaJSPlugin)

name := "starcrossed-valkyrie"

version := "0.1"

scalaVersion := "2.11.12"

//JS DEPS
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.7"

libraryDependencies += "com.fbksoft" %%% "pixi-scala-js" % "1.0"

libraryDependencies += "io.suzaku" %%% "boopickle" % "1.3.1"

libraryDependencies += "com.chuusai" %%% "shapeless" % "2.3.3"

libraryDependencies ++= Seq(
  "org.java-websocket" % "Java-WebSocket" % "1.4.0",
  "io.suzaku" %% "boopickle" % "1.3.1",
  "com.chuusai" %% "shapeless" % "2.3.3"
)

*/