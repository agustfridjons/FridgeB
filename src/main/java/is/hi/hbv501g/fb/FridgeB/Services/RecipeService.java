package is.hi.hbv501g.fb.FridgeB.Services;

        import is.hi.hbv501g.fb.FridgeB.Entities.Recipe;

        import java.util.List;
        import java.util.Optional;

public interface RecipeService {
    Recipe save(Recipe recipe);
    void delete(Recipe recipe);
    List<Recipe> findAll();
    List<Recipe> findByName(String name);
    List<Recipe> searchByKey(String key);
    Optional<Recipe> findById(long id);
    double calculateRating(String ratings);
    List<String> findIngredients(String ratings);
    List<Recipe> searchByIngredients(String ing1, String ing2, String ing3, String ing4, String ing5, String ing6, String ing7, String ing8);
    void addValues(Recipe recipe);
}
