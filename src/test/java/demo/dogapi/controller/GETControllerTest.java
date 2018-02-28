package demo.dogapi.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import demo.dogapi.ControllerTestBase;
import demo.dogapi.TestingException;
import demo.dogapi.domain.Response;
import demo.dogapi.error.NotFoundException;
import demo.dogapi.service.IRestService;

public class GETControllerTest extends ControllerTestBase {

	private String endpoint = "/v1/";

	@InjectMocks
	private GETController restController = new GETController();

	@Mock
	private IRestService service;

	@Before
	public void setUp() throws TestingException {
		super.setControllerList(restController);
		super.setUp();
	}

	@Test
	public void getTest_status_200() throws TestingException {
		try {
			String breed = "chiguagua";

			when(service.getDataByBreed(any(String.class))).thenReturn(new Response());

			MockHttpServletResponse responseRestService;
			responseRestService = mockMvc
					.perform(
							get(endpoint + breed)
							.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andReturn()
					.getResponse();

			assertEquals(OK.value(), responseRestService.getStatus());
			assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, responseRestService.getContentType());

		} catch (Exception e) {
			throw new TestingException(e.getMessage(), e);
		}
	}

	@Test
	public void getTest_status_404() throws TestingException {
		try {
			String breed = "chiguagua";

			when(service.getDataByBreed(any(String.class))).thenThrow(new NotFoundException("no existe"));

			ResultActions result;
			result = mockMvc
					.perform(
							get(endpoint + breed)
							.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
							.accept(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print());
			
			result.andExpect(MockMvcResultMatchers.status().isNotFound());

		} catch (Exception e) {
			throw new TestingException(e.getMessage(), e);
		}
	}
}