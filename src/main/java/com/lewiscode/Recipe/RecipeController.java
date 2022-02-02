package com.lewiscode.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;


    @GetMapping("/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id){
        return recipeService.getRecipeList(id);
    }

    @PostMapping ("/recipe/new")
    public void addRecipe(@RequestBody Recipe recipe){
         recipeService.addRecipe(recipe);
    }


}
