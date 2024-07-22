package com.application.rest.controllers.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDefaultDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private byte age;
    private Character gender;


}
