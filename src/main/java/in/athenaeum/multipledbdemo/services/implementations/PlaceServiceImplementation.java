package in.athenaeum.multipledbdemo.services.implementations;

import in.athenaeum.multipledbdemo.entities.place.Place;
import in.athenaeum.multipledbdemo.repositories.place.IPlaceRepository;
import in.athenaeum.multipledbdemo.services.interfaces.IPlaceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImplementation implements IPlaceService {
    private final IPlaceRepository repository;

    public PlaceServiceImplementation(IPlaceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Place> getPlaces() {
        return repository.findAll();
    }
}
