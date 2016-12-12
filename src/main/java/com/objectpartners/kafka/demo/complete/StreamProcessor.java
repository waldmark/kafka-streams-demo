package com.objectpartners.kafka.demo.complete;

import org.apache.kafka.streams.KafkaStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StreamProcessor implements Runnable {
    private final static Logger logger = LoggerFactory.getLogger(StreamProcessor.class);
    private volatile boolean running = false;

    @Autowired
    private KafkaStreams streams;

    @Override
    public void run() {
        running = true;
        streams.start();

        while (running) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }
        streams.close();
        streams.cleanUp();
    }

    void stop() {
        logger.info("=== stopping processor ===");
        running = false;
    }
}
