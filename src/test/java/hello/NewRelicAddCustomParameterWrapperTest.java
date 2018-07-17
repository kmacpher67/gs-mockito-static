package hello;

import com.newrelic.api.agent.NewRelic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NewRelic.class})
public class NewRelicAddCustomParameterWrapperTest {

    @Autowired
    @InjectMocks
    NewRelicAddCustomParameterWrapper newRelicAddCustomParameterWrapper;

    @Before
    public void setUp() throws Exception {

        PowerMockito.mockStatic(NewRelic.class);
        //PowerMockito.spy(NewRelic.class);
    }

    @Test
    public void addCustomParam() throws Exception {
        //GIVEN
        String key = "woopsie-doo1";
        String value = "tippyConue";

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        PowerMockito.doNothing().when(NewRelic.class, "addCustomParameter", captor.capture(), captor2.capture());

        //WHEN
        newRelicAddCustomParameterWrapper.addCustomParam(key, value);

        //THEN
        PowerMockito.verifyStatic( VerificationModeFactory.times(1));
        String resourceKey = captor.getValue();
        String resourceValue = captor2.getValue();
        assertTrue("key equals inpput key", key.contentEquals(resourceKey));
        assertTrue("value value equals input value", value.contentEquals(resourceValue));

    }

    @Test
    public void addCustomParam2() throws Exception {
        //GIVEN
        String key = "woopsie-doo1";
        String value = "tippyConue";

        PowerMockito.spy(NewRelic.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        PowerMockito.doNothing().when(NewRelic.class, "addCustomParameter", captor.capture(), captor2.capture());

        //WHEN
        newRelicAddCustomParameterWrapper.addCustomParam(key, value);

        //THEN
        PowerMockito.verifyStatic( VerificationModeFactory.times(1));
        String resourceKey = captor.getValue();
        String resourceValue = captor2.getValue();
        assertTrue("key equals inpput key", key.contentEquals(resourceKey));
        assertTrue("value value equals input value", value.contentEquals(resourceValue));

    }
}