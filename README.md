# Apache Beam Example Code

An example Apache Beam project.

### Description

This example can be used with conference talks and self-study. The base of the examples are taken from Beam's `example` directory. They are modified to use Beam as a dependency in the `pom.xml` instead of being compiled together. The example code is changed to output to local directories.

### How to clone and run

1. Open a terminal window.
1. Run `git clone git@github.com:eljefe6a/beamexample.git`
1. Run `cd beamexample/BeamTutorial`
1. Run `mvn compile`
1. Run `mvn exec:java -Dexec.mainClass="org.apache.beam.examples.tutorial.game.solution.Exercise1"`
1. Run `cat output/user_score` to verify the program ran correctly and the output file was created.

### Using Eclipse

1. Run `mvn eclipse:eclipse`
1. Import the project.

### Using IntelliJ

1. Import the Maven project.
