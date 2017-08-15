package com.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import com.model.Address;
import com.model.Student;

public class Demo_1 {
	private static final String OUTPUT_XML = "student-jaxb.xml";

	public static void main(String a[]) throws JAXBException, FileNotFoundException {

		Address address = new Address("Street", "City", "Country", 96L);
		Student student = new Student(9, new Date(), "LastName", address);

		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(Student.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		marshaller.marshal(student, System.out);

		// Write to File
		marshaller.marshal(student, new File(OUTPUT_XML));

		// get variables from our xml file, created before
		System.out.println();
		System.out.println("Output from our XML File: ");
		Unmarshaller unMarshaller = context.createUnmarshaller();
		Student readFromXML = (Student) unMarshaller.unmarshal(new FileReader(OUTPUT_XML));
		System.out.println(readFromXML.toString());
		
		try {
			generateSchema();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void sampleData(){
		Address address = new Address("Street", "City", "Country", 96L);
		Student student = new Student(9, new Date(), "LastName", address);
	}
	
	// https://stackoverflow.com/a/7214065/1679643
	private static void generateSchema() throws JAXBException, IOException{
		JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
		// Annonymous class inline => https://stackoverflow.com/questions/5848510/how-can-an-anonymous-class-use-extends-or-implements
		SchemaOutputResolver sor = new SchemaOutputResolver() {
			@Override
			public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
		        File file = new File(suggestedFileName);
		        StreamResult result = new StreamResult(file);
		        result.setSystemId(file.toURI().toURL().toString());
		        return result;
		    }
		};
		jaxbContext.generateSchema(sor);
		sor.createOutput("namespace-student", "XSD_"+OUTPUT_XML);
	}
}
