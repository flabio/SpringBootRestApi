package com.application.rest.mapper;

import com.application.rest.controllers.dto.MakerDTO;
import com.application.rest.entities.Maker;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel=MappingConstants.ComponentModel.SPRING,uses ={ProductMapper.class} )
public interface MakerMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name",target = "name"),

            @Mapping(source = "productList",target = "productList"),
    })
    MakerDTO makerToMakerDTO(Maker maker);
    @InheritInverseConfiguration
    Maker toEntity(MakerDTO makerDTO);
    List<MakerDTO> toMakerDTOList(List<Maker> makerList);
}
