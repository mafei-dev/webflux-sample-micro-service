package com.mafei.productservice.controller;

import com.mafei.productservice.bean.Product;
import com.mafei.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
  @Author mafei
*/
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<Product.ProductDTO> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product.ProductDTO>> getById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<Product.ProductDTO> insert(@RequestBody Mono<Product.ProductDTO> productDTOMono) {
        return productService.insertProduct(productDTOMono);
    }

    @PutMapping
    public Mono<ResponseEntity<Product.ProductDTO>> update(@RequestBody Mono<Product.ProductDTO> productDTOMono) {
        return productService.updateProduct(productDTOMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return productService.deleteProduct(id);
    }
}
