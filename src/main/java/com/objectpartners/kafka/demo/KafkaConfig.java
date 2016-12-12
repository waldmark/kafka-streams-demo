package com.objectpartners.kafka.demo;

import com.objectpartners.kafka.demo.complete.ConversionProcessor;
import com.objectpartners.kafka.demo.complete.StreamProcessor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    public static final String TOPIC = "demo-topic";
    public static final  String DATA_FILE = "Seattle_Real_Time_Fire_911_Calls_Chrono.csv.gz";

    @Bean
    KafkaProducer<String, String> kafkaProducer() {
        return new KafkaProducer<>(producerProperties());
    }

    private Properties producerProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "demo-client");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    @Bean
    KafkaConsumer<String, String> kafkaConsumer() {
        return new KafkaConsumer<>(consumerProperties());
    }

    private Properties consumerProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "demo-group");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        return props;
    }

    @Bean
    Constants configConstants() {
        return new Constants();
    }

    @Bean
    StreamProcessor kafkaStreamProcessor() {
        return new StreamProcessor();
    }

    @Bean
    KafkaStreams kafkaStreams() {
        Properties props = new Properties();
        props.put("bootstrap.servers", configConstants().getBootstrapServers());
        props.put("application.id", configConstants().getApplicationId());

        StringDeserializer stringDeserializer = new StringDeserializer();
        StringSerializer stringSerializer = new StringSerializer();

        KStreamBuilder builder =  new KStreamBuilder();
        builder.addSource("Source", stringDeserializer, stringDeserializer,  configConstants().getSource_topic())
                .addProcessor("Process", () -> new ConversionProcessor(), "Source")
                .addSink("Sink", configConstants().getSink_topic(), stringSerializer, stringSerializer, "Process");

        return new KafkaStreams(builder, props);
    }

}
