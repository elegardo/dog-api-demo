package cl.elegardo.dogapi.domain;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel("Breed")
public class Breed {

    private String breed;
    private List<String> subBreeds;
    private List<BreedImage> images;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public List<String> getSubBreeds() {
        return subBreeds;
    }

    public void setSubBreeds(List<String> subBreeds) {
        this.subBreeds = subBreeds;
    }

    public List<BreedImage> getImages() {
        return images;
    }

    public void setImages(List<BreedImage> images) {
        this.images = images;
    }

}
