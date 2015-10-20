package service.rest;

import java.util.List;

import domain.Track;

public interface ITrackResource {
	public List<Track> getAllTracks();
	
	public List<Track> getTracksByTitle(String title);
	
	public List<Track> getTracksByPlaylistId(int playlistId);
	
	public List<Track> getTracksNotInPlaylist(int playlistId);
	
	public List<Track> getTracksNotInPlaylistByTitle(String title, int playlistId);
}
