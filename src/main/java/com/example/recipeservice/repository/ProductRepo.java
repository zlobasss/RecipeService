package com.example.recipeservice.repository;

import com.example.recipeservice.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {

}
