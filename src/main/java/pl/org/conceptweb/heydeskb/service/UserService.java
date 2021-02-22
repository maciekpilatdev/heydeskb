package pl.org.conceptweb.heydeskb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.model.User;
import pl.org.conceptweb.heydeskb.repository.UserRepository;

@Service
public class UserService {

@Autowired
UserRepository userRepository;

    public User deleteUserById(Long userId) throws Exception{
        User user = userRepository.findById(userId).get();
        user.setIsDeleted(Boolean.TRUE);
        user.setActive(Boolean.FALSE);
        userRepository.save(user);
        return user;
    }
}
