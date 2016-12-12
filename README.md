# kafka-streams-demo

Demo of Kafka streams including a message producer, a Kafka stream processor, and a message Consumer

### To run the demo, you must have:

1. Kafka (version 0.10.0.1) installed
2. Started Kafka (and Zookeeper)

#### Assuming Kafka was installed at ~/kafka_2.11-0.10.0.1
   then:
      - run zookeeper (do this first)
      - cd ~/kafka_2.11-0.10.0.1 && ./bin/zookeeper-server-start.sh config/zookeeper.properties
      - run Kafka
      - cd ~/kafka_2.11-0.10.0.1 && ./bin/kafka-server-start.sh config/server.properties

### To build executable jar, run from the project root in a terminal:
$ gradle clean build
$ gradle shadowJar

### To run from the executable (uber) jar:
$ java -jar kafka-demo-0.1-all.jar
