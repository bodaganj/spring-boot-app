package com.bodaganj.spring.boot.app.kafka.producer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TestKafkaProducer<V> implements AutoCloseable {

   private final KafkaProducer<String, V> producer;
   private final String topic;

   @Override
   public void close() {
      producer.flush();
      producer.close();
   }

   public RecordMetadata send(String key, V value) {
      final ProducerRecord<String, V> record = createRecord(key, value);
      log.info("Sending msg with key: [{}], value: [{}] to topic: {}", key, value, this.topic);
      Future<RecordMetadata> send = producer.send(record);
      try {
         return send.get();
      } catch (InterruptedException | ExecutionException e) {
         log.error("FAILED to send msg... :(");
         throw new RuntimeException(e);
      }
   }

   private ProducerRecord<String, V> createRecord(String key, V value) {
      return new ProducerRecord<>(this.topic, key, value);
   }
}
