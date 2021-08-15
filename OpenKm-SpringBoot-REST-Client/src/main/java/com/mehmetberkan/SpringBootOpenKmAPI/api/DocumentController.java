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

import com.mehmetberkan.SpringBootOpenKmAPI.business.FindDocumentsService;
import com.mehmetberkan.SpringBootOpenKmAPI.business.ResponseCheckService;

@Controller
@RequestMapping("/document")
public class DocumentController {
	
	String webUrl = "http://localhost:8080/OpenKM/services/rest/document";
	
	@Autowired
	private ResponseCheckService checkManager;
	
	@Autowired
	private FindDocumentsService findManager;

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/getPath/{id}")
	public ResponseEntity<String> getPath(@PathVariable String docId) {	
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl + "/getPath/"+docId, String.class);
		System.out.print("Path = " + result.getBody());
		return ResponseEntity.ok("Path = " + result.getBody());
	}
	
	@GetMapping("/getDocumentsInFolder")
	public ResponseEntity<String> getDocumentsInFolder(@RequestParam("folderId") String folderId) {
		String path = "http://localhost:8080/OpenKM/services/rest/folder/getPath/";
		ResponseEntity<String> result = restTemplate.getForEntity(path+folderId, String.class);
		System.out.print(result.getBody());
		return ResponseEntity.ok(findManager.findDocument(result.getBody()));
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
	public ResponseEntity<String> lock(@RequestParam("docId") String docId) {
		try {
			HttpEntity<String> entity = null;
			ResponseEntity<String> result = restTemplate.exchange(webUrl+"/lock?docId="+docId , HttpMethod.PUT, entity, String.class);	
			return ResponseEntity.ok(checkManager.check(result.getStatusCodeValue(), HttpMethod.PUT.toString()));
		}catch (Exception e) {
			System.out.print("Error : "+e.getMessage());
			return ResponseEntity.ok(e.getMessage());
		}	
	}	
			
	@PutMapping("/unlock")
	public ResponseEntity<String> unLock(@RequestParam("docId") String docId) {
		try {
			HttpEntity<String> entity = null;
			ResponseEntity<String> result = restTemplate.exchange(webUrl+"/unlock?docId="+docId, HttpMethod.PUT, entity, String.class); 
			return ResponseEntity.ok(checkManager.check(result.getStatusCodeValue(), HttpMethod.PUT.toString()));
		}catch (Exception e) {
			System.out.print("Error : "+e.getMessage());
			return ResponseEntity.ok(e.getMessage());
		}		
	}
	
	@PutMapping("/rename")
	public ResponseEntity<String> rename(@RequestParam("docId") String docId, @RequestParam("newName") String newName) {
		try {
			HttpEntity<String> entity = null;
			ResponseEntity<String> result = restTemplate.exchange(webUrl+"/rename?docId="+docId+"&newName="+newName, HttpMethod.PUT, entity,String.class);
			return ResponseEntity.ok(checkManager.check(result.getStatusCodeValue(), HttpMethod.PUT.toString()));
		}catch (Exception e) {
			System.out.print("Error : "+e.getMessage());
			return ResponseEntity.ok(e.getMessage());
		}		
	}
	
	@PutMapping("/copy")
	public ResponseEntity<String> copy(@RequestParam("docId") String docId,@RequestParam("dstId") String dstId) {
		try {
			HttpEntity<String> entity = null;	
			ResponseEntity<String> result = restTemplate.exchange(webUrl+"/copy?docId="+docId+"&dstId="+dstId, HttpMethod.PUT, entity, String.class);	
			return ResponseEntity.ok(checkManager.check(result.getStatusCodeValue(), HttpMethod.PUT.toString()));
		}catch (Exception e) {
			System.out.print("Error : "+e.getMessage());
			return ResponseEntity.ok(e.getMessage());
		}		
	}
	
	@PutMapping("/move")
	public ResponseEntity<String> move(@RequestParam("docId") String docId,@RequestParam("dstId") String dstId) {
		try {
			HttpEntity<String> entity = null;
			ResponseEntity<String> result = restTemplate.exchange(webUrl+"/move?docId="+docId+"&dstId="+dstId, HttpMethod.PUT, entity, String.class);
			return ResponseEntity.ok(checkManager.check(result.getStatusCodeValue(), HttpMethod.PUT.toString()));
		}catch (Exception e) {
			System.out.print("Error : "+e.getMessage());
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@PutMapping("/purgeVersionHistory")
	public ResponseEntity<String> purgeVersionHistory(@RequestParam("docId") String docId) {
		try {
			HttpEntity<String> entity = null;
			ResponseEntity<String> result = restTemplate.exchange(webUrl+"/purgeVersionHistory?docId="+docId, HttpMethod.PUT, entity, String.class);
			return ResponseEntity.ok(checkManager.check(result.getStatusCodeValue(), HttpMethod.PUT.toString()));
		}catch (Exception e) {
			System.out.print("Error : "+e.getMessage());
			return ResponseEntity.ok(e.getMessage());
		}		
	}
	
	@PutMapping("/restoreVersion")
	public ResponseEntity<String> restoreVersion(@RequestParam("docId") String docId, @RequestParam String versionId) {
		try {
			HttpEntity<String> entity = null;
			ResponseEntity<String> result = restTemplate.exchange(webUrl+"/restoreVersion?docId="+docId+"&versionId="+versionId, HttpMethod.PUT, entity, String.class);
			return ResponseEntity.ok(checkManager.check(result.getStatusCodeValue(), HttpMethod.PUT.toString()));
		}catch (Exception e) {
			System.out.print("Error : "+e.getMessage());
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@PutMapping("/cancelCheckout")
	public ResponseEntity<String> cancelCheckout(@RequestParam("docId") String docId) {
		try {
			HttpEntity<String> entity = null;
			ResponseEntity<String> result = restTemplate.exchange(webUrl+"/cancelCheckout?docId="+docId, HttpMethod.PUT, entity, String.class);
			return ResponseEntity.ok(checkManager.check(result.getStatusCodeValue(), HttpMethod.PUT.toString()));
		}catch (Exception e) {
			System.out.print("Error : "+e.getMessage());
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam("docId") String docId) {
		try {
			HttpEntity<String> entity = null;
			ResponseEntity<String> result = restTemplate.exchange(webUrl+"/delete?docId="+docId, HttpMethod.DELETE, entity, String.class);
			return ResponseEntity.ok(checkManager.check(result.getStatusCodeValue(), HttpMethod.DELETE.toString()));
		}catch (Exception e) {
			System.out.print("Error : "+e.getMessage());
			return ResponseEntity.ok(e.getMessage());
		}	
	}
	
}