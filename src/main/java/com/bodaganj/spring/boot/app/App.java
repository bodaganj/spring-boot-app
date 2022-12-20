package com.bodaganj.spring.boot.app;

import com.bodaganj.spring.boot.app.config.KafkaPersonListenerConfiguration;
import com.bodaganj.spring.boot.app.config.MongoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
   MongoConfiguration.class,
   KafkaPersonListenerConfiguration.class
})
public class App {

   public static void main(String[] args) {
      SpringApplication.run(App.class);
   }
}
