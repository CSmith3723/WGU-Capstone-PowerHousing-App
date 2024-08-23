package cs.capstone.powerhousing.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@PropertySource(value={"classpath:application.properties"})
public class MapService {

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public MapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchByCity(String cityName) {
        // Endpoint for searching addresses
        String url = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/place/textsearch/json")
                .queryParam("query", cityName)
                .queryParam("key", apiKey)
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}
