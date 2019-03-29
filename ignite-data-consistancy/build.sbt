name := "ignite-data-consistancy"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.apache.ignite" % "ignite-scalar"         % "2.7.0",
  "org.apache.ignite" % "ignite-indexing"       % "2.7.0",
  "org.apache.ignite" % "ignite-visor-console"  % "2.7.0",
  "org.apache.ignite" % "ignite-spring"         % "2.7.0",
  "org.apache.ignite" % "ignite-kubernetes"     % "2.7.0",
  "org.apache.ignite" % "ignite-slf4j"          % "2.7.0",
)
