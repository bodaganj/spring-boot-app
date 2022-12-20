package com.bodaganj.spring.boot.app.test.kafka;

import com.bodaganj.spring.boot.app.interfaces.Writer;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestMongoConfiguration {

   @Bean
   @Primary
   Writer writer() {
      return Mockito.mock(Writer.class);
   }
}
