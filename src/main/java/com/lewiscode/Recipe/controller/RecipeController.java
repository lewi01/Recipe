package com.lewiscode.Recipe.controller;

import com.lewiscode.Recipe.entity.Recipe;
import com.lewiscode.Recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;


    @GetMapping( value = "/recipe/{id}")
    public Optional<Recipe> getRecipe(@PathVariable Long id){
        return recipeService.getRecipeById(id);
    }

    @PostMapping (value = "/recipe/new")
    public ResponseEntity<Long> addRecipe(@RequestBody Recipe recipe) {
          Recipe createdRecipe = recipeService.addRecipe(recipe);
          if (createdRecipe == null){
              return ResponseEntity.notFound().build();
          }else {
              URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                      .path("/{id}")
                      .buildAndExpand(createdRecipe.getId())
                      .toUri();
              return ResponseEntity.created(uri).body(createdRecipe.getId());
          }

    }
    @DeleteMapping("/recipe/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            recipeService.deleteRecipe(id);
            return ResponseEntity.ok("NO_CONTENT");
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/recipe/{id}")
    public ResponseEntity<?> update(@RequestBody Recipe newRecipe,@PathVariable Long id){
        try {
            recipeService.updateRecipe(newRecipe,id);
            return ResponseEntity.noContent().build();
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }

    }
}
