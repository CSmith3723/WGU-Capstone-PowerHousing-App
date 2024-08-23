package cs.capstone.powerhousing.controllers;

import cs.capstone.powerhousing.configs.GoogleMapsConfig;
import cs.capstone.powerhousing.entities.Calculator;
import cs.capstone.powerhousing.entities.Housing;
import cs.capstone.powerhousing.entities.SavedProfile;
import cs.capstone.powerhousing.entities.UserInfo;
import cs.capstone.powerhousing.services.CalculatorService;
import cs.capstone.powerhousing.services.HousingService;
import cs.capstone.powerhousing.services.ProfileService;
import cs.capstone.powerhousing.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CalculatorController {

    private UserService userService;
    private CalculatorService calculatorService;
    private HousingService housingService;
    private ProfileService profileService;

    public CalculatorController(CalculatorService calculatorService, HousingService housingService, ProfileService profileService, UserService userService){
        this.calculatorService = calculatorService;
        this.housingService = housingService;
        this.profileService = profileService;
        this.userService = userService;
    }


    @GetMapping("/calculator")
    public String showCalculator(Model theModel, HttpSession session, GoogleMapsConfig googleMapsConfig){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if(username.equals("anonymousUser")) {
            username = "Guest";
        }

        theModel.addAttribute("username", username);
        theModel.addAttribute("calculator", new Calculator());
        theModel.addAttribute("housing", new Housing());
        theModel.addAttribute("currentUser", username);

        return "calculator-form";
    }


    @PostMapping("/processCalculation")
    public String processForm(@ModelAttribute("calculator") Calculator calculator,
                              @ModelAttribute("neighborhood") String neighborhood,
                              @ModelAttribute("housing") Housing housing,
                              Model theModel){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        SavedProfile savedProfile = new SavedProfile();
        UserInfo existingUser = userService.findByUsername(username);

        calculator.setGrossMonthlyWages(calculatorService.calculateGrossWages(calculator));
        calculator.setNetMonthlyWages(calculatorService.calculateNetWages(calculator));
        housing.setHousingType(housing.getHousingType());
        housing.setNeighborhood(housing.getNeighborhood());
        housing.setCurrentPriceByHousingType(housingService.priceByHousingType(neighborhood,housing.getHousingType()));

        if(existingUser != null) {
            try {
                savedProfile.setUsername(username);
                savedProfile.setNeighborhood(housing.getNeighborhood());
                savedProfile.setHousingType(housing.getHousingType());
                savedProfile.setHousingPrice(housing.getCurrentPriceByHousingType());
                savedProfile.setGrossMonthlyWages(calculator.getGrossMonthlyWages());
                savedProfile.setMonthlyExpenses(calculator.getMonthlyExpenses());
                savedProfile.setNetMonthlyWages(calculator.getNetMonthlyWages());

                profileService.saveProfile(savedProfile);
                System.out.println("Profile saved successfully!");

                theModel.addAttribute("saveSuccess", true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            theModel.addAttribute("saveFail", true);
        }

        theModel.addAttribute("housing", housing);
        theModel.addAttribute("calculator",calculator);
        theModel.addAttribute("currentUser", username);

        return "calculator-confirmation";
    }



    @ModelAttribute("neighborhoodList")
    public List<String> neighborhoods(){
        List<String> neighborhoods = housingService.neighborhoodsList();
        return neighborhoods;
    }
}
