package com.bodaganj.spring.boot.app;

import com.bodaganj.spring.boot.app.config.KafkaPersonListenerConfiguration;
import com.bodaganj.spring.boot.app.config.MongoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@ImportAutoConfiguration(KafkaAutoConfiguration.class)
@SpringBootApplication
@Import({
   MongoConfiguration.class,
   KafkaPersonListenerConfiguration.class
})
@PropertySource(value = "classpath:application.properties")
public class App {

   public static void main(String[] args) {
      SpringApplication.run(App.class);
   }
}
