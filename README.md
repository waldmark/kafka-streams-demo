# kafka-demo

Example code that reads a gzip of 911 call data and publishes the call data to a Kafka topic.

To build executable jar, run from a terminal:

$ gradle clean build
$ gradle shadowJar

To run the 911 call producer, you must have:

1. Kafka (version 0.10.0.1) installed
2. Started Kafka (and Zookeeper)

then, from a terminal

$ java -jar kafka-demo-0.1-all.jar [path to gzip of 911 calls]

if path is unspecified, the code will use the large chronological file located at:

src/main/resources/Seattle_Real_Time_Fire_911_Calls_Chrono.csv.gz
