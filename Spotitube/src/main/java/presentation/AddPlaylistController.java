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
public class AddPlaylistController extends AbstractInjectableServlet {
	private final static int METHOD_GET = 1;
	private final static int METHOD_POST = 2;
	/*
	 * Field injection, omdat het injecteren van de constructor
	 * niet mogelijk is. 
	 */
	@Inject
	private IPlaylistResource resource;
	/*
	 * Methode om zowel de post als de get requests af te handelen.
	 */
	private void handleRequest(HttpServletRequest request, HttpServletResponse response, int method) throws ServletException, IOException
	{
		String owner = request.getParameter("owner");
		if (owner != null && owner != "")
		{
			request.setAttribute("owner", owner);
			if (method == METHOD_POST)
				addPlaylist(request, owner);

			request.getRequestDispatcher("addPlaylistView.jsp").forward(request, response);
		}
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
	/*
	 * Methode om een playlist aan te maken.
	 */
	private void addPlaylist(HttpServletRequest request, String owner)
	{
		String name = request.getParameter("name");
		if (name != null && name != "")
			resource.addPlaylist(owner, name);
	}
}