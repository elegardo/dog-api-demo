package demo.dogapi;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ControllerTestBase {
    
    protected MockMvc mockMvc;
    protected Object[] controllerList;

    @Before
    public void setUp() throws TestingException {
        mockMvc = MockMvcBuilders.standaloneSetup(getControllerList()).build();
    }

    protected Object[] getControllerList() {
        return controllerList;
    }

    protected void setControllerList(Object... controllers) {
        this.controllerList = controllers;
    }
}
