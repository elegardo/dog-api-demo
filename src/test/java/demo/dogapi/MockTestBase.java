package demo.dogapi;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import demo.dogapi.error.ExceptionController;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class MockTestBase {
    
    protected MockMvc mockMvc;
    protected Object[] mockList;

    @Before
    public void setUp() throws TestingException {
        mockMvc = MockMvcBuilders.standaloneSetup(getMockList())
        							.setControllerAdvice(new ExceptionController())
        							.build();
    }

    protected Object[] getMockList() {
        return mockList;
    }

    protected void setMockList(Object... objectList) {
        this.mockList = objectList;
    }
}
