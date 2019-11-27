package is.hi.hbv501g.fb.FridgeB.Repositories;

import is.hi.hbv501g.fb.FridgeB.Entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    Recipe save(Recipe recipe);
    void delete(Recipe recipe);
    List<Recipe> findAll();
    Optional<Recipe> findById(long id);
    List<Recipe> findByName(String name);

    @Query(value = "SELECT * FROM recipe WHERE name LIKE '%:key%'", nativeQuery = true)
    List<Recipe> searchByKey(@Param("key") String key);
 }
