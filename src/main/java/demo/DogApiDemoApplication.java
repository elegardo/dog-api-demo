package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DogApiDemoApplication {

    @Value("${api.timeout}")
    private int apiTimeout;

    public static void main(String[] args) {
        SpringApplication.run(DogApiDemoApplication.class, args);
    }

    @Bean
    public RestTemplate getRestClient() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();

        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        rf.setReadTimeout(this.apiTimeout);
        rf.setConnectTimeout(this.apiTimeout);

        return restTemplate;
    }
}
