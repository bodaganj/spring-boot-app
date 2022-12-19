package com.bodaganj.spring.boot.app.impl.kafka;

import com.bodaganj.spring.boot.app.interfaces.Processor;
import com.bodaganj.spring.boot.app.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@RequiredArgsConstructor
public class PersonKafkaListener {

   private final Processor processor;

   @KafkaListener(
      id = "personListener",
      topics = "${kafka.topic}",
      containerFactory = "personKafkaListenerContainerFactory"
   )
   public void processMessage(ConsumerRecord<String, PersonDTO> record) {
      log.info("Consumed message with key={} and value={}", record.key(), record.value());
      processor.process(record.value());
   }
}
