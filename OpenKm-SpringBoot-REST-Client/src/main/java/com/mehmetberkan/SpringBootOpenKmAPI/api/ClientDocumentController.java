package com.mehmetberkan.SpringBootOpenKmAPI.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;



@Controller
@RequestMapping("/test")
public class ClientDocumentController {
	
	String webUrl = "http://localhost:8080/OpenKM/services/rest/document";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/getPath/{id}")
	public ResponseEntity<String> getPath(@PathVariable String docId) {
		
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl + "/getPath/"+docId, String.class);
		System.out.print("Path = " + result.getBody());
		return ResponseEntity.ok("Path = " + result.getBody());
	}
	
	@GetMapping("/getProperties")
	public ResponseEntity<String> getProperties(@RequestParam("docId") String docId) {
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl + "/getProperties?docId="+docId, String.class);
		System.out.print("getProperties = " + result.getBody());
		return ResponseEntity.ok("getProperties = " + result.getBody());
	}
	
	@GetMapping("/isLocked")
	public ResponseEntity<String> isLocked(@RequestParam("docId") String docId) {
		
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl + "/isLocked?docId="+docId, String.class);
		System.out.print("isLocked = " + result.getBody());
		return ResponseEntity.ok("isLocked = " + result.getBody());
	}
	
	@GetMapping("/isValid")
	public ResponseEntity<String> isValid(@RequestParam("docId") String docId) {
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl+"/isValid?docId="+docId, String.class);
		System.out.print("isValid = " + result.getBody());
		return ResponseEntity.ok("isValid = " + result.getBody());
	}
	
	@GetMapping("/isCheckedOut")
	public ResponseEntity<String> isCheckedOut(@RequestParam("docId") String docId) {
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl+"/isCheckedOut?docId="+docId, String.class, docId);
		System.out.print("isCheckedOut = " + result.getBody());
		return ResponseEntity.ok("isCheckedOut = " + result.getBody());
	}
	
	@GetMapping("/getVersionHistory")
	public ResponseEntity<String> getVersionHistory(@RequestParam("docId") String docId) {
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl+"/getVersionHistory?docId="+docId, String.class, docId);
		System.out.print("getVersionHistory = " + result.getBody());
		return ResponseEntity.ok("getVersionHistory = " + result.getBody());
	}
	
	
}