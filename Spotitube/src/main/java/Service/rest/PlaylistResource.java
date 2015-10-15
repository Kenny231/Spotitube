package service.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import datasource.IPlaylistDAO;
import domain.Playlist;

@Path("/playlist")
public class PlaylistResource implements IPlaylistResource {
	
	private IPlaylistDAO playlistDAO;
	
	@Inject	
	public PlaylistResource(IPlaylistDAO playlistDAO)
	{
		this.playlistDAO = playlistDAO;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Playlist> getAllPlaylists()
	{
		return playlistDAO.findAllPlaylists();
	}
	
	@GET
	@Path("/owner/{owner}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Playlist> getPlaylistByOwner(@PathParam("owner") final String owner) {
		return playlistDAO.findPlaylistByOwner(owner);
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Playlist getPlaylistById(@PathParam("owner") final String id)
	{
		return playlistDAO.findPlaylistById(Integer.parseInt(id));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPlaylist(Playlist playlist)
	{
		playlistDAO.insert(playlist.getOwner(), playlist.getName());
	}
	
	@DELETE
	@Path("/{id}")
	public void deletePlaylist(@PathParam("id") final int id)
	{
		playlistDAO.delete(id);
	}
}
