NewRelic static Mockito Unit Test
Sample test for springboot and PowerMockito on Static methods test for NewRelic.

Wrote this simple sample test case to figure out why random errors are occuring in the PowerMockito static tests. 

SAMPLE Code for Unit tests on 
            NewRelic.addCustomParameter(key, value);

## Run the test a few times. 

NewRelic unit test in src/test: 

Class: `NewRelicAddCustomParameterWrapperTest`
     
Run the gradle test or right click and run the class or individual methods multiple times, eventually, the test will fail with this: 

```hello.NewRelicAddCustomParameterWrapperTest > addCustomParam FAILED
       org.mockito.exceptions.base.MockitoException at NewRelicAddCustomParameterWrapperTest.java:47
   
   
   No argument value was captured!
   You might have forgotten to use argument.capture() in verify()...
   ...or you used capture() in stubbing but stubbed method was not called.
   Be aware that it is recommended to use capture() only with verify()
   
   Examples of correct argument capturing:
       ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
       verify(mock).doSomething(argument.capture());
       assertEquals("John", argument.getValue().getName());
   
   org.mockito.exceptions.base.MockitoException: 
   No argument value was captured!
   You might have forgotten to use argument.capture() in verify()...
   ...or you used capture() in stubbing but stubbed method was not called.
   Be aware that it is recommended to use capture() only with verify()
   
   Examples of correct argument capturing:
       ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
       verify(mock).doSomething(argument.capture());
       assertEquals("John", argument.getValue().getName());
   
   	at hello.NewRelicAddCustomParameterWrapperTest.addCustomParam(NewRelicAddCustomParameterWrapperTest.java:47)
   	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
   	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
   	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
   	at java.lang.reflect.Method.invoke(Method.java:497)
   	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:68)
```

It fails on the `        String resourceKey = captor.getValue();`
because the method invocation doesn't get matched. 

It's completely random and sporadic, you might have to run test tests a few times to get it to fail the tests. 

HowTO: unit test
         PowerMockito.mockStatic(NewRelic.class);


See question: 
https://stackoverflow.com/questions/51385533/race-condition-behavior-static-newrelic-addcustomparameterkey-value-using-po

