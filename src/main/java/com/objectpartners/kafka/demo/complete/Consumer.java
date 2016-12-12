package com.objectpartners.kafka.demo.complete;

import com.objectpartners.kafka.demo.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
class Consumer implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final AtomicBoolean running = new AtomicBoolean(false);

    @Autowired
    KafkaConsumer<String, String> kafkaConsumer;

    @Override
    public void run() {
        kafkaConsumer.subscribe(Collections.singletonList(KafkaConfig.TOPIC));
        running.set(true);

        try {
            while (running.get()) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
                for (ConsumerRecord<String, String> record : records)
                    logger.info("received: record = " + record.offset() + " value = " + record.value());
            }
            kafkaConsumer.close();
        } catch (WakeupException e) {
            running.set(false);
        }
    }

    void stop() {
        logger.info("=== stopping consumer ===");
        kafkaConsumer.wakeup();
    }
}