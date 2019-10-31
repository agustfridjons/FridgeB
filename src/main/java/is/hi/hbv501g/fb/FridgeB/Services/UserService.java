package is.hi.hbv501g.fb.FridgeB.Services;

import is.hi.hbv501g.fb.FridgeB.Entities.User;

import java.util.List;

public interface UserService {
    User save(User user);
    void delete(User user);
    List<User> findAll();
}
