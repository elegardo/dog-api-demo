package demo.dogapi.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.dogapi.domain.Response;
import demo.dogapi.service.IRestService;

@RestController
@RequestMapping("/v1/{breed}")
public class GETController {
	
	@Autowired
	private IRestService service;

	@RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Response> get(@PathVariable String breed) {
		return new ResponseEntity<Response>(this.service.getDataByBreed(breed), HttpStatus.OK);	
	}
	
}
