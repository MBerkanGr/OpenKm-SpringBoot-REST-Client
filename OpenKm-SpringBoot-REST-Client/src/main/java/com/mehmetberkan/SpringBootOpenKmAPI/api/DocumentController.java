package com.mehmetberkan.SpringBootOpenKmAPI.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl+"/getVersionHistory?docId="+docId, String.class);
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
	
	@PutMapping("/copy")
	public void copy(@RequestParam("docId") String docId,@RequestParam("dstId") String dstId) {
		restTemplate.put(webUrl+"/copy?docId="+docId+"&dstId="+dstId, String.class);		
	}
	
	@PutMapping("/move")
	public void move(@RequestParam("docId") String docId,@RequestParam("dstId") String dstId) {
		restTemplate.put(webUrl+"/move?docId="+docId+"&dstId="+dstId, String.class);
	}
	
	@PutMapping("/purgeVersionHistory")
	public void purgeVersionHistory(@RequestParam("docId") String docId) {
		restTemplate.put(webUrl+"/purgeVersionHistory?docId="+docId, String.class);
	}
	
	@PutMapping("/restoreVersion")
	public void restoreVersion(@RequestParam("docId") String docId, @RequestParam String versionId) {
		restTemplate.put(webUrl+"/restoreVersion?docId="+docId+"&versionId="+versionId, String.class);
	}
	
	@PutMapping("/cancelCheckout")
	public void cancelCheckout(@RequestParam("docId") String docId) {
		restTemplate.put(webUrl+"/cancelCheckout?docId="+docId, String.class);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam("docId") String docId) {
		try {
			HttpEntity<String> entity = null;
			ResponseEntity<String> result = restTemplate.exchange(webUrl+"/delete?docId="+docId, HttpMethod.DELETE, entity, String.class);

			if(result.getStatusCodeValue() == 204) {
				System.out.print("successful operation : " + HttpMethod.DELETE.toString());
				return ResponseEntity.ok("Successful Operation : "+ HttpMethod.DELETE.toString());
			}
			else {
				System.out.print("Failed : HTTP error code : " + result.getStatusCodeValue());
				return ResponseEntity.ok("Failed : HTTP error code : " + result.getStatusCodeValue());
			}
		}catch (Exception e) {
			System.out.print("Error : "+e.getMessage());
			return ResponseEntity.ok(e.getMessage());
		}	
	}
}