package com.lewiscode.Recipe;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService {

    private final Map<Integer,Recipe> recipeList = new HashMap<>();

    public Recipe getRecipeList(int id){
        Recipe recipe = recipeList.get(id);
        if (recipe == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not Found");
        }
        return recipe;
    }

    public void addRecipe(Recipe recipe){
        int id=0;
        recipeList.put(++id,recipe);
    }
}
