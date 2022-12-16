package com.bodaganj.spring.boot.app.kafka;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class KafkaMessage<V> {

   final String key;
   final V value;

   public KafkaMessage(ConsumerRecord<String, V> consumerRecord) {
      this.key = consumerRecord.key();
      this.value = consumerRecord.value();
   }
}
