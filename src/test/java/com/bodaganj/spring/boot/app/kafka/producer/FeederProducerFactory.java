package com.bodaganj.spring.boot.app.kafka.producer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class FeederProducerFactory {

   public static <V> FeederKafkaProducer<V> create(FeederProducerProperties<V> properties) {
      return new FeederKafkaProducer<>(createProducer(properties), properties.getTopic());
   }

   private static <V> KafkaProducer<String, V> createProducer(FeederProducerProperties<V> producerProperties) {
      return new KafkaProducer<>(producerProperties.getProducerProperties());
   }
}
