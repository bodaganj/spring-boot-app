package com.bodaganj.spring.boot.app.kafka.serialize;

import com.bodaganj.spring.boot.app.dto.PersonDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class PersonSerializer implements Serializer<PersonDTO> {

   private final ObjectMapper objectMapper = new ObjectMapper();

   @Override
   public byte[] serialize(String s, PersonDTO personDTO) {
      try {
         return objectMapper.writeValueAsBytes(personDTO);
      } catch (JsonProcessingException e) {
         throw new SerializationException(e);
      }
   }
}
