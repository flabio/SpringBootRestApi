package com.application.rest.repositories;

import com.application.rest.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProducRepository extends CrudRepository<Product,Long> {
    List<Product>findProductByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

}
