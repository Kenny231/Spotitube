package service.rest;

import java.util.List;

import domain.Playlist;

public interface IPlaylistResource {
	public List<Playlist> getAllPlaylists();
	
	public List<Playlist> getPlaylistByOwner(String owner);
	
	public void addPlaylist(Playlist playlist);
	
	public void deletePlaylist(int id);
}
