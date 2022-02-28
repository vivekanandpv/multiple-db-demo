package in.athenaeum.multipledbdemo.apis;

import in.athenaeum.multipledbdemo.entities.place.Place;
import in.athenaeum.multipledbdemo.services.interfaces.IPlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/places")
public class PlaceApi {
    private final IPlaceService service;

    public PlaceApi(IPlaceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Place>> getAll() {
        return ResponseEntity.ok(service.getPlaces());
    }
}
