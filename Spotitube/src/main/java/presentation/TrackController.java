package presentation;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Track;
import service.rest.ITrackResource;

@Singleton
public class TrackController extends AbstractInjectableServlet {
	/*
	 * Field injection, omdat het injecteren van de constructor
	 * niet mogelijk is. 
	 */
	@Inject
	private ITrackResource resource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String title = request.getParameter("title");
		
		List<Track> tracks = null;
		if (title != null && title != "")
			tracks = resource.getTracksByTitle(title);
		else
			tracks = resource.getAllTracks();
					
		request.setAttribute("tracks", tracks);
		request.getRequestDispatcher("viewTracks.jsp").forward(request, response);		
	}
}
