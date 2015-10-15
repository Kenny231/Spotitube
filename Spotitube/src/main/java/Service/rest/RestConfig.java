package service.rest;

import guice.SpotitubeModule;

import javax.ws.rs.ApplicationPath;
import javax.inject.Inject;

import com.google.inject.Guice;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

@ApplicationPath("/spotitube")
public class RestConfig extends ResourceConfig {
	public static final String JSON_SERIALIZER = "jersey.config.server.provider.packages";
	public static final String JACKSON_JSON_SERIALIZER = "com.fasterxml.jackson.jaxrs.json;service";
	
	@Inject
	public RestConfig(ServiceLocator serviceLocator) {
		packages(true, "service");
		property(JSON_SERIALIZER, JACKSON_JSON_SERIALIZER);
		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
		GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
		guiceBridge.bridgeGuiceInjector(Guice.createInjector(new SpotitubeModule()));		
	}
}
