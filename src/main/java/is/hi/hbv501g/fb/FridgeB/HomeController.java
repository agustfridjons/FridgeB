package is.hi.hbv501g.fb.FridgeB;

import is.hi.hbv501g.fb.FridgeB.Entities.Recipe;
import is.hi.hbv501g.fb.FridgeB.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class HomeController {

    private RecipeService recipeService;

    @Autowired
    public HomeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @RequestMapping("/")
    public String Home(Model model){
        model.addAttribute("Recipes", recipeService.findAll());
        return "Velkominn";
    }

    @RequestMapping(value = "/addrecipe",method = RequestMethod.POST)
    public String addRecipe(@Valid Recipe recipe, BindingResult results, Model model){
        if(results.hasErrors()){
            return "add-recipe";
        }
        System.out.println(recipe.getName());
        recipeService.save(recipe);
        model.addAttribute("Recipes", recipeService.findAll());
        return "Velkominn";
    }

    @RequestMapping(value = "/addrecipe", method = RequestMethod.GET)
    public String addRecipeForm(Recipe recipe){
        return "add-recipe";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteRecipe(@PathVariable("id") long id, Model model){
        Recipe recipe = recipeService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Recipe Id"));
        recipeService.delete(recipe);
        model.addAttribute("Recipes", recipeService.findAll());
        return "Velkominn";
    }

}
