package com.lewiscode.Recipe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "email must not be blank")
    @Email(regexp = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z]+",message = "provide a valid email")
    private String email;
    @NotBlank
    @Size(min = 8,message = "password should contain at least 8 characters")
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Recipe> recipe;
}
