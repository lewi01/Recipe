package com.lewiscode.Recipe;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private final List<Recipe> recipeList = new ArrayList<>();

    public List<Recipe> getRecipeList(){
        return recipeList;
    }
    public void addRecipe(Recipe recipe){
        recipeList.add(recipe);
    }
}
