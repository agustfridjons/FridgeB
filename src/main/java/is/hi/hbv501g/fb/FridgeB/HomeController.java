package is.hi.hbv501g.fb.FridgeB;

import is.hi.hbv501g.fb.FridgeB.Entities.Diet;
import is.hi.hbv501g.fb.FridgeB.Entities.Recipe;
import is.hi.hbv501g.fb.FridgeB.Entities.User;
import is.hi.hbv501g.fb.FridgeB.Entities.ViewLog;
import is.hi.hbv501g.fb.FridgeB.Services.RecipeService;
import is.hi.hbv501g.fb.FridgeB.Services.UserService;
import is.hi.hbv501g.fb.FridgeB.Services.ViewLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

@Controller
public class HomeController {

    private RecipeService recipeService;
    private ViewLogService viewLogService;
    private UserService userService;

    @Autowired
    public HomeController(RecipeService recipeService, ViewLogService viewLogService, UserService userService){
        this.viewLogService = viewLogService;
        this.recipeService = recipeService;
        this.userService = userService;
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
        recipeService.save(recipe);
        model.addAttribute("Recipes", recipeService.findAll());
        return "Velkominn";
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
        return "Velkominn";
    }

    @RequestMapping("/search")
    public String search(){
        return "search";
    }

    @RequestMapping(value= "/recipeSearch", method = RequestMethod.POST)
    public String searchMovie(@RequestParam(value = "search", required = false) String search, Model model){
        List<Recipe> recipe = recipeService.findByName(search);
        model.addAttribute("Recipe", recipe);
        return "Velkominn";
    }

    @RequestMapping("/makedata")
    public String makeData(Model model){
        HashSet<Diet> diets = new HashSet<>();
        diets.add(Diet.NORMAL);
        diets.add(Diet.VEGITERIAN);
        for (int i = 0; i < 3; i++) { this.recipeService.save(new Recipe("Good food"+i," recipe with ",Double.valueOf(i),diets));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        User tempUser = new User("Karl Jóhann","pass123");
        List<Recipe> tempRecipe = recipeService.findAll();
        this.userService.save(tempUser);
        try {
            viewLogService.save(new ViewLog(tempRecipe.get(0),tempUser,sdf.parse("21/12/2012"),sdf.parse("31/12/2013") ));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("Recipes", recipeService.findAll());
        return "Velkominn";
    }

    @RequestMapping("/views")
    public String allViews(Model model){
        model.addAttribute("viewLog", viewLogService.findAll());
        return "rentals";
    }

}
