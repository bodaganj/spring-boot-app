package com.bodaganj.spring.boot.app.kafka.consumer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.KafkaConsumer;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class FeederConsumerFactory {

   public static <V> FeederKafkaConsumer<V> create(FeederConsumerProperties<V> properties) {
      return new FeederKafkaConsumer<>(createConsumer(properties), properties.getTopic(), properties.getDuration());
   }

   private static <V> KafkaConsumer<String, V> createConsumer(FeederConsumerProperties<V> consumerProperties) {
      return new KafkaConsumer<>(consumerProperties.getConsumerProperties());
   }
}
