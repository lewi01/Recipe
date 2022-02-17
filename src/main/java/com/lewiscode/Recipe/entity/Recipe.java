package com.lewiscode.Recipe.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotEmpty
    @ElementCollection
    @Size(min = 1,message = "provide at least 2 directions")
    private List<String> ingredients;
    @NotEmpty
    @ElementCollection
    @Size(min = 1,message = "provide at least 3 directions")
    private List<String> directions;
}
