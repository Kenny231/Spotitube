package presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.rest.IAvailabilityResource;
import service.rest.IPlaylistResource;
import service.rest.ITrackResource;
import domain.Playlist;

@Singleton
public class PlaylistTracksController extends AbstractInjectableServlet {
	private static final int METHOD_GET = 1;
	private static final int METHOD_POST = 2;
	/*
	 * Field injection, omdat het injecteren van de constructor
	 * niet mogelijk is. 
	 */
	@Inject
	private IPlaylistResource playlistResource;
	
	@Inject
	private IAvailabilityResource availabilityResource;
	
	@Inject 
	private ITrackResource trackResource;
	/*
	 * Methode om zowel de post als de get requests af te handelen.
	 */	
	private void handleRequest(HttpServletRequest request, HttpServletResponse response, int method)  throws ServletException, IOException
	{
		int playlistId = Integer.parseInt(request.getParameter("playlistId"));
		// In het geval van een post, dan ook een track verwijderen.
		if (method == METHOD_POST)
		{
			// Allebei aanroepen, een wordt maar uitgevoerd i.v.m.
			// post variabele.
			deleteTrack(request, playlistId);	
			changePlaylistName(request, playlistId);
		}
		showTracks(request, playlistId);	
		
		request.setAttribute("playlistId", playlistId);	
		request.getRequestDispatcher("viewPlaylistTracks.jsp").forward(request, response);
	}
	/*
	 * Methode die de gewenste tracks in een lijst stop.
	 */
	private void showTracks(HttpServletRequest request, int playlistId)
	{
		Playlist playlist = playlistResource.getPlaylistById(playlistId);
		if (playlist != null)
			request.setAttribute("tracksByPlaylist", trackResource.getTracksByPlaylistId(playlistId));
		else
			request.setAttribute("tracksByPlaylist", new ArrayList<Playlist>());	
	}
	/*
	 * Methode die de naam van een playlist aanpast.
	 */
	private void changePlaylistName(HttpServletRequest request, int playlistId)
	{
		String name = request.getParameter("playlistName");
		
		if (name != null && name != "")
			playlistResource.changePlaylistName(playlistId, name);
	}
	/*
	 * Methode die een track uit een playlist verwijderd.
	 */
	private void deleteTrack(HttpServletRequest request, int playlistId) {
		String trackId = request.getParameter("trackId");
		
		if (trackId != null && trackId != "")
			availabilityResource.deleteTrackFromPlaylist(playlistId, Integer.parseInt(trackId));
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request, response, METHOD_GET);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request, response, METHOD_POST);
	}	
}
