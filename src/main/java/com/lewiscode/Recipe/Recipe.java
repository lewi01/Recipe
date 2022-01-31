package com.lewiscode.Recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private String name;
    private String description;
    private String ingredients;
    private String directions;
}
