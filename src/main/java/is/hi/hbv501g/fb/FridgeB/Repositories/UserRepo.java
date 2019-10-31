package is.hi.hbv501g.fb.FridgeB.Repositories;

import is.hi.hbv501g.fb.FridgeB.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User save(User user);
    void delete(User user);
    List<User> findAll();
}
