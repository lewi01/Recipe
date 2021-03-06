package com.lewiscode.Recipe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
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
    private String category;
    @NonNull
    @CreationTimestamp
    private LocalDateTime date;
    @NotBlank
    private String description;
    @NotEmpty
    @ElementCollection
    @Size(min = 1,message = "provide at least 1 directions")
    private List<String> ingredients;
    @NotEmpty
    @ElementCollection
    @Size(min = 1,message = "provide at least 1 directions")
    private List<String> directions;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
