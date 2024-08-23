package cs.capstone.powerhousing.controllers;

import cs.capstone.powerhousing.entities.SavedProfile;
import cs.capstone.powerhousing.services.ProfileService;
import cs.capstone.powerhousing.services.ReportService;
import cs.capstone.powerhousing.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProfileController {

    ProfileService profileService;
    UserService userService;
    ReportService reportService;

    public ProfileController(ProfileService profileService, UserService userService, ReportService reportService) {
        this.profileService = profileService;
        this.userService = userService;
        this.reportService = reportService;
    }

    @GetMapping("/report")
    public String viewReport(Model theModel){
        try{
            String reportHtml = reportService.generateHTMLReport();
            theModel.addAttribute("reportHtml", reportHtml);
            return "profiles-report";
        }catch (Exception e){
            theModel.addAttribute("reportError");
            return "profiles-report";
        }
    }


    @GetMapping("/saved")
    public String viewSaved(Model theModel){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<SavedProfile> userProfiles = profileService.findAllByUsername(username);
        theModel.addAttribute("userProfiles", userProfiles);
        return "saved-profiles";
    }

    @PostMapping("/exportToPDF")
    public void exportToPDF(HttpServletResponse response){

        try {
            byte[] pdfContent = reportService.generatePDF();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"profiles_report.pdf\"");
            response.setContentLength(pdfContent.length);

            response.getOutputStream().write(pdfContent);
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
