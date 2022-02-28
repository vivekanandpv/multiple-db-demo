package in.athenaeum.multipledbdemo.services.interfaces;

import in.athenaeum.multipledbdemo.entities.person.Person;

import java.util.List;

public interface IPersonService {
    List<Person> getPeople();
}
