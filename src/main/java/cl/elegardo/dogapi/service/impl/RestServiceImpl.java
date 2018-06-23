package cl.elegardo.dogapi.service.impl;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.elegardo.dogapi.api.APIError;
import cl.elegardo.dogapi.api.APISuccess;
import cl.elegardo.dogapi.domain.Breed;
import cl.elegardo.dogapi.domain.BreedImage;
import cl.elegardo.dogapi.error.NotFoundException;
import cl.elegardo.dogapi.error.ServiceException;
import cl.elegardo.dogapi.service.IRestService;

@Service
public class RestServiceImpl implements IRestService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Breed getDataByBreed(String breed) {

        Breed response = new Breed();
        response.setBreed(breed);

        try {
            response.setSubBreeds(getSubBreedByBreed(breed));
            response.setImages(getImagesByBreed(breed));
        } catch (ConnectException | ResourceAccessException e) {
            throw new ServiceException("No se pudo conectar a la API de Dog API");
        } catch (IOException e) {
            throw new ServiceException("Error: No es posible obtener la informacion solicitada desde la API de Dog API");
        }

        return response;
    }

    private List<String> getSubBreedByBreed(String breed)
            throws ResourceAccessException, JsonParseException, JsonMappingException, IOException {

        APISuccess breedResult;
        ObjectMapper objectMapper = new ObjectMapper();
        String breedListURL = String.format("https://dog.ceo/api/breed/%s/list", breed);

        HttpEntity<Void> requestEntity = new HttpEntity<Void>(new HttpHeaders());
        ResponseEntity<String> uri = restTemplate.exchange(breedListURL, HttpMethod.GET, requestEntity, String.class);

        if (uri.getStatusCode().value() == 200 && uri.getBody().contains("success")) {
            breedResult = objectMapper.readValue(uri.getBody(), APISuccess.class);
        } else if (uri.getStatusCode().value() == 200 && uri.getBody().contains("error")) {
            APIError error = objectMapper.readValue(uri.getBody(), APIError.class);
            if ("404".equals(error.getCode())) {
                throw new NotFoundException("La raza solicitada no fue encontrada en la API de Dog API");
            } else {
                throw new ServiceException("Error: La API de Dog API entrega status:" + uri.getStatusCode() +  ", y el mensaje:" + error.getMessage());
            }
        } else {
            throw new ServiceException("Error: La API de Dog API entrega el siguiente status:" + uri.getStatusCode());
        }

        return breedResult.getMessage();
    }

    private List<BreedImage> getImagesByBreed(String breed) throws ConnectException, ResourceAccessException {
        String breedImagesURL = String.format("https://dog.ceo/api/breed/%s/images", breed);
        APISuccess breedResult = restTemplate.getForObject(breedImagesURL, APISuccess.class);

        List<BreedImage> images = new ArrayList<BreedImage>();
        for (String i : breedResult.getMessage()) {
            images.add(new BreedImage(i));
        }

        return images;
    }

}
