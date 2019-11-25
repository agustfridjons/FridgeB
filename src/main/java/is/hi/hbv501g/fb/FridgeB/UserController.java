package is.hi.hbv501g.fb.FridgeB;

import is.hi.hbv501g.fb.FridgeB.Entities.User;
import is.hi.hbv501g.fb.FridgeB.Services.RecipeService;
import is.hi.hbv501g.fb.FridgeB.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;
    private RecipeService recipeService;

    @Autowired
    public UserController(UserService userService,RecipeService recipeService){
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @RequestMapping("/login")
    public String Login(User user){
        return "logIn";
    }

    @RequestMapping("/signup")
    public  String Signup(User user){
        return "signUp";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpGET(User user){
        return "signUp";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpPOST(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "signUp";
        }
        User exists = userService.findByUName(user.getUName());
        if(exists == null){
            System.out.println(user);
            userService.save(user);
        }else{
            return "signUp";
        }
        model.addAttribute("Recipes", recipeService.findAll());
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){
        return "logIn";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@Valid User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "login";
        }
        model.addAttribute("Recipes",recipeService.findAll());
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", user);
            return "redirect:/";
        }
        return "redirect:/";
    }

}
