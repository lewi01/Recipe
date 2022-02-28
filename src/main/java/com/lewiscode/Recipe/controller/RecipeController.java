package com.lewiscode.Recipe.controller;

import com.lewiscode.Recipe.entity.MyUserDetails;
import com.lewiscode.Recipe.entity.Recipe;
import com.lewiscode.Recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipe")
    public List<Recipe>allRecipe(){
        return recipeService.getAllRecipe();
    }
    @GetMapping( value = "/recipe/{id}")
    public Optional<Recipe> getRecipe(@PathVariable Long id){
        return recipeService.getRecipeById(id);
    }

    @PostMapping (value = "/recipe/new")
    public ResponseEntity<String> addRecipe(@AuthenticationPrincipal MyUserDetails userDetails,
                                            @RequestBody Recipe newRecipe) {
          Recipe createdRecipe = recipeService.addRecipe(newRecipe, userDetails.getUsername());
          if (createdRecipe == null){
              return ResponseEntity.notFound().build();
          }else {
              String response = "{" +
                      "id:" + createdRecipe.getId() +
                      "}";
              return new  ResponseEntity<>(response,HttpStatus.OK);
          }

    }
    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<Recipe> delete(@PathVariable Long id,
                                         @AuthenticationPrincipal MyUserDetails userDetails){
        try {
            if (recipeService.deleteRecipe(id, userDetails.getUsername())) {
                return ResponseEntity.noContent().build();
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only update your own recipes.");
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/recipe/{id}")
    public ResponseEntity<Recipe> update(@AuthenticationPrincipal MyUserDetails userDetails,
                                         @RequestBody Recipe newRecipe,@PathVariable Long id){
        try {

            if (recipeService.updateRecipe(newRecipe,id, userDetails.getUsername())) {
                return ResponseEntity.noContent().build();
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only update your own recipes.");
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/recipe/search/")
    public List<Recipe> search(@RequestParam(required = false) String category,
                               @RequestParam(required = false) String name){
        if (category != null){
            return recipeService.searchCategory(category);
        }else if (name != null){
            return recipeService.searchName(name);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"BAD_REQUEST");
        }
    }
}
