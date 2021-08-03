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
@RequestMapping("/search")
public class SearchController {
	
	String webUrl = "http://localhost:8080/OpenKM/services/rest/search";
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/find")
	public ResponseEntity<String> find(@RequestParam("find") String find, @RequestParam("selection") String selection){
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl+"/find?"+selection+"="+find, String.class);
		return ResponseEntity.ok(result.getBody());
	}
	
	@GetMapping("/findByName")
	public ResponseEntity<String> findByName(@RequestParam("name") String name) {
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl+"/findByName?name="+name, String.class);
		return ResponseEntity.ok(result.getBody());
	}
	
	@GetMapping("/getCategorizedDocuments/{categoryId}")
	public ResponseEntity<String> getCategorizedDocuments(@PathVariable String categoryId) {
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl+"/getCategorizedDocuments/"+categoryId, String.class);
		return ResponseEntity.ok(result.getBody());
	}
	
	@GetMapping("/getAllSearchs")
	public ResponseEntity<String> getAllSearchs() {
		ResponseEntity<String> result = restTemplate.getForEntity(webUrl+"/getAllSearch", String.class);
		return ResponseEntity.ok(result.getBody());
	}
}