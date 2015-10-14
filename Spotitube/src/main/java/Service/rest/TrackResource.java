package service.rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import datasource.PlaylistDAO;
import datasource.TrackDAO;
import domain.Playlist;
import domain.Track;

public class TrackResource {
	TrackDAO trackDAO = new TrackDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getAllTracks()
	{
		return trackDAO.findAllTracks();
	}
	
	@GET
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getTrackByTitle(@PathParam("title") final String title) {
		return trackDAO.findTrackByTitle(title);
	}
}
