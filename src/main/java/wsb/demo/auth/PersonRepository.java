package wsb.demo.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
//    Optional<Person> findByUsernameAndEnabled(String username,Boolean enable);

    List<Person> findByEnable(Boolean enable);
//
//    @Query("select p from Person p where p.dateCreated >= :date order by p.dateCreated desc")
//    Iterable<Person> findEnabledUsersCreatedAfter(@Param("date") Date date);
}

