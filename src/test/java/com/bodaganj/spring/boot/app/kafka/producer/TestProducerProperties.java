package com.bodaganj.spring.boot.app.kafka.producer;

import lombok.Builder;
import lombok.Data;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Data
@Builder
public class TestProducerProperties<V> {

   private final String brokers;
   private final Serializer<V> serializer;
   private final String topic;

   public Properties getProducerProperties() {
      Properties properties = new Properties();
      properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
      properties.put(ProducerConfig.RETRIES_CONFIG, 0);
      properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, false);
      properties.put(ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, 50000);
      properties.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, 1000);
      properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
      properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializer.getClass());
      return properties;
   }
}
