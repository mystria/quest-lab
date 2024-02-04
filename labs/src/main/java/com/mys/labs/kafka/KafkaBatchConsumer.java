package com.mys.labs.kafka;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
@Getter
public class KafkaBatchConsumer {

    private CountDownLatch latch = new CountDownLatch(3);

    private String payload;

    @KafkaListener(topics = "batch-topic", containerFactory = "customKafkaListenerContainerFactory")
    public void receive(ConsumerRecords<?, ?> consumerRecords) {
        log.info("received count='{}'", consumerRecords.count());
        consumerRecords.iterator().forEachRemaining(consumerRecord -> {
            log.info("received payload='{}'", consumerRecord.toString());
            payload = consumerRecord.toString();
        });
        log.info("------");
        latch.countDown();
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }
}
