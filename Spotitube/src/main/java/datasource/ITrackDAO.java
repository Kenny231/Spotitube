package datasource;

import java.util.List;

import domain.Track;

public interface ITrackDAO {
	public List<Track> findAllTracks();
	
	public List<Track> findTracksByPlaylistId(int playlistId);
	
	public List<Track> findTracksByTitle(String title);
	
	public List<Track> findTracksNotInPlaylist(int playlistId);
}
