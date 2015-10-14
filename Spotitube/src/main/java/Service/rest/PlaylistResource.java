package service.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import datasource.Database;
import datasource.PlaylistDAO;
import domain.Playlist;

@Path("/playlist")
public class PlaylistResource {
	
	PlaylistDAO playlistDAO = new PlaylistDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Playlist> getAllPlaylists()
	{
		return ((PlaylistDAO) playlistDAO).findAllPlaylists();
	}
	
	@GET
    @Path("/{owner}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Playlist> getPlaylistByOwner(@PathParam("owner") final String owner) {
		return ((PlaylistDAO) playlistDAO).findPlaylistByOwner(owner);
    }	
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPlaylist(Playlist playlist)
    {
    	((PlaylistDAO) playlistDAO).insert(playlist.getOwner(), playlist.getName());
    }
    
    @DELETE
    @Path("/{id}")
    public void deletePlaylist(@PathParam("id") final int id)
    {
        ((PlaylistDAO) playlistDAO).delete(id);
    }
}
