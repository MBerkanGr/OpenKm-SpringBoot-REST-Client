package com.mehmetberkan.SpringBootOpenKmAPI.business;

import org.springframework.stereotype.Component;

@Component
public class ResponseCheckManager implements ResponseCheckService {

	@Override
	public String check(int statusCode, String method) {
		
		if(statusCode == 204 || statusCode == 200 ) {
			System.out.print("successful operation : " + method);
			return "Successful Operation : "+ method;
		}
		else {
			System.out.print("Failed : HTTP error code : " + statusCode);
			return "Failed : HTTP error code : " + statusCode;
		}	
	}
}
