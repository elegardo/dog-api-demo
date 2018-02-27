package demo.dogapi.service.impl;

import org.springframework.stereotype.Service;

import demo.dogapi.domain.Response;
import demo.dogapi.service.IRestService;

@Service
public class RestServiceImpl implements IRestService {

	@Override
	public Response getDataByBreed(String breed) {
		// TODO Auto-generated method stub
		return new Response();
	}

}
