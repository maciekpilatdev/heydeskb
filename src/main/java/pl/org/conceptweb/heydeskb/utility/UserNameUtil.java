package pl.org.conceptweb.heydeskb.utility;

import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.model.MethodResponse;

@Log
@Component
public class UserNameUtil {

    @Autowired
    UserRepository userRepository;

    public MethodResponse ifUserNameIsProper(String userName, int minLength, int maxLength) {

        MethodResponse methodResponse = new MethodResponse();

        StringBuilder sb = new StringBuilder();
        if (!checkIfUnique(userName)) {
            sb.setLength(0);
            methodResponse.setStatus(Constans.ERROR);
            methodResponse.setMessage(sb.append(Constans.IF_PASSWORD_IDENTICAL_ERROR_MESSAGE).toString());
        };

        if (!ifNotToShortOrToLong(userName, minLength, maxLength)) {
            sb.setLength(0);
            methodResponse.setStatus(Constans.ERROR);
            methodResponse.setMessage(sb.append(Constans.IF_USERNAME_NOT_TO_SHORT_OR_LONG_ERROR_MESSAGE).toString());
        }
        if (sb.length() == 0) {
            sb.append(Constans.IF_USERNAME_IS_PROPER_SUCCESS_MESSAGE);
            methodResponse.setStatus(Constans.OK);
            methodResponse.setMessage(sb.append(Constans.IF_USERNAME_IS_PROPER_SUCCESS_MESSAGE).toString());
        }
        return methodResponse;
    }

    private boolean checkIfUnique(String userName) {
        boolean response = false;
         if (userRepository.findByUserNameWithoutDeleted(userName).isEmpty()) {
            response = true;
        }
        return response;
    }

    private boolean ifNotToShortOrToLong(String userName, int minLength, int maxLength) {
        boolean response = false;
        if (userName.length() >= minLength && userName.length() <= maxLength) {
            response = true;
        }
        log.log(Level.INFO, "UserNameUtil: ifNotToShortOrToLong: " + response);
        return response;
    }
}
