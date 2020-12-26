package pl.org.conceptweb.heydeskb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.org.conceptweb.heydeskb.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}