package demo.dogapi.domain;

import java.util.List;

public class Response {
	
	private String breed;
	private String[] subBreeds;
	private List<Image> images;
	
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String[] getSubBreeds() {
		return subBreeds;
	}
	public void setSubBreeds(String[] subBreeds) {
		this.subBreeds = subBreeds;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}

}
