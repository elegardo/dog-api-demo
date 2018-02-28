package demo.dogapi.service.impl;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import demo.dogapi.domain.APIResult;
import demo.dogapi.domain.ResponseImage;
import demo.dogapi.domain.Response;
import demo.dogapi.error.ServiceException;
import demo.dogapi.service.IRestService;

@Service
public class RestServiceImpl implements IRestService {
	
	@Value("${api.timeout}")
	private int apiTimeout;
	
	private final RestTemplate restTemplate;

    public RestServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        setTimeout(restTemplate);
    }
    
    @Override
	public Response getDataByBreed(String breed) {
		
        Response response = new Response();
        response.setBreed(breed);
        
        try {
			response.setSubBreeds(getSubBreedByBreed(breed));
			response.setImages(getImagesByBreed(breed));
		} catch (ConnectException | ResourceAccessException e) {
			throw new ServiceException("No se pudo conectar a la API de Dog API");
		}
        
		return response;
	}
    
    private List<String> getSubBreedByBreed(String breed) throws ConnectException, ResourceAccessException {
		String breedListURL = String.format("https://dog.ceo/api/breed/%s/list", breed);
		APIResult breedListResult = restTemplate.getForObject(breedListURL, APIResult.class);
		
		return breedListResult.getMessage();    	
    }
    
    private List<ResponseImage> getImagesByBreed(String breed) throws ConnectException, ResourceAccessException {
		String breedImagesURL = String.format("https://dog.ceo/api/breed/%s/images", breed);
		APIResult breedImagesResult = restTemplate.getForObject(breedImagesURL, APIResult.class);
		
        List<ResponseImage> images = new ArrayList<ResponseImage>();
        for(String i:breedImagesResult.getMessage()) {
        		images.add(new ResponseImage(i));
        }
        
        return images;    	
    }
    
    private void setTimeout(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        rf.setReadTimeout(this.apiTimeout);
        rf.setConnectTimeout(this.apiTimeout);
    }

}
