package com.mafei.productservice.repository;

import com.mafei.productservice.bean.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/*
  @Author mafei
*/
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product.Entity, String> {

}
