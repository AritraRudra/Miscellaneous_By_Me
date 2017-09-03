package com.resources.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/simple")
public class SimpleResources {
	private static final String message = "Hello Jersey";

	@GET
	public String sayDefaultHello() {
		return message;
	}

	// This method is called if TEXT_PLAIN is request
	@Path("text")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return message+ MediaType.TEXT_PLAIN;
	}

	// This method is called if XML is request
	@Path("xml")
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello>"+ message + MediaType.TEXT_XML + "</hello>";
	}

	// This method is called if HTML is request
	@Path("html")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + message + "</title>" + "<body><h1>" + message + MediaType.TEXT_HTML + "</h1></body>" + "</html> ";
	}
	
	// This method is called if JSON is request
	@Path("json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayJSONHello() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(message + MediaType.APPLICATION_JSON );
	}

}
