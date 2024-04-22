package com.example.recipeservice.repository;

import com.example.recipeservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Category, Integer> {
}
