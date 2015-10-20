package service.guice;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.google.inject.servlet.GuiceFilter;

/*
 * @WebFilter zorgt er voor dat all 'requests' door
 * een filter gehaald worden.
 */
@WebFilter("/*")
public class GuiceWebFilter extends GuiceFilter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		super.doFilter(servletRequest, servletResponse, filterChain);
	}
}
