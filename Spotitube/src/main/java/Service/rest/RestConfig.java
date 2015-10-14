package service.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/spotitube")
public class RestConfig extends ResourceConfig {
	public static final String JSON_SERIALIZER = "jersey.config.server.provider.packages";
	public static final String JACKSON_JSON_SERIALIZER = "com.fasterxml.jackson.jaxrs.json;service";
	
	public RestConfig() {
		packages(true, "service");
		property(JSON_SERIALIZER, JACKSON_JSON_SERIALIZER);		 
	}
}
