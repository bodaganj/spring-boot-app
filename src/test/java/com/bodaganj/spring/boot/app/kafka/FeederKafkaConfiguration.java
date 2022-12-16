package com.bodaganj.spring.boot.app.kafka;

import com.bodaganj.spring.boot.app.dto.PersonDTO;
import com.bodaganj.spring.boot.app.kafka.consumer.FeederConsumerFactory;
import com.bodaganj.spring.boot.app.kafka.consumer.FeederConsumerProperties;
import com.bodaganj.spring.boot.app.kafka.producer.FeederProducerFactory;
import com.bodaganj.spring.boot.app.kafka.producer.FeederProducerProperties;
import com.bodaganj.spring.boot.app.kafka.serialize.PersonDeserializer;
import com.bodaganj.spring.boot.app.kafka.serialize.PersonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class FeederKafkaConfiguration {

   @Bean(destroyMethod = "close")
   FeederKafkaHelper<PersonDTO> feederKafkaHelper(
      @Value("${spring.docker.kafka.brokers}") String brokers,
      @Value("${spring.docker.kafka.topic}") String topic
   ) {
      return new FeederKafkaHelper<>(
         FeederConsumerFactory.create(
            FeederConsumerProperties.<PersonDTO>builder()
                                    .brokers(brokers)
                                    .deserializer(new PersonDeserializer())
                                    .topic(topic)
                                    .build()
         ),
         FeederProducerFactory.create(
            FeederProducerProperties.<PersonDTO>builder()
                                    .brokers(brokers)
                                    .serializer(new PersonSerializer())
                                    .topic(topic)
                                    .build()
         )
      );
   }
}
