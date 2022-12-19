package com.bodaganj.spring.boot.app.impl.mongo;

import com.bodaganj.spring.boot.app.interfaces.Writer;
import com.bodaganj.spring.boot.app.dto.PersonDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MongoWriter implements Writer {

   @Override
   public void write(PersonDTO person) {
      log.info("Start to write to Mongo person with name={}", person.getName());
      //TODO: write to Mongo DB part
      log.info("Finished writing to Mongo person with name={}", person.getName());
   }
}
