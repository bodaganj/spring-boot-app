package com.bodaganj.spring.boot.app.kafka.consumer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.KafkaConsumer;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class TestConsumerFactory {

   public static <V> TestKafkaConsumer<V> create(TestConsumerProperties<V> properties) {
      return new TestKafkaConsumer<>(createConsumer(properties), properties.getTopic(), properties.getDuration());
   }

   private static <V> KafkaConsumer<String, V> createConsumer(TestConsumerProperties<V> consumerProperties) {
      return new KafkaConsumer<>(consumerProperties.getConsumerProperties());
   }
}
