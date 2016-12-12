package com.objectpartners.kafka.demo.complete;

import org.apache.kafka.streams.processor.AbstractProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConversionProcessor extends AbstractProcessor<String, String> {

    private final static Logger logger = LoggerFactory.getLogger(ConversionProcessor.class);


    @Override
    public void process(String key, String value) {
        String newValue;
        try {
            newValue = "VALUE: " + value;
            context().forward(key,newValue);
            logger.info("\n >>>> CONVERTING >>>> \n");
            logger.debug("\nPROCESSOR: received: <" + key + "," + value
                    + "> \nFORWARDING >>>> <" + key + "," + newValue  + ">\n");
            context().commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("key: " + key + " value: " + value);
        }
    }

}
