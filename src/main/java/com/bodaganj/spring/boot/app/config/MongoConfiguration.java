package com.bodaganj.spring.boot.app.config;

import com.bodaganj.spring.boot.app.impl.mongo.MongoWriter;
import com.bodaganj.spring.boot.app.interfaces.Writer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

   @Bean
   Writer writer() {
      return new MongoWriter();
   }
}
