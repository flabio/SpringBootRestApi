package com.application.rest.mapper;

import com.application.rest.controllers.dto.PersonDefaultDTO;
import com.application.rest.entities.Person;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {
    //PersonMapper INSTANCE= Mappers.getMapper(PersonMapper.class);
    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName",target = "lastName"),
            @Mapping(source = "email",target = "email"),
            @Mapping(source = "age",target = "age"),
            @Mapping(source = "gender",target = "gender"),

    })

    PersonDefaultDTO personToPersonDefaultDTO(Person person);
    
    @InheritInverseConfiguration
    Person toEntity(PersonDefaultDTO personDefaultDTO);

    List<PersonDefaultDTO> personDefaultDTOList(List<Person> personList);

}
