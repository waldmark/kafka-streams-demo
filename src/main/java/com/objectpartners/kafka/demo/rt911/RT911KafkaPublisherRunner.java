package com.objectpartners.kafka.demo.rt911;

import com.objectpartners.kafka.demo.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RT911KafkaPublisherRunner {

    private final static Logger logger = LoggerFactory.getLogger(RT911KafkaPublisherRunner.class);

    private String dataFileName = KafkaConfig.DATA_FILE;

    @Autowired
    RT911Producer producer;

    public RT911KafkaPublisherRunner() {
    }

    public void runDemo() {
        logger.info("this demo requires ZooKeeper and Kafka to be up and running " +
                    "and the topic " + KafkaConfig.TOPIC + " should be available");

        producer.setDataFileName(dataFileName);

        logger.info("running with " + dataFileName + " as input data to be published to Kafka on topic " +
                        KafkaConfig.TOPIC);

        Thread producerThread = new Thread(producer);

        logger.info("starting producer thread....");
        producerThread.start();

        try {
            producerThread.join();
            logger.info("producer complete");
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }

        logger.info("kafka rt911 publisher completed");
    }

    public void setDataFileName(String dataFileName) {
        this.dataFileName = dataFileName;
    }
}
