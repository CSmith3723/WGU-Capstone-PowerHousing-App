package cs.capstone.powerhousing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MapViewController {

    @PostMapping("/upload-image")
    @ResponseBody
    public String uploadImage(@RequestBody ImageData imageData) {
        // Process the image data here
        // imageData.getImage() contains the base64 encoded image data

        // You could save the image to a file or database here

        return "Image received";
    }

    @RequestMapping("/display-image")
    public String displayImage(Model model) {
        // Add logic to retrieve image data
        model.addAttribute("imageUrl", "/path/to/saved/image.png");
        return "display";
    }

    public static class ImageData {
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
