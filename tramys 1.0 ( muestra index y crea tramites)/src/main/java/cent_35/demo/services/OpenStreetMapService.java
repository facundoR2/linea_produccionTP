package cent_35.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenStreetMapService {

    private final RestTemplate restTemplate;

    @Autowired
    public OpenStreetMapService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public String getLocationData(String query){
        String url = "https://nominatim.openstreetmap.org/search?q="+query+"&format=json&addressdetails=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        return response.getBody();
    }
}
