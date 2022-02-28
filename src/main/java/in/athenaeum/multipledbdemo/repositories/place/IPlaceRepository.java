package in.athenaeum.multipledbdemo.repositories.place;

import in.athenaeum.multipledbdemo.entities.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlaceRepository extends JpaRepository<Place, Long> {
}
