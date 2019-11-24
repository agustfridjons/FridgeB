package is.hi.hbv501g.fb.FridgeB.Services.Implementations;

import is.hi.hbv501g.fb.FridgeB.Entities.User;
import is.hi.hbv501g.fb.FridgeB.Repositories.UserRepo;
import is.hi.hbv501g.fb.FridgeB.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    UserRepo repo;

    @Autowired
    public UserServiceImp(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public void delete(User user) {
        repo.delete(user);
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findByUName(String name) {
        return repo.findByUName(name);
    }

    @Override
    public Optional<User> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public User login(User user){
        User exists = findByUName(user.getUName());
        if(exists != null){
            if(exists.getPassword().equals(user.getPassword())){
               return user;
            }
        }
        return null;
    }

}