package redt.service;

import redt.model.Users;

import java.util.Optional;

public interface UserService {
    Optional<Users> findById(Long id);
    Optional<Users> findByEmail(String email);
    void save(Users user);
}
