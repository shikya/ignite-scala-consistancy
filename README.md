# Ignite Consistancy Issue

![Ignite Error](https://github.com/shikya/ignite-scala-consistancy/raw/master/images/ignite%20error.PNG "Ignite Error")

## Requirements

+ Scala 2.12.2

+ sbt

+ ignite binaries 2.7.0

+ IDE prefred: Intellij IDEA


## To Reproduce

1. Compile the jar of ignite-config. Or Copy jar from `ignite-scala-consistancy\ignite-config\out\artifacts\ignite_config_jar`. This contains a lifecycle event which will start the cluster once node count becomes 3.

1. Put the jar in ignite lib folder.

1. Replace the storage paths in ignite configurations

   1.xml, 2.xml, and 3.xml

1. Run the 3 nodes using the configuration above.

   For windows, you can run `start %IGNITE_HOME%\bin\ignite.bat "C:\personal-repositories\ignite-scala-consistancy\ignite-data-consistancy\ignite-runner"` in console. to start a single node.
   
   For linux, run ignite using ignite.sh with the xml files to create cluster of 3 nodes

1. To start all node run "runall.bat"

1. Once the third cluster is up, The cluster will start.

1. Compile and run the ignite-data-consistency.

   Log  `[START] injecting fake data.` represents the start of fake data in ignite cluster.

   `[START] Checking records` represents that data check has been started.

1. Now kill one ignite node. Prefer to kill the node which has 3.xml.

1. This will make the ignite node count to 2.

1. Now delete the storage data of that node to reset it.

1. Start ignite node with 3.xml. This will make the ignite nodes count again to 3.


   In the background, The latest node will start to pull data from other to nodes. And at a certain time the program will throw "[ERROR] from ignite ${size} vs actual ${counter} " error.

   Here size is the actual size which should be present in the node
the counter is the actual size which is present in the node.


## TODOs

- [x] ~~Starter Project.~~
- [x] ~~Jar for ignite.~~
- [X] ~~Code for scala.~~
- [x] ~~Windows Runner.~~
- [x] ~~Documentation.~~
- [ ] Linux/Mac Runner.
