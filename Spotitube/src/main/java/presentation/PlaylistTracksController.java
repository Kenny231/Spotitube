package presentation;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.rest.IPlaylistResource;
import domain.Playlist;

@Singleton
@WebServlet(urlPatterns = "/viewPlaylistTracks")
public class PlaylistTracksController extends AbstractInjectableServlet {
	/*
	 * Field injection, omdat het injecteren van de constructor
	 * niet mogelijk is. 
	 */
	@Inject
	private IPlaylistResource resource;
			
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String id = request.getParameter("id");
		Playlist playlist = resource.getPlaylistById(Integer.parseInt(id));
		if (playlist != null)
			request.setAttribute("tracksByPlaylist", playlist.getTrackList());
		else
			request.setAttribute("tracksByPlaylist", null);
		request.getRequestDispatcher("viewPlaylistTracks.jsp").forward(request, response);
	}
}
