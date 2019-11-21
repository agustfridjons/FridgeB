package is.hi.hbv501g.fb.FridgeB.Services.Implementations;

import is.hi.hbv501g.fb.FridgeB.Entities.Recipe;
import is.hi.hbv501g.fb.FridgeB.Repositories.RecipeRepo;
import is.hi.hbv501g.fb.FridgeB.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImp implements RecipeService {

    RecipeRepo repo;

    @Autowired
    public RecipeServiceImp(RecipeRepo recipeRepo){
        this.repo = recipeRepo;
    }

    @Override
    public Recipe save(Recipe recipe) {
        return repo.save(recipe);
    }

    @Override
    public void delete(Recipe recipe) {
        repo.delete(recipe);
    }

    @Override
    public List<Recipe> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Recipe> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Optional<Recipe> findById(long id) {
        return repo.findById(id);
    }
}
