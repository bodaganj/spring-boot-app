package com.bodaganj.spring.boot.app.impl.kafka;

import com.bodaganj.spring.boot.app.interfaces.Processor;
import com.bodaganj.spring.boot.app.dto.PersonDTO;
import com.bodaganj.spring.boot.app.interfaces.Writer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonProcessorImpl implements Processor {

   private final Writer writer;

   @Override
   public void process(PersonDTO person) {
      writer.write(person);
   }
}
