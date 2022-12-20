package com.bodaganj.spring.boot.app.config;

import com.bodaganj.spring.boot.app.dto.PersonDTO;
import com.bodaganj.spring.boot.app.impl.kafka.PersonKafkaListener;
import com.bodaganj.spring.boot.app.impl.kafka.PersonProcessorImpl;
import com.bodaganj.spring.boot.app.interfaces.Processor;
import com.bodaganj.spring.boot.app.interfaces.Writer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.CommonLoggingErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
@ImportAutoConfiguration(KafkaAutoConfiguration.class)
@PropertySource(value = "classpath:application.properties")
public class KafkaPersonListenerConfiguration {

   @Bean
   Processor processor(Writer writer) {
      return new PersonProcessorImpl(writer);
   }

   @Bean
   PersonKafkaListener personKafkaListener(Processor processor) {
      return new PersonKafkaListener(processor);
   }

   @Bean
   ConcurrentKafkaListenerContainerFactory<String, PersonDTO> personKafkaListenerContainerFactory(KafkaProperties kafkaProperties,
                                                                                                  CommonErrorHandler commonErrorHandler) {
      DefaultKafkaConsumerFactory<String, PersonDTO> cf =
         new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(),
                                           new StringDeserializer(),
                                           new JsonDeserializer<>(PersonDTO.class, false));

      ConcurrentKafkaListenerContainerFactory<String, PersonDTO> cklcf = new ConcurrentKafkaListenerContainerFactory<>();
      cklcf.setConsumerFactory(cf);
      cklcf.setCommonErrorHandler(commonErrorHandler);

      return cklcf;
   }

   @Bean
   CommonErrorHandler errorHandler() {
      return new CommonLoggingErrorHandler();
   }
}
