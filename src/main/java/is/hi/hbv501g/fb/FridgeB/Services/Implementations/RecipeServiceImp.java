package is.hi.hbv501g.fb.FridgeB.Services.Implementations;

import is.hi.hbv501g.fb.FridgeB.Entities.Recipe;
import is.hi.hbv501g.fb.FridgeB.Repositories.RecipeRepo;
import is.hi.hbv501g.fb.FridgeB.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Recipe> findByName(String name){ return repo.findByName(name);}

    @Override
    public List<Recipe> searchByKey(String key) {
        return repo.searchByKey(key);
    }

    @Override
    public List<Recipe> searchByIngredients(String ing1, String ing2, String ing3, String ing4, String ing5, String ing6, String ing7, String ing8) {
        return repo.searchByIngredients(ing1, ing2, ing3, ing4, ing5, ing6, ing7, ing8);
    }

    public Optional<Recipe> findById(long id) { return repo.findById(id); }

    @Override
    public void addValues(Recipe recipe){
        recipe.setRating(0.0);
        recipe.setRatings("");
    }

    @Override
    public List<String> findIngredients(String ratings){
        List<String> ing = new ArrayList<>();
        int c=0;
        int j = 0;
        for(int i = 0; i < ratings.length(); i++){
            j=i+1;
            while(!(ratings.substring(j,j+2).equals(", "))){
                if(j+3>(ratings.length())){
                    j += 2;
                    break;
                }
                j++;
            }
            ing.add(c, ratings.substring(i,j));
            c++;
            i = j+1;
        }
        return ing;
    }

    @Override
    public double calculateRating(String ratings){
        if(ratings.equals("")||ratings == null){
            return 0.0;
        }
        double s = 0;
        double c = 0;
        for (int i = 0; i < ratings.length(); i++) {
            s += Integer.valueOf(ratings.substring(i, i+1));
            c++;
        }
        return round(s/c,2);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
