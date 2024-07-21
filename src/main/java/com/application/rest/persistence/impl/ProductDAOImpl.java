package com.application.rest.persistence.impl;

import com.application.rest.entities.Product;
import com.application.rest.persistence.IProductDAO;
import com.application.rest.repositories.ProducRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDAOImpl implements IProductDAO {
    @Autowired
    private ProducRepository producRepository;
    @Override
    public List<Product> findAll() {
        return (List<Product>) producRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return producRepository.findById(id);
    }

    @Override
    public List<Product> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return (List<Product>) producRepository.findProductByPriceBetween(minPrice,maxPrice);
    }

    @Override
    public void save(Product product) {
        producRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        producRepository.deleteById(id);
    }
}
