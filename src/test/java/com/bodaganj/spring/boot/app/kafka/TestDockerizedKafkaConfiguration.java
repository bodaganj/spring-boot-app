package com.bodaganj.spring.boot.app.kafka;

import com.bodaganj.spring.boot.app.dto.PersonDTO;
import com.bodaganj.spring.boot.app.kafka.consumer.TestConsumerFactory;
import com.bodaganj.spring.boot.app.kafka.consumer.TestConsumerProperties;
import com.bodaganj.spring.boot.app.kafka.producer.TestProducerFactory;
import com.bodaganj.spring.boot.app.kafka.producer.TestProducerProperties;
import com.bodaganj.spring.boot.app.kafka.serialize.PersonDeserializer;
import com.bodaganj.spring.boot.app.kafka.serialize.PersonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestDockerizedKafkaConfiguration {

   @Bean(destroyMethod = "close")
   TestKafkaHelper<PersonDTO> dockerizedKafkaHelper(
      @Value("${spring.docker.kafka.brokers}") String brokers,
      @Value("${spring.kafka.topic}") String topic
   ) {
      return new TestKafkaHelper<>(
         TestConsumerFactory.create(
            TestConsumerProperties.<PersonDTO>builder()
                                  .brokers(brokers)
                                  .deserializer(new PersonDeserializer())
                                  .topic(topic)
                                  .build()
         ),
         TestProducerFactory.create(
            TestProducerProperties.<PersonDTO>builder()
                                  .brokers(brokers)
                                  .serializer(new PersonSerializer())
                                  .topic(topic)
                                  .build()
         )
      );
   }
}
