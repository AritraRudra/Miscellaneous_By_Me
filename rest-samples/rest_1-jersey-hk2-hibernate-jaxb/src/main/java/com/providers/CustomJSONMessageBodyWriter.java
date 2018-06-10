package com.providers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

// https://www.javacodegeeks.com/2014/03/creating-a-simple-jax-rs-messagebodywriter.html
// https://stackoverflow.com/a/24168984/1679643
public class CustomJSONMessageBodyWriter implements MessageBodyWriter {
	@Override
	public boolean isWriteable(Class aClass, Type type, Annotation[] annotations, MediaType mediaType) {
		return true; // usually check for isAnnotationPresent
	}

	@Deprecated
	@Override
	public long getSize(Object o, Class aClass, Type type, Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(Object o, Class aClass, Type type, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap multivaluedMap, OutputStream stream) throws IOException, WebApplicationException {
		// write in required format  
		//stream.write(marshall(o));
	}
}