package pl.org.conceptweb.heydeskb.utility;

import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.constans.Constans;

@Log
@Component
public class PasswordUtil {

    public String ifPasswordIsProper(String password, String repeatedPassword, int minLength, int maxLength) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sukces!");
        if (!ifPasswordIdentical(password, repeatedPassword)) {
            sb.setLength(0);
            sb.append(Constans.IF_PASSWORD_IDENTICAL_ERROR_MESSAGE);
        };

        if (!ifPasswordNotToShortOrLong(password, minLength, maxLength)) {
            sb.setLength(0);
            sb.append(Constans.IF_PASSWORD_NOT_TO_SHORT_OR_LONG_ERROR_MESSAGE);
        }

        if (sb.capacity() == 0) {
            sb.append(Constans.IF_PASSWORD_IS_PROPER_SUCCESS_MESSAGE);
        }
        return sb.toString();
    }

    private boolean ifPasswordIdentical(String password, String repeatedPassword) {
        boolean response = false;
        if (password.equals(repeatedPassword)) {
            response = true;
        }
        log.log(Level.INFO, "PasswordUtil: ifPasswordIdentical: " + response);
        return response;
    }

    private boolean ifPasswordNotToShortOrLong(String password, int minLength, int maxLength) {
        boolean response = false;
        if (password.length() >= minLength && password.length() <= maxLength) {
            response = true;
        }
        log.log(Level.INFO, "PasswordUtil: ifPasswordNotToShortOrLong: " + response);
        return response;
    }
}
