package com.objectpartners.kafka.demo.complete;

import com.objectpartners.kafka.demo.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
class Producer implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    KafkaProducer<String, String> kafkaProducer;

    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // continue
            }
            Date now = new Date();
            String ts = now.toInstant().toString();
            kafkaProducer.send(new ProducerRecord<>(KafkaConfig.TOPIC, Integer.toString(i), ts));
            logger.info("sent <" + i + "," + ts + "> to  topic " + KafkaConfig.TOPIC);
        }
        kafkaProducer.close();
    }
}
