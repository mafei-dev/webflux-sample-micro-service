package com.mafei.productservice.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
  @Author mafei
*/
public class Product {

    @Data
    @ToString
    @NoArgsConstructor
    @Document(collection = "product")
    public static class Entity {
        @Id
        private String id;
        private String description;
        private Double price;
    }

    @Data
    @ToString
    @NoArgsConstructor
    public static class ProductDTO {
        private String id;
        private String description;
        private Double price;
    }
}
