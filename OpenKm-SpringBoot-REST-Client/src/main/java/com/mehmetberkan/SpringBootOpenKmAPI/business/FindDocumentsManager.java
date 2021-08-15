package com.mehmetberkan.SpringBootOpenKmAPI.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindDocumentsManager implements FindDocumentsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public String findDocument(String path) {
		String webUrl = "http://localhost:8080/OpenKM/services/rest/search";
		String result = restTemplate.getForObject(webUrl+"/find?path="+path, String.class);
		System.out.print(result);
		return result;
	}
	
}
