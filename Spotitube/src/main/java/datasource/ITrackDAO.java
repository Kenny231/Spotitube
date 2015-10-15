package datasource;

import java.util.List;

import domain.Track;

public interface ITrackDAO {
	public List<Track> findAllTracks();
	
	public List<Track> findTracksByPlaylistId(int playlistId);
	
	public List<Track> findTrackByTitle(String title);
}
