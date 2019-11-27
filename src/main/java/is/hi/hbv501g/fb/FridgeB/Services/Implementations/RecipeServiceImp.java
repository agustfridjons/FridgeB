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
    public Optional<Recipe> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public List<String> findIngredients(String ratings){
        List<String> ing = new ArrayList<>();
        int c=0;
        int j = 0;
        for(int i = 1; i < ratings.length(); i++){
            j=i+1;
            while(!String.valueOf(ratings.charAt(j)).equals(",")){
                j++;
            }
            System.out.println(ratings.substring(i,j));
            ing.add(c, ratings.substring(i,j));
            c++;
            i = j;
        }
        return ing;
    }

    @Override
    public double calculateRating(String ratings){
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
