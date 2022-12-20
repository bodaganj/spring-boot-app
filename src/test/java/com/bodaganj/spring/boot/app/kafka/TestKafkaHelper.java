package com.bodaganj.spring.boot.app.kafka;

import com.bodaganj.spring.boot.app.kafka.consumer.TestKafkaConsumer;
import com.bodaganj.spring.boot.app.kafka.producer.TestKafkaProducer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.awaitility.Awaitility;

import java.time.Duration;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class TestKafkaHelper<V> implements AutoCloseable {

   private final TestKafkaConsumer<V> consumer;
   private final TestKafkaProducer<V> producer;
   private final Duration duration = Duration.ofSeconds(2);

   public void sendMessageAndWaitToAppear(String key, V value) {
      RecordMetadata send = producer.send(key, value);
      waitForMessage(key, value, send.partition());
   }

   private void waitForMessage(String key, V value, int partition) {
      Awaitility.await()
                .atMost(duration)
                .untilAsserted(() -> {
                   List<KafkaMessage<V>> messages = consumer.consumeMessage(partition);
                   assert anyKey(messages, key);
                   assert anyValue(messages, value);
                });
   }

   private boolean anyKey(List<KafkaMessage<V>> messages, String key) {
      return messages.stream().anyMatch(kafkaMessage -> kafkaMessage.key.equals(key));
   }

   private boolean anyValue(List<KafkaMessage<V>> messages, V value) {
      return messages.stream().anyMatch(kafkaMessage -> {
         if (kafkaMessage.value == null) {
            return value == null;
         } else {
            return kafkaMessage.value.equals(value);
         }
      });
   }

   @Override
   public void close() {
      consumer.close();
      producer.close();
   }
}
