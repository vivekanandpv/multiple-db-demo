package in.athenaeum.multipledbdemo.apis;

import in.athenaeum.multipledbdemo.entities.person.Person;
import in.athenaeum.multipledbdemo.services.interfaces.IPersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/persons")
public class PersonApi {
    private final IPersonService service;

    public PersonApi(IPersonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(service.getPeople());
    }
}
