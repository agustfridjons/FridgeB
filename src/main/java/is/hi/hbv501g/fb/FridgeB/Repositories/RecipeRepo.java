package is.hi.hbv501g.fb.FridgeB.Repositories;

import is.hi.hbv501g.fb.FridgeB.Entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    Recipe save(Recipe recipe);
    void delete(Recipe recipe);
    List<Recipe> findAll();
    List<Recipe> findByName(String name);
    Optional<Recipe> findById(long id);

 }
