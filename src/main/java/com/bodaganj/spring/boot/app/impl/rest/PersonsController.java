package com.bodaganj.spring.boot.app.impl.rest;

import com.bodaganj.spring.boot.app.interfaces.PersonRetriever;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PersonsController {

   @Autowired
   PersonRetriever personRetriever;

   @GetMapping(path = {"/person/names"}, produces = {"text/plain"})
   public String personNames() {
      return personRetriever.retrievePersonNames();
   }

   @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(Exception.class)
   void handleException(Exception e) {
      log.error("", e);
   }
}
