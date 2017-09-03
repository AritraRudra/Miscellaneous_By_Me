package com.resources.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.annotations.Loggable;
import com.entity.Address;
import com.entity.Gender;
import com.entity.Student;
import com.exceptions.UnsupportedGenderException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.StudentService;

/**
 * Rest resources for each Student.
 * 
 * @author Aritra
 */
@Loggable
@Path("/student")
// @RequestScoped
//@Stateless
public class StudentResource {
	private final String className= StudentResource.class.getName();
	protected static final Logger LOGGER = LoggerFactory.getLogger(StudentResource.class);
	@Context
	private UriInfo uriInfo;
	@Context
	private Request request;
	@Context HttpServletRequest httpRequest;
	@Inject
	private StudentService studentService;
	// private final StudentService studentService = new StudentService();

	
	@GET
	@Path("{studentid}")
	// @Produces(MediaType.TEXT_HTML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentByID(@PathParam("studentid") String studentIDStr) {
		LOGGER.info("In getStudentByID PathParam "+studentIDStr);
		final int studentID = Integer.parseInt(studentIDStr);
		final Student student = studentService.getStudentByID(studentID);
		if (student == null)
			return Response.status(200).entity("No Student entry found with ID " + studentID).build();
		// return
		// Response.status(200).entity(prepereResponseAsHTML(student)).build();
		try {
			return Response.status(200).entity(prepereResponseAsJSON(student, null)).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@GET
	//@Path("{studentid}")	// in this case, url should be "rest/student/studentid?studentid=1". Otherwise "rest/student/?studentid=1"
	//@Produces(MediaType.TEXT_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentByID(
			@DefaultValue("-1") @QueryParam("studentid") int studentID) {
		LOGGER.info("In getStudentByID QueryParam "+studentID);
		String requestUri = uriInfo.getRequestUri().toString();
		LOGGER.info("RequestUri : {} {}",requestUri, httpRequest.getRequestURI());
		//final int studentID = Integer.parseInt(studentIDStr);
		if(studentID == -1){
			return Response.status(422).build();		// Unknown code
		}
		final Student student = studentService.getStudentByID(studentID);
		if (student == null)
			return Response.status(200).entity("No Student entry found with ID " + studentID).build();
		// return
		// Response.status(200).entity(prepereResponseAsHTML(student)).build();
		/*
		try {
			return Response.status(200).entity(prepereResponseAsJSON(student, null)).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
		*/
		//return Response.ok().entity(student).build();
		try {
			return Response.status(201).entity(prepereResponseAsJSON(student, null)).build();
			/*
			// when direct json MessageBodyWriter is setup properly.
			prepereResponseAsJSON(student, null);
			return Response.status(201).entity(student).build();
			*/
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	// rest/student/10/age
	// Get age of student having ID 10.
	// https://www.mkyong.com/webservices/jax-rs/jax-rs-pathparam-example/
	@GET
	@Path("{studentid}/{property}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getStudentPropertyById(@PathParam("studentid") String studentIDStr,
			@PathParam("property") String studentPropertyStr) {
		final int studentID = Integer.parseInt(studentIDStr);

		final boolean validProperty = isValidStudentProperty(studentPropertyStr);
		if (validProperty) {
			final Student student = studentService.getStudentByID(studentID);
			if (student == null)
				return Response.status(200).entity("No Student entry found with ID " + studentID).build();
			Object obj = getStudentProperty(student, studentPropertyStr);
			return Response.status(200).entity(obj).build();
		} else {
			return Response.status(200).entity("Invalid property for Student with ID : " + studentID).build();
		}
	}

	
	@POST
	@Path("addstudent_form_url_encoded_multivaluedmap")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	// check what entries are present in formparams 
	public Response addStudent(MultivaluedMap<String, String> formParams){
		// https://stackoverflow.com/questions/6097166/how-do-i-read-post-parameters-for-a-restful-service-using-jersey => https://stackoverflow.com/a/7926440/1679643
		// java 8
		Map<String, List<String>> formParamsAsReceivedByBackend = new HashMap<>();
		formParams.forEach((k,v)->formParamsAsReceivedByBackend.put(k, v));
		
		// Java 7
		//Map<String, List<String>> formParamsAsReceivedAtBackend = new HashMap<>();
		for(Entry<String, List<String>> entry : formParamsAsReceivedByBackend.entrySet()) {
			LOGGER.info(entry.getKey()+" "+ entry.getValue());
	    }
	    LOGGER.info("");
		
		try {
			return Response.status(200).entity(new ObjectMapper().writeValueAsString(formParamsAsReceivedByBackend)).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return Response.serverError().entity(e).build();
		}
	}
			
	@POST
	@Path("addstudent_form_url_encoded")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudent1(
			@FormParam("age") String paramAge, @FormParam("dob") String paramDob,
			@FormParam("firstname") String paramFirstName, @FormParam("lastname") String paramLastName,
			@FormParam("gender") String paramGender, @FormParam("institutename") String paramInstituteName,
			@FormParam("street") String paramStreet, @FormParam("zipcode") String paramZipcode,
			@FormParam("city") String paramCity, @FormParam("country") String paramCountry) {
		// @Context HttpServletResponse servletResponse) throws IOException {
		LOGGER.info("\n\n"+paramAge +" "+paramDob+" "+paramGender+ " "+paramCountry);
		int age = 0;
		Date dob;
		Gender gender;
		int zipCode = 0;
		Address address;
		if (paramAge != null && !paramAge.trim().isEmpty()) {
			age = Integer.parseInt(paramAge);
		} else {
			return Response.status(406).entity("Invalid input for Age. Age can not be empty.").build();
		}
		
		if (paramDob != null && !paramDob.trim().isEmpty()) {
			DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			try {
				dob = formatter.parse(paramDob);
			} catch (ParseException e) {
				e.printStackTrace();
				return Response.status(406).entity("Invalid input for Date of Birth.").build();
			}
		} else {
			return Response.status(406).entity("Invalid input for Date of Birth, it can not be empty.").build();
		}
		
		if (paramGender != null && !paramGender.trim().isEmpty()) {
			try {
				gender = Gender.getGenderByValue(paramGender.toUpperCase());
			} catch (UnsupportedGenderException genderEx) {
				genderEx.printStackTrace();
				return Response.status(406).entity(genderEx.getMessage()).build();
			}
		} else {
			return Response.status(406).entity("Invalid input for Gender.").build();
		}
		
		if(paramLastName == null || paramLastName.trim().isEmpty())
			return Response.status(406).entity("Invalid input for Last Name.").build();
		
		if(paramZipcode != null && !paramZipcode.trim().isEmpty()){
			zipCode = Integer.parseInt(paramZipcode);
		}
		if (paramCountry != null && !paramCountry.trim().isEmpty()) {
			address = new Address(paramStreet, paramCity, paramCountry, zipCode);
		} else {
			return Response.status(406).entity("Invalid input for Address/Country.").build();
		}

		Student student = new Student(age, dob, gender, paramFirstName, paramLastName, paramInstituteName, address);
		
		studentService.addStudent(student);
		
		LOGGER.debug("Added Student details along with address.");
		
		// servletResponse.sendRedirect("./success/");
		String successMessage = "Student details successfully added.";
		
		try {
			return Response.status(201).entity(prepereResponseAsJSON(student, successMessage)).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("addstudent")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudent(Student student) {
		LOGGER.info("Entering {}.addStudent for JSON", className);
		try {
			studentService.addStudent(student);
			LOGGER.debug("Successfully added Student details along with address.");
			// servletResponse.sendRedirect("./success/");
			String successMessage = "Student details successfully added.";
			return Response.status(201).entity(prepereResponseAsJSON(student, successMessage)).build();
		} catch (Exception e) {
			LOGGER.error("Exception occurred : {}", e.getMessage());
			return Response.serverError().entity(e.getMessage()).build();
		}
	}



	// TODO HTTP PUT method
	


	@DELETE
	@Path("{studentid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStudentByID(@PathParam("studentid") String studentIDStr) {
		LOGGER.info("In deleteStudentByID PathParam " + studentIDStr);
		String message = null;
		int studentID = -1;
		try {
			try {
				studentID = Integer.parseInt(studentIDStr);
			} catch (final NumberFormatException nfEx) {
				LOGGER.error("NumberFormatException occurred : {}", nfEx.getMessage());
				message = "Invalid input for Student ID, it should be a positive integer.";
				return Response.status(406).entity(prepereResponseAsJSON(null, message)).build();
			}
			if (studentID > 0) {
				final boolean studentDeleted = studentService.deleteStudentByID(studentID);
				if (studentDeleted) {
					message = "Student with ID " + studentID + " successfully deleted.";
					return Response.status(200).entity(prepereResponseAsJSON(null, message)).build();
				} else {
					message = "No Student with ID " + studentID + " found.";
					return Response.status(200).entity(prepereResponseAsJSON(null, message)).build();
				}
			}else{
				LOGGER.error("Invalid Student ID : {}", studentID);
				message = "Invalid input for Student ID, ot should be apositive integer.";
				return Response.status(406).entity(prepereResponseAsJSON(null, message)).build();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	

	private static Object getStudentProperty(Student student, String studentPropertyStr) {
		Object obj;
		obj = student.getAddress();
		return obj;
	}

	private static boolean isValidStudentProperty(String studentPropertyStr) {
		// TODO Auto-generated method stub
		return true;
	}

	static private String prepereResponseAsHTML(Student student) {
		final String output = "<html> " + "<title>" + "Operation Result" + "</title>" + "<body><h3>"
				+ "Response from server is : " + student.toString() + "</h3></body>" + "</html>";
		return output;
	}

	private static String prepereResponseAsJSON(final Object object, String messageToAdd) throws JsonProcessingException {
		ObjectMapper jsonMapped = new ObjectMapper();
		String jsonString = null;
		if(object != null && messageToAdd != null)
			jsonString = jsonMapped.writeValueAsString(messageToAdd+object);
		else if(object != null && messageToAdd == null)
			jsonString = jsonMapped.writeValueAsString(object);
		else if(object == null && messageToAdd != null)
			jsonString = jsonMapped.writeValueAsString(messageToAdd);
		else
			jsonString = jsonMapped.writeValueAsString("EMPTY_RESPONSE_FROM_SERVER");
		LOGGER.debug(jsonString);
		return jsonString;
	}
}
