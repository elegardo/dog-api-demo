package demo.dogapi.service;

import demo.dogapi.domain.Breed;

public interface IRestService {
	
	public Breed getDataByBreed(String breed);
	
}
