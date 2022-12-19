package com.bodaganj.spring.boot.app.impl.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
@RestController
public class MonitorController {

   private final String version;

   public MonitorController(ApplicationContext context) {
//      The following code takes the version from the MANIFEST file. But it works when the app is started as a jar only.
//      version = context.getBeansWithAnnotation(SpringBootApplication.class)
//                       .values()
//                       .stream()
//                       .findFirst()
//                       .flatMap(getImplementationVersion())
//                       .orElse("Unknown :(");

      version = "Some random version";
   }

   @GetMapping(path = {"/monitor/version"}, produces = {"text/plain"})
   public String version() {
      return version;
   }

   @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(Exception.class)
   void handleException(Exception e) {
      log.error("", e);
   }

   private Function<Object, Optional<String>> getImplementationVersion() {
      return SpringBootApplication ->
         Optional.ofNullable(SpringBootApplication.getClass()
                                                  .getPackage()
                                                  .getImplementationVersion());
   }
}
