package demo.dogapi.service;

import demo.dogapi.domain.Response;

public interface IRestService {
	
	public Response getDataByBreed(String breed);
	
}
