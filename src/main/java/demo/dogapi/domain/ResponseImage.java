package demo.dogapi.domain;

import io.swagger.annotations.ApiModel;

@ApiModel("Image")
public class ResponseImage {

	private String url;
	
	public ResponseImage(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
