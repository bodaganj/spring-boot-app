package com.bodaganj.spring.boot.app.kafka.consumer;

import com.bodaganj.spring.boot.app.kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TestKafkaConsumer<V> implements AutoCloseable {

   private final KafkaConsumer<String, V> consumer;
   private final String topic;
   private final Duration defaultPollDuration;
   private final int lastMessagesToCheckout = 100;

   public List<KafkaMessage<V>> consumeMessage(int partition) {
      seekToLastMessages(partition);

      log.info("Started polling last {} messages from topic: [{}], partition: [{}]", lastMessagesToCheckout, topic, partition);
      List<KafkaMessage<V>> partitionMessages = new ArrayList<>();
      consumer.poll(defaultPollDuration)
              .forEach(consumerRecord -> partitionMessages.add(new KafkaMessage<>(consumerRecord)));

      List<KafkaMessage<V>> consumedMessages = new ArrayList<>(partitionMessages);
      log.info("Consumed [{}] messages from topic: [{}], partition: [{}]", partitionMessages.size(), topic, partition);
      return consumedMessages;
   }

   public void seekToLastMessages(int partition) {
      final TopicPartition topicPartition = new TopicPartition(topic, partition);
      final List<TopicPartition> topicPartitions = Collections.singletonList(topicPartition);

      consumer.assign(topicPartitions);
      consumer.seekToEnd(topicPartitions);

      long endPosition = consumer.position(topicPartition);
      long recentMessagesStartPosition = endPosition - lastMessagesToCheckout >= 0 ? endPosition - lastMessagesToCheckout : 0;
      consumer.seek(topicPartition, recentMessagesStartPosition);
   }

   @Override
   public void close() {
      consumer.close();
   }
}
