package demo.dogapi.domain;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel("Response")
public class Response {
	
	private String breed;
	private List<String> subBreeds;
	private List<ResponseImage> images;
	
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
	public List<ResponseImage> getImages() {
		return images;
	}
	public void setImages(List<ResponseImage> images) {
		this.images = images;
	}

}
