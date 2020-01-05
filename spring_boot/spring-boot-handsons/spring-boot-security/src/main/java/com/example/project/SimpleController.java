package com.example.project;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class SimpleController {

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String getCurrentCourses() throws Exception {
		return "SpringBoot";
	}

}
