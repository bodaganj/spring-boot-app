package com.bodaganj.spring.boot.app.config;

import com.bodaganj.spring.boot.app.impl.mongo.MongoPersonRetriever;
import com.bodaganj.spring.boot.app.impl.mongo.MongoWriter;
import com.bodaganj.spring.boot.app.impl.mongo.PersonRepository;
import com.bodaganj.spring.boot.app.interfaces.PersonRetriever;
import com.bodaganj.spring.boot.app.interfaces.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
   basePackages = "com.bodaganj.spring.boot.app.impl.mongo"
)
public class MongoConfiguration {

   @Autowired
   PersonRepository repository;

   @Bean
   PersonRetriever personRetriever(PersonRepository repository) {
      return new MongoPersonRetriever(repository);
   }

   @Bean
   Writer writer(PersonRepository repository) {
      return new MongoWriter(repository);
   }
}
