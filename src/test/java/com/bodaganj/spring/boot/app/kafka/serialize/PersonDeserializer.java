package com.bodaganj.spring.boot.app.kafka.serialize;

import com.bodaganj.spring.boot.app.dto.PersonDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class PersonDeserializer implements Deserializer<PersonDTO> {

   private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

   @Override
   public PersonDTO deserialize(String s, byte[] bytes) {
      try {
         return objectMapper.readValue(bytes, PersonDTO.class);
      } catch (IOException e) {
         throw new SerializationException(e);
      }
   }
}
