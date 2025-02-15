package pl.org.conceptweb.heydeskb.inputTest;

import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.configuration.AppConfiguration;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.model.MethodResponse;

@Log
@Component
public class PasswordStrategy implements InputTestStrategyInterface {

    @Autowired
    InputTestsLibrary inputTestsLibrary;

    @Override
    public MethodResponse runTest(String comaSeperatedPasswords) {
        String[] passwords;
        String status = Constans.OK;
        StringBuilder message = new StringBuilder();
        try {
            passwords = comaSeperatedPasswords.split(",");
            if (!inputTestsLibrary.isLengthAppropriate(AppConfiguration.MIN_INPUT_LENGTH, AppConfiguration.MAX_INPUT_LENGTH, passwords[0])) {
                status = Constans.ERROR;
                message.append(Constans.IS_LENGTH_APPROPRIATE_ERROR_MESSAGE);
            } else if (!inputTestsLibrary.areRepeated(passwords[0], passwords[1])) {
                status = Constans.ERROR;
                message.append(Constans.IF_PASSWORD_IDENTICAL_ERROR_MESSAGE);
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            log.log(Level.WARNING, "PasswordStrategy: runTest: ", e);
            return new MethodResponse(Constans.ERROR, Constans.INADEQUATE_DATA);
        }
        return new MethodResponse(status, message.toString());
    }
}
