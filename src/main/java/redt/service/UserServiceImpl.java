package redt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redt.model.Users;
import redt.repository.UserRepository;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserRepository repository;

    public UserServiceImpl(){
    }


    @Override
    public Optional<Users> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void save(Users user) {
        repository.save(user);
    }
}
