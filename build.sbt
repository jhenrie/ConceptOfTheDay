lazy val commonSettings = Seq(
  organization := "org.jhenrie",
  version := "0.1.0",
  scalaVersion := "2.11.8"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "ConceptOfTheDay",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.0.0",
      "org.scalatest" %% "scalatest" % "3.0.0" % "test"
      )
  )
