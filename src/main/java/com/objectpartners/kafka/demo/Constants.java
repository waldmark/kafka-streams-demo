package com.objectpartners.kafka.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Constants {

    @Value("${kafka.streams.source_topic}")
    private String source_topic;

    @Value(value = "${kafka.streams.sink_topic}")
    private String sink_topic;

    @Value(value = "${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value(value = "${kafka.producer.client.id}")
    private String clientId;

    @Value(value = "${kafka.consumer.group.id}")
    private String groupId;

    @Value(value = "${kafka.streams.application.id}")
    private String applicationId;

    @Value(value = "${kafka.key.serializer}")
    private String keySerializer;

    @Value(value = "${kafka.key.deserializer}")
    private String keyDeSerializer;

    @Value(value = "${kafka.value.serializer}")
    private String valueSerializer;

    @Value(value = "${kafka.value.deserializer}")
    private String valueDeSerializer;

    public Constants() {
    }

    public String getSource_topic() {
        return source_topic;
    }

    public String getSink_topic() {
        return sink_topic;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public String getClientId() {
        return clientId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getKeySerializer() {
        return keySerializer;
    }

    public String getKeyDeSerializer() {
        return keyDeSerializer;
    }

    public String getValueSerializer() {
        return valueSerializer;
    }

    public String getValueDeSerializer() {
        return valueDeSerializer;
    }
}
