package cent_35.demo.Controllers;

import cent_35.demo.services.OpenStreetMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OpenStreetMapController {
    private final OpenStreetMapService openStreetMapService;

    @Autowired
    public OpenStreetMapController(OpenStreetMapService openStreetMapService) {
        this.openStreetMapService = openStreetMapService;
    }

    @GetMapping("/location")
    public ResponseEntity<String> getLocation(@RequestParam String query){
        String locationData = openStreetMapService.getLocationData(query);
        return ResponseEntity.ok(locationData);
    }



}
