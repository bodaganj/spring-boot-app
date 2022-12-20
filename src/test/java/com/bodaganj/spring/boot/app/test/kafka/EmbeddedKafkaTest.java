package com.bodaganj.spring.boot.app.test.kafka;

import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@DirtiesContext
@EmbeddedKafka(
   brokerPropertiesLocation = "classpath:test-broker.properties",
   topics = "${spring.kafka.topic:test_topic}",
   partitions = EmbeddedKafkaTest.PARTITIONS_NUMBER
)
@TestPropertySource(
   properties = {
      "spring.kafka.bootstrap-servers=localhost:9092"
   }
)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmbeddedKafkaTest {

   int PARTITIONS_NUMBER = 4;
}
