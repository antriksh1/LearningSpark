name := "learning-spark-mini-example-wordcount"

version := "0.0.1"

scalaVersion := "2.10.2"

//additional libraries
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.2.0" % "provided"
)