package com.bodaganj.spring.boot.app.test;

import com.bodaganj.spring.boot.app.config.KafkaPersonListenerConfiguration;
import com.bodaganj.spring.boot.app.dto.PersonDTO;
import com.bodaganj.spring.boot.app.interfaces.Writer;
import com.bodaganj.spring.boot.app.kafka.PartitionAssignmentHelper;
import com.bodaganj.spring.boot.app.kafka.TestEmbeddedKafkaConfiguration;
import com.bodaganj.spring.boot.app.kafka.TestKafkaHelper;
import com.bodaganj.spring.boot.app.test.kafka.EmbeddedKafkaTest;
import com.bodaganj.spring.boot.app.test.kafka.TestMongoConfiguration;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(
   classes = {
      TestMongoConfiguration.class,
      KafkaPersonListenerConfiguration.class,
      TestEmbeddedKafkaConfiguration.class
   },
   webEnvironment = SpringBootTest.WebEnvironment.NONE,
   properties = {
      "spring.kafka.topic=test_topic"
   }
)
@EmbeddedKafkaTest
public class KafkaProcessingTest {

   @Autowired
   TestKafkaHelper<PersonDTO> kafkaTestHelper;

   @Autowired
   Writer mongoWriter;

   @Autowired
   PartitionAssignmentHelper partitionAssignmentHelper;

   @BeforeEach
   void setUp() {
      partitionAssignmentHelper.waitForAssignment();
      Mockito.reset(mongoWriter);
   }

   @Test
   void shouldConsumeMessageFromKafkaAndWriteToMongo() {
      String testName = "Test name";
      PersonDTO personDTO = PersonDTO.builder().name(testName).build();

      kafkaTestHelper.sendMessageAndWaitToAppear(testName, personDTO);

      Awaitility.await()
                .atMost(Duration.ofSeconds(2))
                .untilAsserted(() ->
                                  verify(mongoWriter, times(1))
                                     .write(argThat(person -> {
                                        assertThat(person.getName())
                                           .as("Person name is not correct.")
                                           .isEqualTo(testName);
                                        return true;
                                     })));
   }
}
