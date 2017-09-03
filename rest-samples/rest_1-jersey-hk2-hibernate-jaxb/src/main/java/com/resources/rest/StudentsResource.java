package com.resources.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.entity.Address;
import com.entity.Gender;
import com.entity.Student;
import com.exceptions.UnsupportedGenderException;
import com.services.StudentService;

/**
 * Rest resources for all Students.
 * @author Aritra
 */
@Path("/students")
public class StudentsResource {
	@Context
	private UriInfo uriInfo;
	@Context
	private Request request;
	@Inject private StudentService studentService;
	//private StudentService studentService = new StudentService();

	//@Inject private StudentResource studentResource;


	@GET
	@Path("allstudents")
	//@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getStudents() {
		List<Student> listOfAllStudents = studentService.getStudentsAsList();
		//GenericEntity<List<Student>> output = new GenericEntity<List<Student>>(listOfAllStudents) {};
		//return Response.ok(output).build();
		return Response.ok(listOfAllStudents.toString()).build();
	}

	@GET
	@Path("allstudentsastxt")
	//@Produces(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getStudentsAsHtml() {
		List<Student> output = studentService.getStudentsAsList();
		return Response.ok(output.toString()).build();
	}

	// URI: /rest/students/count
	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCount() {
		return String.valueOf(studentService.getStudentsCount());
	}
	
	// Defines that the next path parameter after students is treated as a parameter
	// and passed to the StudentResuorce
    // Allows to type http://localhost:8080/com.vogella.jersey.todo/rest/students/1
    // 1 will be treaded as parameter studentid and passed to StudentResource
	/*
	@Path("{studentid}")
	public Response getStudent(@PathParam("studentid") String studentID) {
		//return new StudentResource(uriInfo, request, id);
		studentResource.setUriInfo(uriInfo);
		studentResource.setRequest(request);
		studentResource.setStudentID(Integer.parseInt(studentID));
		
	}
	*/
	

}
