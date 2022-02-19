package com.lewiscode.Recipe.repository;

import com.lewiscode.Recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe>findByCategoryIgnoreCaseOrderByDateDesc(String category);
    List<Recipe>findByNameIgnoreCaseContainingOrderByDateDesc(String name);
}
