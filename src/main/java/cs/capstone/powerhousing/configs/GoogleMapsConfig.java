package cs.capstone.powerhousing.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class GoogleMapsConfig {

    @Value("${google.maps.default.city}")
    private String defaultCity;


}
