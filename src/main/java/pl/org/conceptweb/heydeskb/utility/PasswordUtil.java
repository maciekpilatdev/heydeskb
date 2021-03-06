package pl.org.conceptweb.heydeskb.utility;

import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.model.MethodResponse;

@Log
@Component
public class PasswordUtil {

    public MethodResponse ifPasswordIsProper(String password, String repeatedPassword, int minLength, int maxLength) {
        MethodResponse methodResponse = new MethodResponse();
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        
        if (!ifPasswordIdentical(password, repeatedPassword)) {
            sb.setLength(0);
            methodResponse.setStatus(Constans.ERROR);
            methodResponse.setMessage(sb.append(Constans.IF_PASSWORD_IDENTICAL_ERROR_MESSAGE).toString());
        };
        if (!ifPasswordNotToShortOrLong(password, minLength, maxLength)) {
            sb.setLength(0);
            methodResponse.setStatus(Constans.ERROR);
            methodResponse.setMessage(sb.append(Constans.IF_PASSWORD_NOT_TO_SHORT_OR_LONG_ERROR_MESSAGE).toString());
        }
        if (sb.length() == 0) {
            methodResponse.setStatus(Constans.OK);
            methodResponse.setMessage(sb.append(Constans.IF_PASSWORD_IS_PROPER_SUCCESS_MESSAGE).toString());
        }
        return methodResponse;
    }

    private boolean ifPasswordIdentical(String password, String repeatedPassword) {
        boolean response = false;
        if (password.equals(repeatedPassword)) {
            response = true;
        }
        return response;
    }

    private boolean ifPasswordNotToShortOrLong(String password, int minLength, int maxLength) {
        boolean response = false;
        if (password.length() >= minLength && password.length() <= maxLength) {
            response = true;
        }
        return response;
    }
}
