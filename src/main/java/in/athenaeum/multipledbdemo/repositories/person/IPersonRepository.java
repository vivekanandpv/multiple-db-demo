package in.athenaeum.multipledbdemo.repositories.person;

import in.athenaeum.multipledbdemo.entities.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
}
