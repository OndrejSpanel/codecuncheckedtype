ThisBuild / scalaVersion := "3.10.0-RC1"
Compile / scalacOptions ++= Seq(
  "-unchecked",
  "-Werror"
)

Compile / sources := Seq(
  baseDirectory.value / "shared/src/main/scala/net/gamatron/procas/anim/definitions.scala",
  baseDirectory.value / "shared/src/main/scala/net/gamatron/json/Schema.scala"
)

libraryDependencies ++= Seq(
  "io.bullet" %% "borer-derivation" % "1.18.0"
)
