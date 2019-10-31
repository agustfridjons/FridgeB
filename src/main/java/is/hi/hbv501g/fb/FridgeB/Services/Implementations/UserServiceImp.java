package is.hi.hbv501g.fb.FridgeB.Services.Implementations;

import is.hi.hbv501g.fb.FridgeB.Entities.User;
import is.hi.hbv501g.fb.FridgeB.Repositories.UserRepo;
import is.hi.hbv501g.fb.FridgeB.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    UserRepo repository;

    @Autowired
    public UserServiceImp(UserRepo repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}