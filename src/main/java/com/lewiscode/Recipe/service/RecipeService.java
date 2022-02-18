package com.lewiscode.Recipe.service;

import com.lewiscode.Recipe.entity.Recipe;
import com.lewiscode.Recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
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

    public Recipe addRecipe(Recipe recipe){
        recipe.setDate(LocalDateTime.now());
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(long recipeId){
        recipeRepository.deleteById(recipeId);
    }
    public Optional<Recipe> updateRecipe(Recipe newRecipe, Long id){
        Optional<Recipe> recipeId = recipeRepository.findById(id);
        if (recipeId.isPresent()) {
            return recipeId.map(recipe -> {
                recipe.setName(newRecipe.getName());
                recipe.setCategory(newRecipe.getCategory());
                recipe.setDate(LocalDateTime.now());
                recipe.setDescription(newRecipe.getDescription());
                recipe.setIngredients(newRecipe.getIngredients());
                recipe.setDirections(newRecipe.getDirections());
                return recipeRepository.save(recipe);
            });
        }throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"BAD_REQUEST");

    }
}
