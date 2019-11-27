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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class RecipeController {

    private RecipeService recipeService;
    private UserService userService;
    private List<String> ingredients;

    @Autowired
    public RecipeController(UserService userService, RecipeService recipeService){
        this.userService = userService;
        this.recipeService = recipeService;
    }
    @RequestMapping("/")
    public String Home(Model model, HttpSession session){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser != null){
            System.out.println(sessionUser);
            model.addAttribute("user",sessionUser);
        }
        model.addAttribute("Recipes", recipeService.findAll());
        return "home";
    }

    @RequestMapping(value = "/addrecipe",method = RequestMethod.POST)
    public String addRecipe(@Valid Recipe recipe, BindingResult results, Model model){
        if(results.hasErrors()){
            model.addAttribute("error", "Not a valid recipe");
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

    @RequestMapping(value="/view/{id}", method = RequestMethod.GET)
    public String viewRecipe(@PathVariable("id") long id, Model model){
        Recipe recipe = recipeService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Recipe Id"));
        this.ingredients = recipeService.findIngredients(recipe.getIngredients());
        model.addAttribute("selectedRecipe", recipe);
        model.addAttribute("ingredient",this.ingredients);
        return "viewRecipe";
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
        List<Recipe> recipe = recipeService.searchByKey(search);
        //System.out.println(recipe.get(0));
        model.addAttribute("Recipes", recipe);
        return "home";
    }

    @RequestMapping(value= "/rate/{id}", method = RequestMethod.POST)
    public String rateRecipe(@RequestParam(value = "rate", required = false) int rate, @PathVariable("id") long id, Model model){
        Recipe recipe = recipeService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Recipe Id"));
        String ratings = recipe.getRatings() + rate;
        recipe.setRatings(ratings);
        recipe.setRating(recipeService.calculateRating(ratings));
        recipeService.save(recipe);
        model.addAttribute("selectedRecipe", recipe);
        model.addAttribute("ingredient",this.ingredients);
        return "viewRecipe";
    }

    @RequestMapping("/view")
    public String view(){
        return "viewRecipe";
    }

    @RequestMapping("/makedata")
    public String makeData(Model model){
        String[] img = {"https://images2.minutemediacdn.com/image/upload/c_crop,h_1126,w_2000,x_0,y_181/f_auto,q_auto,w_1100/v1554932288/shape/mentalfloss/12531-istock-637790866.jpg",
                        "https://cdn.popmenu.com/image/upload/c_limit,f_auto,h_1440,q_auto,w_1440/j7gunhkdbqkgwhpfl808.jpg",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQes9Iim0-rPaLoelNDzqjuleF18o4OKQzZewSPhZTk_JpDxdz1&s"};
        String[] food = {"Eggs 2pcs","Beef 500g","Milk 0.5L"};
        /*HashSet<Diet> diets = new HashSet<>();
        diets.add(Diet.CLASSIC);
        diets.add(Diet.VEGITERIAN);*/
        for (int i = 1; i <= 3; i++) {
            this.recipeService.save(new Recipe("Good food "+i," recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with recipe with ","23544"+i,img[i-1],"Chicken 1kg, Bacon 200g, Lettuce 100g"+food[i-1],Double.valueOf(i) /*,diets*/));
        }
        User tempUser = new User("Karl","pass",true);
        this.userService.save(tempUser);
        model.addAttribute("Recipes", recipeService.findAll());
        return "home";
    }

}
