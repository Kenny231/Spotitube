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
import service.rest.IAvailabilityResource;
import service.rest.IPlaylistResource;
import service.rest.ITrackResource;

@Singleton
public class AddTrackController extends AbstractInjectableServlet {
	private static final int METHOD_GET = 1;
	private static final int METHOD_POST = 2;	
	/*
	 * Field injection, omdat het injecteren van de constructor
	 * niet mogelijk is. 
	 */
	@Inject
	private ITrackResource trackResource;
	
	@Inject
	private IPlaylistResource playlistResource;
	
	@Inject
	private IAvailabilityResource availabilityResource;
	/*
	 * Methode om zowel de post als de get requests af te handelen.
	 */	
	private void handleRequest(HttpServletRequest request, HttpServletResponse response, int method) throws ServletException, IOException
	{
		String id = request.getParameter("playlistId");
		if (id != null && id != "")
		{
			int playlistId = Integer.parseInt(id);
			if (method == METHOD_POST)
			{
				addTrack(request, playlistId);
			}
			showTracks(request, playlistId);
			
			request.setAttribute("playlistId", playlistId);
			request.getRequestDispatcher("addTracksView.jsp").forward(request, response);				
		}
	}
	/*
	 * Methode om een track aan een playlist toe te voegen.	
	 */
	private void addTrack(HttpServletRequest request, int playlistId) {
		String trackId = request.getParameter("trackId");
		
		if (trackId != null && trackId != "")
			availabilityResource.addTrackToPlaylist(playlistId, Integer.parseInt(trackId));
	}
	/*
	 * Methode die alle gewenste tracks in een lijst stopt en mee geeft.
	 */
	private void showTracks(HttpServletRequest request, int playlistId) {
		String title = request.getParameter("title");
		
		List<Track> tracks = null;
		if (title != null && title != "")
			tracks = trackResource.getTracksNotInPlaylistByTitle(title, playlistId);
		else
			tracks = trackResource.getTracksNotInPlaylist(playlistId);
		
		request.setAttribute("tracks", tracks);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request, response, METHOD_GET);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request, response, METHOD_POST);
	}
}
