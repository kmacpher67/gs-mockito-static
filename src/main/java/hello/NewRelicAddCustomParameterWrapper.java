package hello;

import com.newrelic.api.agent.NewRelic;
import org.springframework.stereotype.Component;

@Component
public class NewRelicAddCustomParameterWrapper {

    public void addCustomParam(final String key, final String value) {
        // ADD US17232 for NR custom parameters transaction-id, request, user, parent-initator
        // Create a component to wrapper the static NewRelic
        try {
            NewRelic.addCustomParameter(key, value);
        } catch (Exception newRCustomException) {
            newRCustomException.printStackTrace();
        }
    }
}
