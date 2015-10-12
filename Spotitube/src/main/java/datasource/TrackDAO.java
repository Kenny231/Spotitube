package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.*;

public class TrackDAO {
	private static final Logger LOGGER = Logger.getLogger(TrackDAO.class.getName());
	// Dao's.
	private SongDAO songDAO = new SongDAO();
	private VideoDAO videoDAO = new VideoDAO();
	private AvailabilityDAO availabilityDAO = new AvailabilityDAO();
	// Hashmap met alle tracks.
	private Map<Integer, Track> trackList = new HashMap<Integer, Track>();
	
	/* 
	 * Alle tracks ophalen en in een list stoppen.
	 * Op deze manier hoeven we niet steeds een query
	 * uit te voeren om tracks op te halen.
	 */
	public TrackDAO()
	{
		load();
	}
	private void load()
	{
		songDAO.load(trackList);
		videoDAO.load(trackList);
	}	
	
	/*
	 * Methode om alle tracks te vinden bij een bepaalde playlist id.
	 */
    public List<Track> findTracksByPlaylistId(int playlistId)
    {
		List<Track> trackList = new ArrayList<Track>();
		List<Integer> trackIds = availabilityDAO.getTrackIdsByPlaylist(playlistId);
		addTracksFromList(trackList, trackIds);
		return trackList;
    }
    private void addTracksFromList(List<Track> trackList, List<Integer> trackIds)
    {
    	for (int trackId : trackIds)
    	{
    		Track track = this.trackList.get(trackId);
    		if (track != null)
    			trackList.add(track);
    	}
    }
    /*
     * Methode om een track te vinden met een bepaalde titel.
     */
    public List<Track> findTrackByTitle(String title)
    {
    	List<Track> trackList = new ArrayList<Track>();
    	for (Track track : trackList)
    	{
    		if (track.getTitle().toLowerCase() == title.toLowerCase())
    			trackList.add(track);
    	}
    	return trackList;
    }
}
