package com.application.rest.mapper;

import com.application.rest.controllers.dto.ProductDTO;
import com.application.rest.entities.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "price",target = "price",numberFormat = "$0.00"),
            @Mapping(source = "maker.name",target = "nameMaker"),
    })
    ProductDTO productToProductDTO(Product product);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "maker",ignore = true)
    })
    Product toEntity(ProductDTO productDTO);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "maker",ignore = true)
    })
    List<ProductDTO> toProductDTOList(List<Product> productList);
}
