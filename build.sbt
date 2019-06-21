enablePlugins(ScalaJSPlugin)

name := "starcrossed-valkyrie"

version := "0.1"

scalaVersion := "2.11.12"

//JS DEPS
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.7"

libraryDependencies += "com.fbksoft" %%% "pixi-scala-js" % "1.0"

libraryDependencies += "io.suzaku" %%% "boopickle" % "1.3.1"

libraryDependencies ++= Seq(
  "org.java-websocket" % "Java-WebSocket" % "1.4.0",
  "io.suzaku" %% "boopickle" % "1.3.1"
)

