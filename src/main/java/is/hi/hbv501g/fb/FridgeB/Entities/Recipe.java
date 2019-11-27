package is.hi.hbv501g.fb.FridgeB.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private String ratings;
    private String img;
    private String ingredients;
    private double rating;

    /*
    @ElementCollection(targetClass = Diet.class)
    @Column(name = "diet", nullable=false)
    @CollectionTable(name="recipe_diet", joinColumns = {@JoinColumn(name = "recipe_id")})
    public Set<Diet> diet;
*/
    public Recipe(){}

    public Recipe(String name, String description, String ratings, String img, String ingredients, double rating/*, HashSet<Diet> diet*/) {
        this.name = name;
        this.description = description;
        this.ratings = ratings;
        this.img = img;
        this.ingredients = ingredients;
        this.rating =  rating;
        //this.diet = diet;
    }

    public String getImg() { return img; }

    public void setImg(String img) { this.img = img; }

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

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
