package pl.org.conceptweb.heydeskb.inputTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.configuration.AppConfiguration;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.model.MethodResponse;

@Component
public class EmailStrategy implements InputTestStrategyInterface {

    @Autowired
    InputTestsLibrary inputTestsLibrary;

    @Override
    public MethodResponse runTest(String input) {
        String status = Constans.OK;
        StringBuilder message = new StringBuilder();
        if (!inputTestsLibrary.isNotNull(input)) {
            status = Constans.ERROR;
            message.append(Constans.IS_NOT_NULL_ERROR_MESSAGE);
        } else if (!inputTestsLibrary.isLengthAppropriate(AppConfiguration.MIN_INPUT_LENGTH, AppConfiguration.MAX_INPUT_LENGTH, input)) {
            status = Constans.ERROR;
            message.append(Constans.IS_LENGTH_APPROPRIATE_ERROR_MESSAGE);
        } else if (!inputTestsLibrary.isEmailAddress(input)) {
            status = Constans.ERROR;
            message.append(Constans.IS_EEMAIL_ADDRESS_ERROR_MESSAGE);
        }
        return new MethodResponse(status, message.toString());
    }
}
