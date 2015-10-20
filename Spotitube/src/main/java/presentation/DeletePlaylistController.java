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


@Singleton
public class DeletePlaylistController extends AbstractInjectableServlet {
	/*
	 * Field injection, omdat het injecteren van de constructor
	 * niet mogelijk is. 
	 */
	@Inject
	private IPlaylistResource resource;
	/*
	 * Methode die een track uit een playlist verwijderd.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String playlistId = request.getParameter("playlistId");
		if (playlistId != null && playlistId != "")
			resource.deletePlaylist(Integer.parseInt(playlistId));
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}