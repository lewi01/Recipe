package com.lewiscode.Recipe.controller;

import com.lewiscode.Recipe.entity.Recipe;
import com.lewiscode.Recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;


    @GetMapping("/recipe/{id}")
    public Optional<Recipe> getRecipe(@PathVariable Long id){
        return recipeService.getRecipeById(id);
    }

    @PostMapping ("/recipe/new")
    public void addRecipe(@RequestBody Recipe recipe){
         recipeService.addRecipe(recipe);
    }
    @DeleteMapping("/recipe/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            recipeService.deleteRecipe(id);
            return ResponseEntity.noContent().build();
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }

    }


}
