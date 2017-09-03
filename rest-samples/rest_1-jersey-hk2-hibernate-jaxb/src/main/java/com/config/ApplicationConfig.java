package com.config;

//import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

// https://stackoverflow.com/questions/16216759/dependency-injection-with-jersey-2-0
//@ApplicationPath("/rest")
public class ApplicationConfig extends ResourceConfig {
	// https://stackoverflow.com/documentation/jersey/7016/dependency-injection-with-jersey/23632/
	// basic-dependency-injection-using-jerseys-hk2#t=201708061012472240944
	
	// https://stackoverflow.com/documentation/jersey/7012/configuring-jax-rs-in-jersey/12837/
	// java-jersey-configuration#t=20170806102049713882

	// https://stackoverflow.com/a/37884547/1679643
    public ApplicationConfig() {
        //register(ServiceBinder.class);
    	//register(StudentPersistenceService.class);
    	register(new ServiceBinder());
    	packages(true, "com.services");
    }

}
