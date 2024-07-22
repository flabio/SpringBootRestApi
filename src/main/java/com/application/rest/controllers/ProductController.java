package com.application.rest.controllers;

import com.application.rest.controllers.dto.ProductDTO;
import com.application.rest.entities.Product;
import com.application.rest.mapper.ProductMapper;
import com.application.rest.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private IProductService productService;
    private ProductMapper productMapper;
    @Autowired
    public ProductController(IProductService productService,ProductMapper productMapper){
        this.productService=productService;
        this.productMapper=productMapper;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<ProductDTO> productDTOList = productMapper.toProductDTOList(productService.findAll());
        return ResponseEntity.ok(productDTOList);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if(productOptional.isPresent()){
            Product product =productOptional.get();
            ProductDTO productDTO = productMapper.productToProductDTO(product);
            return ResponseEntity.ok(productDTO);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductDTO productDTO) throws URISyntaxException {
        if(productDTO.getName().isBlank() || productDTO.getPrice()==null||productDTO.getMaker()==null ){
            return ResponseEntity.badRequest().build();
        }
        Product product = productMapper.toEntity(productDTO);
        productService.save(product);
        return ResponseEntity.created(new URI("/api/product/save")).build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody ProductDTO productDTO){
        Optional<Product> productOptional= productService.findById(id).map(
                product -> productMapper.toEntity(productDTO)
        );
        if(productOptional.isPresent()){

            Product product =productOptional.get();

            productService.save(product);
            return ResponseEntity.ok("Registro Actualizado");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if(id!=null){
            productService.deleteById(id);
            return ResponseEntity.ok("Registro eliminado");
        }
        return ResponseEntity.badRequest().build();
    }
}
