package in.athenaeum.multipledbdemo.services.implementations;

import in.athenaeum.multipledbdemo.entities.person.Person;
import in.athenaeum.multipledbdemo.repositories.person.IPersonRepository;
import in.athenaeum.multipledbdemo.services.interfaces.IPersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImplementation implements IPersonService {
    private final IPersonRepository repository;

    public PersonServiceImplementation(IPersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> getPeople() {
        return repository.findAll();
    }
}
