package com.lewiscode.Recipe.service;

import com.lewiscode.Recipe.entity.Recipe;
import com.lewiscode.Recipe.repository.RecipeRepository;
import com.lewiscode.Recipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Recipe> getAllRecipe(){
        return recipeRepository.findAll();
    }

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
    public void updateRecipe(Recipe newRecipe, Long id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
                recipe.get().setName(newRecipe.getName());
                recipe.get().setCategory(newRecipe.getCategory());
                recipe.get().setDate(LocalDateTime.now());
                recipe.get().setDescription(newRecipe.getDescription());
                recipe.get().setIngredients(newRecipe.getIngredients());
                recipe.get().setDirections(newRecipe.getDirections());
               recipeRepository.save(recipe.get());
        }throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"BAD_REQUEST");

    }
    public List<Recipe> searchCategory(String category){
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> searchName(String name){
        return recipeRepository.findByNameIgnoreCaseContainingOrderByDateDesc(name);
    }
}
