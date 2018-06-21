package demo.dogapi.service;

import cl.elegardo.dogapi.domain.Breed;

public interface IRestService {

    public Breed getDataByBreed(String breed);

}
