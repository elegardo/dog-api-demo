package cl.elegardo.dogapi.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.elegardo.dogapi.domain.Breed;
import cl.elegardo.dogapi.error.ResponseError;
import demo.dogapi.service.IRestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/breed/{breed}")
public class GETController {

    @Autowired
    private IRestService service;

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "GET", value = "get")
    @ApiResponses(value = { 
            @ApiResponse(code = 404, message = "Not Found", response = ResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseError.class)
            })
    public ResponseEntity<Breed> get(@PathVariable String breed) {
        return new ResponseEntity<Breed>(this.service.getDataByBreed(breed), HttpStatus.OK);
    }

}
