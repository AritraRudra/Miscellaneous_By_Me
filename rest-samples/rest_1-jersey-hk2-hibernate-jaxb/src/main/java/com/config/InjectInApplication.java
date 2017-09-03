package com.config;

import java.util.HashSet;
import java.util.Set;

import com.resources.rest.StudentResource;
import com.resources.rest.StudentsResource;

// https://stackoverflow.com/a/9606414/1679643

public class InjectInApplication extends javax.ws.rs.core.Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public InjectInApplication() {
		// no instance is created, just class is listed
		classes.add(StudentResource.class);
		classes.add(StudentsResource.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
