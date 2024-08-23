package cs.capstone.powerhousing.RestControllers;

import cs.capstone.powerhousing.services.MapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maps")
public class MapAPIController {

    private final MapService mapService;

    public MapAPIController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/search")
    public String searchCity(@RequestParam String cityName) {
        return mapService.searchByCity(cityName);
    }



}
