package cs.capstone.powerhousing.RestControllers;

import cs.capstone.powerhousing.entities.Housing;
import cs.capstone.powerhousing.services.HousingService;
import cs.capstone.powerhousing.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NeighborhoodController {

    HousingService housingService;
    UserService userService;

    public NeighborhoodController(HousingService housingService, UserService userService) {
        this.housingService = housingService;
        this.userService = userService;
    }


    // --- NEIGHBORHOOD INFO --- //

    @GetMapping("/neighborhoodInfo")
    public List<Housing> neighborhoodInfo(){
        return housingService.findAll();
    }


    @GetMapping("/oneBedAptPrices")
    public List<Integer> apartmentPrices(){
        return housingService.oneBRAptPrices();
    }

    @GetMapping("{neighborhood}")
    public Housing findNeighborhood(@PathVariable String neighborhood){
        return housingService.findByNeighborhood(neighborhood);
    }

    @GetMapping("{neighborhood}/oneBed")
    public int oneBedPrice(@PathVariable String neighborhood){
        return housingService.oneBedPriceByNeighborhood(neighborhood);
    }

}
