package com.lewiscode.Recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private String name;
    private String description;
    private List<String> ingredients;
    private List<String> directions;
}
