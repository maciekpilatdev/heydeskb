package pl.org.conceptweb.heydeskb.inputTest;

import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.MethodResponse;

@Component
public class InputTester {

    public MethodResponse runTest(InputTestStrategyInterface testStrategy, String input) {
        return testStrategy.runTest(input);
    }
}
