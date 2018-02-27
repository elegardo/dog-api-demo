package demo.dogapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import demo.dogapi.domain.APIResult;
import demo.dogapi.domain.Image;
import demo.dogapi.domain.Response;
import demo.dogapi.service.IRestService;

@Service
public class RestServiceImpl implements IRestService {
	
	private final RestTemplate restTemplate;

    public RestServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
    @Override
	public Response getDataByBreed(String breed) {
		
		String breedListURL = String.format("https://dog.ceo/api/breed/%s/list", breed);
		APIResult breedListResult = restTemplate.getForObject(breedListURL, APIResult.class);
		
		String breedImagesURL = String.format("https://dog.ceo/api/breed/%s/images", breed);
		APIResult breedImagesResult = restTemplate.getForObject(breedImagesURL, APIResult.class);
		
        List<Image> images = new ArrayList<Image>();
        for(String i:breedImagesResult.getMessage()) {
        		images.add(new Image(i));
        }
        
        Response response = new Response();
        response.setBreed(breed);
        response.setSubBreeds(breedListResult.getMessage());
        response.setImages(images);
        
		return response;
	}

}
