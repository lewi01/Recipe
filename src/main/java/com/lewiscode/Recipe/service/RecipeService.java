package com.lewiscode.Recipe.service;

import com.lewiscode.Recipe.entity.Recipe;
import com.lewiscode.Recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Optional<Recipe> getRecipeById(Long recipeId){
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            return recipe;
        }throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not Found");
    }

    public Long addRecipe(Recipe recipe){
        Recipe recipe1 = recipeRepository.save(recipe);
        return recipe1.getId();
    }

    public void deleteRecipe(long recipeId){
        recipeRepository.deleteById(recipeId);
    }
}
