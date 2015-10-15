package presentation;

import guice.SpotitubeModule;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Guice;

import domain.Playlist;
import service.rest.IPlaylistResource;
import service.rest.PlaylistResource;

@Singleton
@WebServlet(urlPatterns = "/viewPlaylists")
public class PlaylistView extends AbstractInjectableServlet {
	/*
	 * Field injection, omdat het injecteren van de constructor
	 * niet mogelijk is. 
	 */
	@Inject
	private IPlaylistResource resource;
			
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String owner = request.getParameter("name");
		List<Playlist> playlists = resource.getPlaylistByOwner(owner); 
		request.setAttribute("all", playlists);
		request.getRequestDispatcher("viewPlaylists.jsp").forward(request, response);
	}
}
