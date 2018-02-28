package demo.dogapi.domain;

import io.swagger.annotations.ApiModel;

@ApiModel("Image")
public class BreedImage {

	private String url;
	
	public BreedImage(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
