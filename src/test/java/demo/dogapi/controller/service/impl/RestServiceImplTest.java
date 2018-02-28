package demo.dogapi.controller.service.impl;

import static org.junit.Assert.assertEquals;

import java.net.ConnectException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import demo.dogapi.MockTestBase;
import demo.dogapi.TestingException;
import demo.dogapi.api.APISuccess;
import demo.dogapi.domain.Breed;
import demo.dogapi.domain.BreedImage;
import demo.dogapi.error.ServiceException;
import demo.dogapi.service.impl.RestServiceImpl;
import static org.mockito.Mockito.when;

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
	public void getTest_service_sucessWithoutImages() throws TestingException {
		try {

			String breedName = "pitbull";
			
			Breed myBreed = new Breed();
			myBreed.setBreed(breedName);
			
			mockUriBreed(breedName);
		    
		    APISuccess sucessTest = new APISuccess();
		    sucessTest.setStatus("success");
		    sucessTest.setMessage(new ArrayList<String>());
		    
		    mockUriBreedImages(breedName, sucessTest);
		    
	        Breed returnBreed = restService.getDataByBreed(breedName);
	        
	        assertEquals(myBreed.getBreed(), returnBreed.getBreed());

		} catch (Exception e) {
			throw new TestingException(e.getMessage(), e);
		}
	}
	
	@Test
	public void getTest_service_sucessWithImages() throws TestingException {
		try {

			String breedName = "pitbull";
			
			Breed myBreed = new Breed();
			myBreed.setBreed(breedName);
			myBreed.setImages(new ArrayList<BreedImage>());
			myBreed.getImages().add(new BreedImage("https://image1.jpg"));
			myBreed.getImages().add(new BreedImage("https://image2.jpg"));
			myBreed.getImages().add(new BreedImage("https://image3.jpg"));
			
			mockUriBreed(breedName);
		    
		    APISuccess sucessTest = new APISuccess();
		    sucessTest.setStatus("success");
		    sucessTest.setMessage(new ArrayList<String>());
		    sucessTest.getMessage().add("https://image1.jpg");
		    sucessTest.getMessage().add("https://image2.jpg");
		    sucessTest.getMessage().add("https://image3.jpg");
		    
		    mockUriBreedImages(breedName, sucessTest);
		    
	        Breed returnBreed = restService.getDataByBreed(breedName);
	        
	        assertEquals(myBreed.getBreed(), returnBreed.getBreed());
	        assertEquals(myBreed.getImages().size(), returnBreed.getImages().size());

		} catch (Exception e) {
			throw new TestingException(e.getMessage(), e);
		}
	}
	
	@Test(expected=ServiceException.class)
	public void getTest_service_errorTimeout() {

			String breedName = "pitbull";
			
			Breed myBreed = new Breed();
			myBreed.setBreed(breedName);
			
			mockUriBreedTimeout(breedName);
		    
			when(restService.getDataByBreed(breedName)).thenThrow(new ServiceException("error"));
	}
	
	private void mockUriBreed(String breedName) {
		ResponseEntity<String> uriBreedTest = new ResponseEntity<String>("{\"status\":\"success\",\"message\":[]}", HttpStatus.OK);
	    Mockito.when(restTemplate.exchange(
	    		Matchers.eq("https://dog.ceo/api/breed/"+breedName+"/list"),
	    		Matchers.eq(HttpMethod.GET),
	    		Matchers.<HttpEntity<Void>>any(),
	    		Matchers.<Class<String>>any())
	    	).thenReturn(uriBreedTest);		
	}
	
	@SuppressWarnings("unchecked")
	private void mockUriBreedTimeout(String breedName) {
	    Mockito.when(restTemplate.exchange(
	    		Matchers.eq("https://dog.ceo/api/breed/"+breedName+"/list"),
	    		Matchers.eq(HttpMethod.GET),
	    		Matchers.<HttpEntity<Void>>any(),
	    		Matchers.<Class<String>>any())
	    	).thenThrow(ConnectException.class);		
	}
	
	private void mockUriBreedImages(String breedName, APISuccess sucessTest) {
	    Mockito.when(restTemplate.getForObject(
	    		Matchers.eq("https://dog.ceo/api/breed/"+breedName+"/images"),
	    		Matchers.<Class<APISuccess>>any())
	    	).thenReturn(sucessTest);		
	}
	
}
