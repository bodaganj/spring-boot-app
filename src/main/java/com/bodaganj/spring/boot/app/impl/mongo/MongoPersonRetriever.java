package com.bodaganj.spring.boot.app.impl.mongo;

import com.bodaganj.spring.boot.app.dto.PersonDTO;
import com.bodaganj.spring.boot.app.interfaces.PersonRetriever;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class MongoPersonRetriever implements PersonRetriever {

   private PersonRepository repository;

   @Override
   public String retrievePersonNames() {
      log.info("Get list of names from Mongo DB.");
      List<PersonDTO> all = repository.findAll();
      List<String> ans = all.stream().map(PersonDTO::getName).toList();
      log.info("List of names is retrieved from Mongo DB successfully.");
      return String.join(" ", ans);
   }
}
