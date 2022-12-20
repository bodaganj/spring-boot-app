package com.bodaganj.spring.boot.app.kafka;

import com.bodaganj.spring.boot.app.test.kafka.EmbeddedKafkaTest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.test.utils.ContainerTestUtils;

@RequiredArgsConstructor
public class PartitionAssignmentHelper {

   private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

   public void waitForAssignment() {
      kafkaListenerEndpointRegistry.getListenerContainers()
                                   .forEach(container -> ContainerTestUtils.waitForAssignment(container,
                                                                                              EmbeddedKafkaTest.PARTITIONS_NUMBER));
   }
}
