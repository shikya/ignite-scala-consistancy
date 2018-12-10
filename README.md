# Ignite Scala Consistancy
Ignite and Scala integration test and consistancy issue

![Ignite Error](https://github.com/shikya/ignite-scala-consistancy/raw/master/images/ignite%20error.PNG "Ignite Error")

## Requirements

+Scala 2.12.2

+sbt

+ignite binaries 2.6.0

+IDE prefred: Intellij IDEA


## To reproduce,

Compile the jar of ignite-config.
Put the jar in ignite lib folder.
The contains a lifecycle event which will start the cluster once node count becomes 3.

Replace the storage paths in ignite configurations
1.xml, 2.xml, and 3.xml
Run the 3 nodes using the configuration above.
For windows, you can run "start %IGNITE_HOME%\bin\ignite.bat "C:\personal-repositories\ignite-data-consistancy\ignite-runner\1.xml"" in console. to start a single node.
To start all node run "runall.bat"
Once the third cluster is up, The cluster will start.

Compile and run the ignite-data-consistency.
Log  "[START] injecting fake data." represents the start of fake data in ignite cluster.

"[START] Checking records" represents that data check has been started.
Now kill one ignite node. Prefer to kill the node which has 3.xml.
This will make the ignite node count to 2.
Now delete the storage data of that node to reset it.
Start ignite node with 3.xml. This will make the ignite nodes count again to 3.

In the background, The latest node will start to pull data from other to nodes. And at a certain time the program will throw "[ERROR] from ignite ${size} vs actual ${counter} " error.
Here size is the actual size which should be present in the node
the counter is the actual size which is present in the node.


## TODOs

- [x] ~~Starter Project.~~
- [x] ~~Jar for ignite.~~
- [X] ~~Code for scala.~~
- [x] ~~Windows Runner.~~
- [ ] Documentation.
- [ ] Linux/Mac Runner.
