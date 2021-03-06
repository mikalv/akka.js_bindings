
lazy val akkajs =
  project
    .in(file("."))
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name := "akkajs",
      organization := "org.akka-js",
      scalaVersion := "2.12.2",
      scalacOptions := Seq("-feature", "-language:_", "-deprecation"),
      libraryDependencies ++= Seq(
        "org.akka-js" %%% "akkajsactor" % "1.2.5.4"
      ),
      scalaJSUseMainModuleInitializer in Global := true,
      scalaJSModuleKind := ModuleKind.CommonJSModule,
      skip in packageJSDependencies := false,
      deploy := {
        val opt = (fullOptJS in Compile).value.data
        val target = baseDirectory.value / "lib" / "akkajs.js"

        IO.copy(Seq((opt -> target)), true)
      }
    )

val deploy: TaskKey[Unit] = taskKey[Unit]("akka.js binaries to bin folder")
