package com.bodaganj.spring.boot.app.precondition;

import com.bodaganj.spring.boot.app.dto.PersonDTO;
import com.bodaganj.spring.boot.app.kafka.FeederKafkaConfiguration;
import com.bodaganj.spring.boot.app.kafka.FeederKafkaHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FeederKafkaConfiguration.class)
@TestPropertySource(value = "classpath:kafka.properties")
public class PrepareEnvTest {

   @Autowired
   private FeederKafkaHelper<PersonDTO> feederKafkaHelper;

   @Test
   public void sendSomeDataToKafkaWithConfirmation() {
      String keyFather = UUID.randomUUID().toString();
      PersonDTO father = PersonDTO.builder()
                                  .age(33)
                                  .name("Bogdan")
                                  .status("Father")
                                  .build();

      feederKafkaHelper.sendMessageAndWaitToAppear(keyFather, father);
   }
}
