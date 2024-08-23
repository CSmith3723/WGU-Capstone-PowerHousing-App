package cs.capstone.powerhousing.controllers;

import cs.capstone.powerhousing.entities.SavedProfile;
import cs.capstone.powerhousing.services.ProfileService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SearchController {

    private ProfileService profileService;

    public SearchController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping("/searchProfile")
    public String searchProfile(Model theModel, @Param("keyword")String keyword){

        List<SavedProfile> savedProfiles = profileService.listAll(keyword);
        theModel.addAttribute("savedProfiles", savedProfiles);
        theModel.addAttribute("keyword", keyword);
        return "search-profiles";
    }
}
