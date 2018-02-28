package demo.dogapi.controller.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import demo.dogapi.MockTestBase;
import demo.dogapi.TestingException;
import demo.dogapi.service.impl.RestServiceImpl;

public class RestServiceImplTest extends MockTestBase {
	
	@InjectMocks
	private RestServiceImpl restService = new RestServiceImpl();

	@Mock
	private RestTemplate restTemplate;
	
	@Before
	public void setUp() throws TestingException {
		super.setMockList(restService);
		super.setUp();
	}

	@Test
	public void getTest_service_1() throws TestingException {
		try {

			assertEquals(true, true);

		} catch (Exception e) {
			throw new TestingException(e.getMessage(), e);
		}
	}
	
}
