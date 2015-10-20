package service.rest;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Path;

import datasource.IAvailabilityDAO;

@Path("/availability")
public class AvailabilityResource implements IAvailabilityResource {
	private IAvailabilityDAO availabilityDAO;
	
	@Inject	
	public AvailabilityResource(IAvailabilityDAO availabilityDAO)
	{
		this.availabilityDAO = availabilityDAO;
	}
	
	/*
	 * Om een of andere reden mag er hier geen gebruik gemaakt worden van
	 * javarx.
	 */
	//@DELETE
	//@Path("/deltrack/{playlistId}/{trackId}")
	public void deleteTrackFromPlaylist(@PathParam("playlistId") final int playlistId, @PathParam("trackId") final int trackId)
	{
		availabilityDAO.delete(playlistId, trackId);
	}
	
	//@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	public void addTrackToPlaylist(@PathParam("playlistId") final int playlistId, @PathParam("trackId") final int trackId)
	{
		availabilityDAO.insert(playlistId, trackId, 0);
	}			
}
