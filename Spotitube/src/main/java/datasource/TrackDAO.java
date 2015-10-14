package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.*;

public class TrackDAO extends Database {
	private static final Logger LOGGER = Logger.getLogger(TrackDAO.class.getName());
	// Statements
	private static final String selectSongStatementString = 
		"SELECT *" + 
		" FROM song_view S";	
	private static final String selectVideoStatementString = 
		"SELECT *" + 
		" FROM video_view S";	
	// Dao's.
	private AvailabilityDAO availabilityDAO = new AvailabilityDAO();
	// Hashmap met alle tracks.
	private List<Track> trackList = new ArrayList<Track>();
	
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
		load(trackList, selectSongStatementString);
		load(trackList, selectVideoStatementString);
	}	
	private void load(List<Track> trackList, String statementString)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = getConnection();
			statement = conn.prepareStatement(statementString);
			ResultSet rs = statement.executeQuery();
			if (statementString == selectSongStatementString)
				addSongsFromDatabase(rs, trackList);
			else if (statementString == selectVideoStatementString)
				addVideosFromDatabase(rs, trackList);
				
		} 
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error communicating with database.", e);
		} 
		finally {
			closeStatement(statement);
			closeDatabase();
		}			
	}
	private void addSongsFromDatabase(ResultSet rs, List<Track> trackList) throws SQLException {
		while (rs.next())
			trackList.add(new Song(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
	}
	private void addVideosFromDatabase(ResultSet rs, List<Track> trackList) throws SQLException {
		while (rs.next())
			trackList.add(new Video(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
	}	   
	/*
	 * Methode om alle tracks op te halen.
	 */
	public List<Track> findAllTracks()
	{
		return trackList;
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
			for (Track track : this.trackList)
			{
				if (track.getId() == trackId)
				{
					trackList.add(track);
					break;
				}
			}
		}
	}
	/*
	 * Methode om een track te vinden met een bepaalde titel.
	 */
	public List<Track> findTrackByTitle(String title)
	{
		List<Track> trackList = new ArrayList<Track>();
		for (Track track : this.trackList)
		{
			if (track.getTitle().toLowerCase() == title.toLowerCase())
				trackList.add(track);
		}
		return trackList;
	}
}
