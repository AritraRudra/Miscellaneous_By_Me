package com.example.project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class NewsController {

	@Autowired
	private NewsService newsService;

	@RequestMapping(value = "/api/news/topstories")
	public @ResponseBody ResponseEntity<Object> getNews() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("results", Arrays.asList(newsService.getTopStories()));
		map.put("section","Test");
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

}
