package com.bodaganj.spring.boot.app.kafka.producer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class TestProducerFactory {

   public static <V> TestKafkaProducer<V> create(TestProducerProperties<V> properties) {
      return new TestKafkaProducer<>(createProducer(properties), properties.getTopic());
   }

   private static <V> KafkaProducer<String, V> createProducer(TestProducerProperties<V> producerProperties) {
      return new KafkaProducer<>(producerProperties.getProducerProperties());
   }
}
