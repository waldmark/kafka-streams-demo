package com.objectpartners.kafka.demo.complete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaRunner {

    private final static Logger logger = LoggerFactory.getLogger(KafkaRunner.class);

    @Autowired
    Producer producer;

    @Autowired
    Consumer consumer;

    @Autowired
    StreamProcessor kafkaStreamProcessor;

    public void runDemo() {
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        Thread processorThread = new Thread(kafkaStreamProcessor);

        logger.info("starting producer and consumer threads....");
        processorThread.start();
        consumerThread.start();
        producerThread.start();

        try {
            logger.info("sleep for 10 seconds so the producer and consumer can work....");
            Thread.sleep(10000);
            logger.info("sleep complete");
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        } finally {
            kafkaStreamProcessor.stop();
            consumer.stop();
        }
        logger.info("demo run completed\n");
    }

}
