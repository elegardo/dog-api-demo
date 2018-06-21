package cl.elegardo.dogapi.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.elegardo.dogapi.controller.GETController;
import cl.elegardo.dogapi.domain.Breed;
import cl.elegardo.dogapi.error.NotFoundException;
import cl.elegardo.dogapi.error.ResponseError;
import cl.elegardo.dogapi.error.ServiceException;
import cl.elegardo.dogapi.test.MockTestBase;
import cl.elegardo.dogapi.test.TestingException;
import demo.dogapi.service.IRestService;

public class GETControllerTest extends MockTestBase {

    private String endpoint = "/v1/breed/";

    @InjectMocks
    private GETController restController = new GETController();

    @Mock
    private IRestService service;

    @Before
    public void setUp() throws TestingException {
        super.setMockList(restController);
        super.setUp();
    }

    @Test
    public void getTest_status_200() throws TestingException {
        try {
            String breed = "terrier";

            when(service.getDataByBreed(any(String.class))).thenReturn(new Breed());

            MockHttpServletResponse responseRestService;
            responseRestService = mockMvc
                    .perform(get(endpoint + breed)
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
            String breed = "quiltro";

            when(service.getDataByBreed(any(String.class))).thenThrow(new NotFoundException("no existe"));

            ResultActions result;
            result = mockMvc
                    .perform(get(endpoint + breed)
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print());

            result.andExpect(MockMvcResultMatchers.status().isNotFound());

        } catch (Exception e) {
            throw new TestingException(e.getMessage(), e);
        }
    }

    @Test
    public void getTest_status_500() throws TestingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String breed = "quiltro";
            ResponseError myError = new ResponseError(500, "internal error");

            when(service.getDataByBreed(any(String.class))).thenThrow(new ServiceException("internal error"));

            MockHttpServletResponse responseRestService;
            responseRestService = mockMvc
                    .perform(get(endpoint + breed)
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andReturn()
                    .getResponse();

            ResponseError responseError = objectMapper.readValue(responseRestService.getContentAsString(),ResponseError.class);

            assertEquals(INTERNAL_SERVER_ERROR.value(), responseRestService.getStatus());
            assertEquals(myError.getStatus(), responseError.getStatus());
            assertEquals(myError.getMessage(), responseError.getMessage());

        } catch (Exception e) {
            throw new TestingException(e.getMessage(), e);
        }
    }

}