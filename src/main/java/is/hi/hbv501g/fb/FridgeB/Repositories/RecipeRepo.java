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

    @Query(value = "SELECT * FROM recipe WHERE name LIKE %:key%", nativeQuery = true)
    List<Recipe> searchByKey(@Param("key") String key);

    @Query(value = "SELECT * FROM recipe WHERE ingredients LIKE %:ing1% AND ingredients LIKE %:ing2% AND ingredients LIKE %:ing3% AND ingredients LIKE %:ing4% AND ingredients LIKE %:ing5% AND ingredients LIKE %:ing6% AND ingredients LIKE %:ing7% AND ingredients LIKE %:ing8%", nativeQuery = true)
    List<Recipe> searchByIngredients(@Param("ing1") String ing1, @Param("ing2") String ing2, @Param("ing3") String ing3, @Param("ing4") String ing4, @Param("ing5") String ing5, @Param("ing6") String ing6, @Param("ing7") String ing7, @Param("ing8") String ing8);

 }
