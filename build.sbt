ThisBuild / scalaVersion := "3.10.0-RC1"
Compile / scalacOptions ++= Seq(
  "-unchecked",
  "-Werror"
)

libraryDependencies ++= Seq(
  "io.bullet" %% "borer-derivation" % "1.18.0"
)
