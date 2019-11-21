package is.hi.hbv501g.fb.FridgeB.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private Double rating;

    @ElementCollection(targetClass = Diet.class)
    @Column(name = "diet", nullable=false)
    @CollectionTable(name="recipe_diet", joinColumns = {@JoinColumn(name = "recipe_id")})
    public Set<Diet> diet;

    public Recipe(){}

    public Recipe(String name, String description, Double rating, HashSet<Diet> diet) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.diet = diet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
