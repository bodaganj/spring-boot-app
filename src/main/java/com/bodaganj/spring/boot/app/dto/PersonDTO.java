package com.bodaganj.spring.boot.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

   @Id
   public String id;
   private String name;
   private int age;
   private String status;
}
