package is.hi.hbv501g.fb.FridgeB.Services;

import is.hi.hbv501g.fb.FridgeB.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    void delete(User user);
    List<User> findAll();
    User findByUName(String name);
    Optional<User> findById(long id);
    User login(User user);
}
