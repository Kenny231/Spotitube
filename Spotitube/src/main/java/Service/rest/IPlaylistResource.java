package service.rest;

import java.util.List;

import domain.Playlist;

public interface IPlaylistResource {
	public List<Playlist> getAllPlaylists();
	
	public List<Playlist> getPlaylistByOwner(String owner);
	
	public Playlist getPlaylistById(int id);
	
	public void addPlaylist(Playlist playlist);
	
	public void addPlaylist(String owner, String name);
	
	public void deletePlaylist(int id);
	
	public void changePlaylistName(int id, String name);
}
