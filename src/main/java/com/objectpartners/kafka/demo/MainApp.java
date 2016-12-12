package com.objectpartners.kafka.demo;

import com.objectpartners.kafka.demo.complete.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);

    @Autowired
    private KafkaRunner kafkaRunner;

    @Override
    public void run(String... args) {
        logger.info("running Kafka Streams demo");
        kafkaRunner.runDemo();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApp.class, args).close();
    }
}
