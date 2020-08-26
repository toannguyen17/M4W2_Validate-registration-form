package redt.repository;

import org.springframework.data.repository.CrudRepository;
import redt.model.Users;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Long> {
    Optional<Users> findById(Long id);
    Optional<Users> findByEmail(String email);
}
