package pl.org.conceptweb.heydeskb.inputTest;

import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.model.MethodResponse;

@Log
@Component
public class NumberStrategy implements InputTestStrategyInterface {

    @Autowired
    InputTestsLibrary inputTestsLibrary;

    @Override
    public MethodResponse runTest(String input) {
        String status = Constans.OK;
        StringBuilder message = new StringBuilder();
        if (!inputTestsLibrary.isNotNull(input)) {
            status = Constans.ERROR;
            message.append(Constans.IS_NOT_NULL_ERROR_MESSAGE);
        } else if (!inputTestsLibrary.isNumber(input)) {
            status = Constans.ERROR;
            message.append(Constans.IS_NUMBER_ERROR_MESSAGE);
        }
        return new MethodResponse(status, message.toString());
    }
}
