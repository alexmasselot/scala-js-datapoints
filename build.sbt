name := "datapoints"

// Comment to get more information during initialization
logLevel in test := Level.Info

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.1"

seq(webSettings :_*)

classpathTypes ~= (_ + "orbit")

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.0.4",
  "org.scalatra" %% "scalatra-scalate" % "2.0.4",
  "org.scalatra" %% "scalatra-specs2" % "2.0.4" % "test",
  "org.scalatra" %% "scalatra-lift-json" % "2.0.4",
    "net.liftweb" %% "lift-json" % "2.4",
  "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "7.6.0.v20120127" % "container",
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
   "org.scalatest" %% "scalatest" % "2.0.M5" % "test",
   "commons-codec" % "commons-codec" % "1.7",
   "org.slf4j" % "slf4j-api" % "1.6.4",
   "ch.qos.logback" % "logback-classic" % "1.0.6"
    )

testOptions in Test <+= (target in Test) map {
  t => Tests.Argument(TestFrameworks.ScalaTest, "junitxml(directory=\"%s\")" format (t / "test-reports"))
}

resourceDirectory in Compile <<= baseDirectory { _ / "src/main/resources" }
