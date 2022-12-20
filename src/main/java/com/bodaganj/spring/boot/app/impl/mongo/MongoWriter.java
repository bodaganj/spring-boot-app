package com.bodaganj.spring.boot.app.impl.mongo;

import com.bodaganj.spring.boot.app.dto.PersonDTO;
import com.bodaganj.spring.boot.app.interfaces.Writer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class MongoWriter implements Writer {

   private PersonRepository repository;

   @Override
   public void write(PersonDTO person) {
      log.info("Start to write to Mongo person with name={}", person.getName());
      repository.save(person);
      log.info("Finished writing to Mongo person with name={}", person.getName());
   }
}
