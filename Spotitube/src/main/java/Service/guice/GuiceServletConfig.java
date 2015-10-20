package service.guice;

import javax.servlet.annotation.WebListener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/*
 * Wordt aangeroepen op het moment dat de web applicatie
 * is 'ge-deployed' en dus voor de requests.
 */
@WebListener
public class GuiceServletConfig extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new SpotitubeModule());
	}
}