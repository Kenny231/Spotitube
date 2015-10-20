package service.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import datasource.ITrackDAO;
import domain.Track;

@Path("/track")
public class TrackResource implements ITrackResource{
	ITrackDAO trackDAO;
	
	@Inject
	public TrackResource(ITrackDAO trackDAO)
	{
		this.trackDAO = trackDAO;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getAllTracks()
	{
		return trackDAO.findAllTracks();
	}
	
	@GET
	@Path("/title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getTracksByTitle(@PathParam("title") final String title) {
		return trackDAO.findTracksByTitle(title);
	}

	@GET
	@Path("/in/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getTracksByPlaylistId(@PathParam("id") final int id) {
		return trackDAO.findTracksByPlaylistId(id);
	}
	
	@GET
	@Path("/notIn/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getTracksNotInPlaylist(@PathParam("id") final int id) {
		return trackDAO.findTracksNotInPlaylist(id);
	}
	
	@GET
	@Path("notInTitle/{title}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getTracksNotInPlaylistByTitle(@PathParam("title") final String title, @PathParam("id") final int id)
	{
		List<Track> tracksByTitle = getTracksByTitle(title);
		List<Track> tracksNotInPlaylist = getTracksNotInPlaylist(id);
		List<Track> trackList = new ArrayList<Track>();
		for (Track titleTrack : tracksByTitle)
		{
			for (Track playlistTrack : tracksNotInPlaylist)
			{
				if (titleTrack.getId() == playlistTrack.getId())
					trackList.add(titleTrack);
			}
		}
		return trackList;
	}
}
