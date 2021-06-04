package wsb.demo.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
    List<Person> findByEnable(Boolean enable);



}

