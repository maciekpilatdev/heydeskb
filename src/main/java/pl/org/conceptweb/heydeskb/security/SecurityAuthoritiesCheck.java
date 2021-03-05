package pl.org.conceptweb.heydeskb.security;

import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.repository.UserRepository;

@Log
@Component
public class SecurityAuthoritiesCheck {

    @Autowired
    UserRepository userRepository;

    public boolean hasAuthority(String userName, String authority) {
        
        String userAuthoritys = userRepository.findByUserNameWithoutDeleted(userName).getAuthoritys();
        boolean hasAuthority = false;
        try {
            hasAuthority = userAuthoritys.contains(authority);            
        } catch (Exception e) {
            log.log(Level.WARNING, "ERROR: SecurityAuthoritiesCheck: hasAuthority: " + e);
        }
        return hasAuthority;
    }
}
