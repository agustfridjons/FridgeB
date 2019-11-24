package is.hi.hbv501g.fb.FridgeB;

import is.hi.hbv501g.fb.FridgeB.Entities.Recipe;
import is.hi.hbv501g.fb.FridgeB.Entities.User;
import is.hi.hbv501g.fb.FridgeB.Services.RecipeService;
import is.hi.hbv501g.fb.FridgeB.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RecipeController {

    private RecipeService recipeService;
    private UserService userService;

    @Autowired
    public RecipeController(UserService userService, RecipeService recipeService){
        this.userService = userService;
        this.recipeService = recipeService;
    }
    @RequestMapping("/")
    public String Home(Model model){
        model.addAttribute("Recipes", recipeService.findAll());
        return "home";
    }

    @RequestMapping(value = "/addrecipe",method = RequestMethod.POST)
    public String addRecipe(@Valid Recipe recipe, BindingResult results, Model model){
        if(results.hasErrors()){
            return "add-recipe";
        }
        recipeService.save(recipe);
        model.addAttribute("Recipes", recipeService.findAll());
        return "home";
    }

    @RequestMapping(value = "/addrecipe", method = RequestMethod.GET)
    public String addRecipe(Recipe recipe){
        return "add-recipe";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteRecipe(@PathVariable("id") long id, Model model){
        Recipe recipe = recipeService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Recipe Id"));
        recipeService.delete(recipe);
        model.addAttribute("Recipes", recipeService.findAll());
        return "home";
    }

    @RequestMapping("/recipeSearch")
    public String search(){
        return "search";
    }

    @RequestMapping(value= "/recipeSearch", method = RequestMethod.POST)
    public String searchRecipe(@RequestParam(value = "search", required = false) String search, Model model){
        List<Recipe> recipe = recipeService.findByName(search);
        System.out.println(recipe.get(0));
        model.addAttribute("Recipes", recipe);
        return "home";
    }

    @RequestMapping("/makedata")
    public String makeData(Model model){
        System.out.println("make recipes");
        /*HashSet<Diet> diets = new HashSet<>();
        diets.add(Diet.CLASSIC);
        diets.add(Diet.VEGITERIAN);*/
        for (int i = 1; i <= 3; i++) {
            this.recipeService.save(new Recipe("Good food "+i," recipe with ",Double.valueOf(i)/*,diets*/));
        }
        User tempUser = new User("Karl Jóhann","pass123");
        this.userService.save(tempUser);
        model.addAttribute("Recipes", recipeService.findAll());
        return "home";
    }

}
