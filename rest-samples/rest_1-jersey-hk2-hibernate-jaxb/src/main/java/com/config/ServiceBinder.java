package com.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScope;

import com.resources.rest.StudentResource;
import com.services.StudentService;
import com.services.persistence.StudentPersistenceService;

// https://stackoverflow.com/questions/16216759/dependency-injection-with-jersey-2-0
public class ServiceBinder extends AbstractBinder {
    @Override
    protected void configure() {
    	bind(StudentPersistenceService.class).to(StudentPersistenceService.class);
        bind(StudentService.class).to(StudentService.class);
    }
}