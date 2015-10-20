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
	public Playlist getPlaylistById(@PathParam("id") final int id)
	{
		return playlistDAO.findPlaylistById(id);
	}
	
	public void addPlaylist(String owner, String name)
	{
		addPlaylist(new Playlist(0, owner, name));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPlaylist(Playlist playlist)
	{
		playlistDAO.insert(playlist.getOwner(), playlist.getName());
	}
	
	@PUT
	@Path("/name/{id}/{name}")
	public void changePlaylistName(@PathParam("id") final int id, @PathParam("name") final String name)
	{
		playlistDAO.update(id, name);
	}
	
	@DELETE
	@Path("/list/{id}")
	public void deletePlaylist(@PathParam("id") final int id)
	{
		playlistDAO.delete(id);
	}
}
