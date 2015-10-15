package service.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import datasource.ITrackDAO;
import domain.Track;

@Path("/track")
public class TrackResource {
	@Inject
	ITrackDAO trackDAO;
	
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
