package pl.org.conceptweb.heydeskb.inputTest;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class InputTestsLibrary {

    public Boolean isNotNull(String text) {
        if (text.equals(null)) {
            return false;
        }
        return true;
    }

    public Boolean isLengthAppropriate(int minLength, int maxLength, String text) {
        if (text.length() >= minLength && text.length() <= maxLength) {
            return true;
        }
        return false;
    }

    public Boolean isEmailAddress(String text) {
        if (text.contains("@") && text.contains(".")) {
            return true;
        }
        return false;
    }

    public Boolean isNumber(String numberString) {
        try {
            double d = Double.parseDouble(numberString);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public Boolean areRepeated(String password, String repeatedPassword){
        if(!password.equals(repeatedPassword)){
            return false;
        }
        return true;
    }
}
