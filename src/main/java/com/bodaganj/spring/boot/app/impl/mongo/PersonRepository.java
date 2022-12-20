package com.bodaganj.spring.boot.app.impl.mongo;

import com.bodaganj.spring.boot.app.dto.PersonDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<PersonDTO, String> {

   public List<PersonDTO> findByName(String name);
}
