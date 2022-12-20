package com.bodaganj.spring.boot.app.precondition;

import com.bodaganj.spring.boot.app.dto.PersonDTO;
import com.bodaganj.spring.boot.app.kafka.TestDockerizedKafkaConfiguration;
import com.bodaganj.spring.boot.app.kafka.TestKafkaHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestDockerizedKafkaConfiguration.class)
@TestPropertySource(value = "classpath:kafka.properties")
public class PrepareEnvTest {

   @Autowired
   private TestKafkaHelper<PersonDTO> testKafkaHelper;

   @Test
   public void sendSomeDataToKafkaWithConfirmation() {
      String keyFather = UUID.randomUUID().toString();
      PersonDTO father = PersonDTO.builder()
                                  .age(33)
                                  .name("Bogdan")
                                  .status("Father")
                                  .build();

      testKafkaHelper.sendMessageAndWaitToAppear(keyFather, father);
   }
}
