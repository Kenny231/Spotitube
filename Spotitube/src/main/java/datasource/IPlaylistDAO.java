package datasource;

import java.util.List;

import domain.Playlist;

public interface IPlaylistDAO {
	public List<Playlist> findAllPlaylists();
	
	public List<Playlist> findPlaylistByOwner(String name);
	
	public void insert(String owner, String name);
	
	public void update(int id, String owner, String name);
	
	public void delete(int id);
}
