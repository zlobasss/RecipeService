package com.example.recipeservice.repository;

import com.example.recipeservice.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, Integer> {
}
