package is.hi.hbv501g.fb.FridgeB.Services;

import is.hi.hbv501g.fb.FridgeB.Entities.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Recipe save(Recipe recipe);
    void delete(Recipe recipe);
    List<Recipe> findAll();
    List<Recipe> findByName(String name);
    Optional<Recipe> findById(long id);
}
