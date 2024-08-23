package cs.capstone.powerhousing.controllers;

import cs.capstone.powerhousing.entities.UserInfo;
import cs.capstone.powerhousing.services.UserService;
import cs.capstone.powerhousing.user.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NewUserController {

    UserService userService;

    public NewUserController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/createUser")
    public String createUser(Model theModel){
        theModel.addAttribute("webUser", new WebUser());
        theModel.addAttribute("success", false);
        return "create-user";
    }

    @PostMapping("/processRegistration")
    public String processRegistration(@Valid @ModelAttribute("webUser") WebUser webUser,
                                      BindingResult bindingResult,
                                      HttpSession session,
                                      Model theModel) {


        UserInfo existingUser = userService.findByUsername(webUser.getUsername());
        if(existingUser != null){
            theModel.addAttribute("webUser", new WebUser());
            theModel.addAttribute("registrationError",
                    "Username already exists.");
            System.out.println("Username already exists.");
            return "create-user";
        }

        if(webUser.getUsername() == null || webUser.getUsername().length() < 4){
            theModel.addAttribute("usernameLengthError","Username must be at least 4 characters long.");
            return "create-user";
        }

        if(webUser.getPassword() == null || webUser.getPassword().length() < 5){
            theModel.addAttribute("passwordLengthError", "Password must be at least 5 characters long.");
            return "create-user";
        }

        if(!webUser.getPassword().equals(webUser.getConfirmPassword())){
            System.out.println("mismatch");
            theModel.addAttribute("passwordMatchError",
                    "Password and Confirm Password do not match.");
            return "create-user";
        }

        try {

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            UserInfo newUser = new UserInfo();
            newUser.setUsername(webUser.getUsername());
            newUser.setUserPassword(bCryptPasswordEncoder.encode(webUser.getPassword()));

            userService.register(webUser);
            theModel.addAttribute("success", true);
            System.out.println("Successfully created user: " + webUser);

        }catch (Exception ex){
            ex.printStackTrace();
            return "create-user";
        }

        return "home";
    }

}
