package pl.com.conceptweb.uniappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.conceptweb.uniappbackend.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}