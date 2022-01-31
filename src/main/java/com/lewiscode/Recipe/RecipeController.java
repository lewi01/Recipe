package com.lewiscode.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {
    @Autowired
    private RecipeService recipeService;


    @GetMapping("/api/recipe")
    public List<Recipe> getRecipe(){
        return recipeService.getRecipeList();
    }

    @PostMapping ("/api/recipe")
    public void addRecipe(@RequestBody Recipe recipe){
         recipeService.addRecipe(recipe);
    }


}
