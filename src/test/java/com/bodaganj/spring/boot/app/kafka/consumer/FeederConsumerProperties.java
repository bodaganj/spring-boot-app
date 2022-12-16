package com.bodaganj.spring.boot.app.kafka.consumer;

import lombok.Builder;
import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Properties;

@Data
@Builder
public class FeederConsumerProperties<V> {

   @Builder.Default
   private final String groupId = "TestGroupId";
   private final String brokers;
   private final Deserializer<V> deserializer;
   private final String topic;
   @Builder.Default
   private final Duration duration = Duration.ofSeconds(2);

   public Properties getConsumerProperties() {
      Properties properties = new Properties();
      properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
      properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
      properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
      properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OffsetResetStrategy.EARLIEST.name().toLowerCase());
      properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
      properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer.getClass());
      return properties;
   }
}
