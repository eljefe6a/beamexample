# Apache Beam Example Code

An example Apache Beam project.

### Description

This example can be used with conference talks and self-study. The base of the examples are taken from Beam's `example` directory. They are modified to use Beam as a dependency in the `pom.xml` instead of being compiled together. The example code is changed to output to local directories.

## How to clone and run

1. Open a terminal window.
1. Run `git clone git@github.com:eljefe6a/beamexample.git`
1. Run `cd beamexample/BeamTutorial`
1. Run `mvn compile`
1. Create local output directory: `mkdir output`
1. Run `mvn compile exec:java -Dexec.mainClass="org.apache.beam.examples.tutorial.game.solution.Exercise1" -Pdirect-runner`
1. Run `cat output/user_score` to verify the program ran correctly and the output file was created.

### Using a Java IDE

1. Follow the [IDE Setup](http://beam.incubator.apache.org/contribute/contribution-guide/#optional-ide-setup) instructions on the Apache Beam Contribution Guide.

## Other Runners

### Apache Flink

1. Follow the first steps from [Flink's Quickstart](https://ci.apache.org/projects/flink/flink-docs-release-1.1/quickstart/setup_quickstart.html) to [download Flink](https://ci.apache.org/projects/flink/flink-docs-release-1.1/quickstart/setup_quickstart.html#download).
1. Create the `output` directory.
1. To run on a JVM-local cluster: `mvn compile exec:java -Dexec.mainClass=org.apache.beam.examples.tutorial.game.solution.Exercise1 -Dexec.args='--runner=FlinkRunner --flinkMaster=[local]'  -Pflink-runner`
1. To run on an out-of-process local cluster (note that the steps below should also work on a real cluster if you have one running):
   1. [Start a local Flink cluster](https://ci.apache.org/projects/flink/flink-docs-release-1.1/quickstart/setup_quickstart.html#start-a-local-flink-cluster).
   1. Navigate to the WebUI (typically [http://localhost:8081](http://localhost:8081)), click [JobManager](http://localhost:8081/#/jobmanager/config), and note the value of `jobmanager.rpc.port`. The default is probably 6123.
   1. Run `mvn package -Pflink-runner` to generate a JAR file. Note the location of the generated JAR (probably `./target/BeamTutorial-bundled-flink.jar`)
   1. Run `mvn -X -e compile exec:java -Dexec.mainClass=org.apache.beam.examples.tutorial.game.solution.Exercise1 -Dexec.args='--runner=FlinkRunner --flinkMaster=localhost:6123 --filesToStage=./target/BeamTutorial-bundled-flink.jar' -Pflink-runner`, replacing the defaults for port and JAR file if they differ.
   1. Check in the [WebUI](http://localhost:8081) to see the job listed.
1. Run `cat output/user_score` to verify the pipeline ran correctly and the output file was created.

### Apache Spark

1. Create the `output` directory.
1. Allow all users (Spark may run as a different user) to write to the `output` directory. `chmod 1777 output`.
1. Change the output file to a fully-qualified path. For example, `this("output/user_score");` to `this("/home/vmuser/output/user_score");`
1. Run `mvn package -Pspark-runner`
1. Run `spark-submit --jars ./target/BeamTutorial-bundled-spark.jar --class org.apache.beam.examples.tutorial.game.solution.Exercise2 --master yarn-client ./target/BeamTutorial-bundled-spark.jar --runner=SparkRunner`


### Apache Apex
The instructions below are general guidelines to use Apex runner as of now. The examples in the this respository won't work as is.

1. Download pre-configured [Sandbox from here](http://www.datatorrent.com/downloads/3.6.0/datatorrent-sandbox-3.6.0-beam.ova). The sandbox has all pre-requisites installed and setup.
1. Download some data for processing. Run `curl http://www.gutenberg.org/cache/epub/1128/pg1128.txt > /tmp/kinglear.txt`
1. Move data to input directory. Run `hdfs dfs -put /tmp/kinglear.txt /tmp/input/`
1. Confirm for  data in the input directory. Run `hdfs dfs -ls /tmp/input/`
1. Make sure the default output directory does not exist. Run `dfs -rm -r -f /tmp/output/`
1. Run `apex`, at the prompt run `launch ~/code/apex-samples/beam-apex-wordcount/target/beam-apex-wordcount-1.0-SNAPSHOT.apa`. To exit from apex prompt, use “exit” or Control-D.
1. You can track progress of the application via [YARN UI - http://localhost:8088](http://localhost:8088) or [Console - http://localhost:9090](http://localhost:9090) on the sandbox.
1. Once the application is finished successfully, check output `hdfs dfs -ls /tmp/output/`.  You should see couple of files in the output `/tmp/output/_SUCCESS` and `/tmp/output/part-r-00000`. The presence of first signifies success of the program. The output of wordcount program is in `part-r-00000` file. You can check output of the program as `hdfs dfs -cat /tmp/output/part-r-00000` .


### Google Cloud Dataflow

1. Follow the steps in either of the [Java quickstarts for Cloud Dataflow](https://cloud.google.com/dataflow/docs/quickstarts) to initialize your Google Cloud setup.
1. [Create a bucket](https://cloud.google.com/storage/docs/creating-buckets) on Google Cloud Storage for staging and output.
1. Run `mvn -X compile exec:java -Dexec.mainClass="org.apache.beam.examples.tutorial.game.solution.Exercise1" -Dexec.args='--runner=DataflowRunner --project=<YOUR-GOOGLE-CLOUD-PROJECT> --gcpTempLocation=gs://<YOUR-BUCKET-NAME> --outputPrefix=gs://<YOUR-BUCKET-NAME>/output/'  -Pdataflow-runner`, after replacing `<YOUR-GCP-PROJECT>` and `<YOUR-BUCKET-NAME>` with the appropriate values.
1. Check the [Cloud Dataflow Console](https://console.cloud.google.com/dataflow) to see the job running.
1. Check the output bucket to see the generated output: `https://console.cloud.google.com/storage/browser/<YOUR-BUCKET-NAME>/`

## Further Reading

* The World Beyond Batch Streaming [Part 1](https://www.oreilly.com/ideas/the-world-beyond-batch-streaming-101) and [Part 2](https://www.oreilly.com/ideas/the-world-beyond-batch-streaming-102)
* [Future-Proof Your Big Data Processing with Apache Beam](http://thenewstack.io/apache-beam-will-make-big-difference-organization/)
* [Future-proof and scale-proof your code](https://www.oreilly.com/ideas/future-proof-and-scale-proof-your-code)
* [Question and Answers with the Apache Beam Team](http://www.jesse-anderson.com/2016/07/question-and-answers-with-the-apache-beam-team/)
