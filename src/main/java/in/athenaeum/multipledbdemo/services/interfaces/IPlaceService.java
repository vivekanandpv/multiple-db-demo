package in.athenaeum.multipledbdemo.services.interfaces;

import in.athenaeum.multipledbdemo.entities.place.Place;

import java.util.List;

public interface IPlaceService {
    List<Place> getPlaces();
}
