
package com.mehmetberkan.SpringBootOpenKmAPI.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/document")
public class DocumentController {
	
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
	
	@GetMapping("/getLockInfo")
	public ResponseEntity<String> getLockInfo(@RequestParam("docId") String docId) {
		
		try {
			ResponseEntity<String> result = restTemplate.getForEntity(webUrl + "/getLockInfo?docId="+docId, String.class);
			System.out.print("getLockInfo = " + result.getBody());
			return ResponseEntity.ok(result.getBody());
		}
		catch (Exception e) {
			System.out.print("Error = " + e.getMessage());
			return ResponseEntity.ok(e.getMessage());
		}		
	}
	
	@GetMapping("/isValid")
	public ResponseEntity<String> isValid(@RequestParam("docId") String docId) {
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl+"/isValid?docId="+docId, String.class);
		System.out.print("isValid = " + result.getBody());
		return ResponseEntity.ok("isValid = " + result.getBody());
	}
	
	@GetMapping("/checkout")
	public ResponseEntity<String> checkout(@RequestParam("docId") String docId) {
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl+"/checkout?docId="+docId, String.class);
		System.out.print("checkOut = " + result.getBody());
		return ResponseEntity.ok("checkOut = " + result.getStatusCode().toString());
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
	
	@PutMapping("/lock")
	public void lock(@RequestParam("docId") String docId) {
		restTemplate.put(webUrl+"/lock?docId="+docId , String.class);	
	}
	
	@PutMapping("/unlock")
	public void unLock(@RequestParam("docId") String docId) {
		restTemplate.put(webUrl+"/unlock?docId="+docId, String.class);
	}
	
	@PutMapping("/rename")
	public void rename(@RequestParam("docId") String docId, @RequestParam("newName") String newName) {
		restTemplate.put(webUrl+"/rename?docId="+docId+"&newName="+newName, String.class);
	}
	
}