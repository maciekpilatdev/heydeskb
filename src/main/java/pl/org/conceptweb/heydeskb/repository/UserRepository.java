package pl.org.conceptweb.heydeskb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.org.conceptweb.heydeskb.model.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    
    @Query("select u from User u where u.userName = ?1 and u.isDeleted = false")
    public List<User> findByUserNameWithoutDeleted(String userName);
    
    @Query("select u from User u "
            + "join u.companyDb c "
            + "where c.id = ?1 and u.isDeleted = false")
    public List<User> findAllUsersByCompany(Long companyId);
}
