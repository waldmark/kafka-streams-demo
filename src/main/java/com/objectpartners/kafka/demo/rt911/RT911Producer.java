package com.objectpartners.kafka.demo.rt911;

import com.objectpartners.kafka.demo.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.zip.GZIPInputStream;

@Component
class RT911Producer implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(RT911Producer.class);

    private String dataFileName = "Seattle_Real_Time_Fire_911_Calls_Chrono.csv.gz";

    @Autowired
    KafkaProducer<String, String> kafkaProducer;

    @Override
    public void run() {

        try {
            final InputStream is = RT911Producer.class.getResourceAsStream("/"+dataFileName);
            final BufferedInputStream bis =  new BufferedInputStream(is);
            final GZIPInputStream iis = new GZIPInputStream(bis);
            final InputStreamReader gzipReader = new InputStreamReader(iis);
            final BufferedReader br = new BufferedReader(gzipReader);
            br.readLine(); // skip header or first line

            logger.info("publishing to Kafka topic: " + KafkaConfig.TOPIC);

            String line;
            while((line = br.readLine()) != null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // continue
                }
                Date now = new Date();
                String ts = now.toInstant().toString();
                kafkaProducer.send(new ProducerRecord<>(KafkaConfig.TOPIC, ts, line));
                logger.info("published <" + ts + "," + line + ">");

            }
            logger.info("ALL INPUT PROCESSED");
            br.close();
            iis.close();
            kafkaProducer.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public void setDataFileName(String dataFileName) {
        this.dataFileName = dataFileName;
    }
}
