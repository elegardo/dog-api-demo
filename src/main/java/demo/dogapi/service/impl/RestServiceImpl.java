package demo.dogapi.service.impl;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import demo.dogapi.domain.APIResult;
import demo.dogapi.domain.Image;
import demo.dogapi.domain.Response;
import demo.dogapi.error.ServiceException;
import demo.dogapi.service.IRestService;

@Service
public class RestServiceImpl implements IRestService {
	
	private final RestTemplate restTemplate;

    public RestServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        setTimeout(restTemplate, 10000);
    }
    
    @Override
	public Response getDataByBreed(String breed) {
		
        Response response = new Response();
        response.setBreed(breed);
        
        try {
			response.setSubBreeds(getSubBreedList(breed));
			response.setImages(getImagesList(breed));
		} catch (ConnectException | ResourceAccessException e) {
			throw new ServiceException("No se pudo conectar a la API");
		}
        
		return response;
	}
    
    private List<String> getSubBreedList(String breed) throws ConnectException, ResourceAccessException {
		String breedListURL = String.format("https://dog.ceo/api/breed/%s/list", breed);
		APIResult breedListResult = restTemplate.getForObject(breedListURL, APIResult.class);
		return breedListResult.getMessage();    	
    }
    
    private List<Image> getImagesList(String breed) throws ConnectException, ResourceAccessException {
		String breedImagesURL = String.format("https://dog.ceo/api/breed/%s/images", breed);
		APIResult breedImagesResult = restTemplate.getForObject(breedImagesURL, APIResult.class);
		
        List<Image> images = new ArrayList<Image>();
        for(String i:breedImagesResult.getMessage()) {
        		images.add(new Image(i));
        }
        
        return images;    	
    }
    
    private void setTimeout(RestTemplate restTemplate, int timeout) {
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        rf.setReadTimeout(timeout);
        rf.setConnectTimeout(timeout);
    }

}
