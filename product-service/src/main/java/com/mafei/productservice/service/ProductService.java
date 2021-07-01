package com.mafei.productservice.service;

import com.mafei.productservice.bean.Product;
import com.mafei.productservice.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
  @Author mafei
*/
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<Product.ProductDTO> getAllProducts() {
        return productRepository.findAll().map(entity -> {
            Product.ProductDTO productDTO = new Product.ProductDTO();
            BeanUtils.copyProperties(entity, productDTO);
            return productDTO;
        });
    }

    public Mono<Product.ProductDTO> getProductById(final String productID) {
        return productRepository.findById(productID).map(entity -> {
            Product.ProductDTO productDTO = new Product.ProductDTO();
            BeanUtils.copyProperties(entity, productDTO);
            return productDTO;
        });
    }

    public Mono<Product.ProductDTO> insertProduct(Mono<Product.ProductDTO> productDTOMono) {
        return productDTOMono
                .map(_productDTO -> {
                    Product.Entity entity = new Product.Entity();
                    BeanUtils.copyProperties(_productDTO, entity);
                    return entity;
                })
                .flatMap(entity -> this.productRepository.insert(entity))
                .map(_entity -> {
                    Product.ProductDTO _productDTO = new Product.ProductDTO();
                    BeanUtils.copyProperties(_entity, _productDTO);
                    return _productDTO;
                });
    }

    public Mono<Product.ProductDTO> updateProduct(Mono<Product.ProductDTO> productDTOMono) {
        return productDTOMono
                .map(_productDTO -> {
                    Product.Entity entity = new Product.Entity();
                    BeanUtils.copyProperties(_productDTO, entity);
                    return entity;
                })
                .flatMap(entity -> this.productRepository.save(entity))
                .map(_entity -> {
                    Product.ProductDTO _productDTO = new Product.ProductDTO();
                    BeanUtils.copyProperties(_entity, _productDTO);
                    return _productDTO;
                });
    }

    public Mono<Void> deleteProduct(final String productID) {
        return this.productRepository.deleteById(productID);
    }
}
