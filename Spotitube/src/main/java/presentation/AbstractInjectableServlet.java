package presentation;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
 
import com.google.inject.Injector;

/*
 * Superklasse, die het injecteren van service classes in servlets
 * mogelijk maakt. In principe injecteren we de classes hier handmatig
 * omdat het anders niet mogelijk is.
 */
public abstract class AbstractInjectableServlet extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(AbstractInjectableServlet.class.getName());
			
	@Override
	public void init(ServletConfig config) throws ServletException {
		try 
		{
			ServletContext context = config.getServletContext();
			Injector injector = (Injector) context.getAttribute(Injector.class.getName());
			// Variabelen injecteren.
			injector.injectMembers(this);
		}
		catch (NullPointerException e)
		{
			LOGGER.log(Level.SEVERE, "Guice injector niet gevonden.", e);
		}
	}
}