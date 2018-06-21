package cl.elegardo.dogapi.domain;

import io.swagger.annotations.ApiModel;

@ApiModel("Image")
public class BreedImage {

    final private String url;

    public BreedImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
