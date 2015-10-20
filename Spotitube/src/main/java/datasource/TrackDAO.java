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

public class TrackDAO extends Database implements ITrackDAO {
	private static final Logger LOGGER = Logger.getLogger(TrackDAO.class.getName());
	// Statements
	private static final String selectSongStatementString = 
		"SELECT *" + 
		" FROM song_view";	
	private static final String selectVideoStatementString = 
		"SELECT *" + 
		" FROM video_view";
	private static final String selectSongTitleStatementString = 
		"SELECT *" +
		" FROM song_view" +
		" WHERE title = ?";
	private static final String selectVideoTitleStatementString = 
		"SELECT *" +
		" FROM video_view" +
		" WHERE title = ?";	
	private static final String selectSongByPlaylistIdStatementString =
		"SELECT *" +
		" FROM song_view" +
		" where id IN (select track_id from availability where playlist_id = ?);";
	private static final String selectVideoByPlaylistIdStatementString =
		"SELECT *" +
		" FROM video_view" +
		" where id IN (select track_id from availability where playlist_id = ?);";
	private static final String selectSongNotInPlaylistStatementString =
		"SELECT *" +
		" FROM song_view" +
		" where id NOT IN (select track_id from availability where playlist_id = ?);";
	private static final String selectVideoNotInPlaylistStatementString =
		"SELECT *" +
		" FROM video_view" +
		" where id NOT IN (select track_id from availability where playlist_id = ?);";
	/* 
	 * Methode om alle tracks op te halen.
	 */
	public List<Track> findAllTracks()
	{
		List<Track> trackList = new ArrayList<Track>();
		findAllTracks(selectSongStatementString, trackList);
		findAllTracks(selectVideoStatementString, trackList);
		return trackList;
	}
	private List<Track> findAllTracks(String statementString, List<Track> trackList)
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
			return trackList;
		} 
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error communicating with database.", e);
		} 
		finally {
			closeStatement(statement);
			closeDatabase();
		}
		return null;			
	}   
	/*
	 * Methodes om alle tracks te vinden bij een bepaalde playlist id.
	 */
	public List<Track> findTracksByPlaylistId(int playlistId)
	{
		List<Track> trackList = new ArrayList<Track>();
		findTracksByPlaylistId(selectSongByPlaylistIdStatementString, trackList, playlistId);
		findTracksByPlaylistId(selectVideoByPlaylistIdStatementString, trackList, playlistId);
		return trackList;
	}
	private List<Track> findTracksByPlaylistId(String statementString, List<Track> trackList, int playlistId)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = getConnection();
			statement = conn.prepareStatement(statementString);
			statement.setInt(1, playlistId);
			ResultSet rs = statement.executeQuery();
			if (statementString == selectSongByPlaylistIdStatementString)
				addSongsFromDatabase(rs, trackList);
			else if (statementString == selectVideoByPlaylistIdStatementString)
				addVideosFromDatabase(rs, trackList);
			return trackList;
		} 
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error communicating with database.", e);
		} 
		finally {
			closeStatement(statement);
			closeDatabase();
		}
		return null;			
	}
	/*
	 * Methodes om alle tracks te die niet in een bepaalde playlist zitten.
	 */
	public List<Track> findTracksNotInPlaylist(int playlistId)
	{
		List<Track> trackList = new ArrayList<Track>();
		findTracksNotInPlaylist(selectSongNotInPlaylistStatementString, trackList, playlistId);
		findTracksNotInPlaylist(selectVideoNotInPlaylistStatementString, trackList, playlistId);
		return trackList;
	}
	private List<Track> findTracksNotInPlaylist(String statementString, List<Track> trackList, int playlistId)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = getConnection();
			statement = conn.prepareStatement(statementString);
			statement.setInt(1, playlistId);
			ResultSet rs = statement.executeQuery();
			if (statementString == selectSongNotInPlaylistStatementString)
				addSongsFromDatabase(rs, trackList);
			else if (statementString == selectVideoNotInPlaylistStatementString)
				addVideosFromDatabase(rs, trackList);
			return trackList;
		} 
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error communicating with database.", e);
		} 
		finally {
			closeStatement(statement);
			closeDatabase();
		}
		return null;			
	}	
	/*
	 * Methode om een track te vinden met een bepaalde titel.
	 */
	public List<Track> findTracksByTitle(String title)
	{
		List<Track> trackList = new ArrayList<Track>();
		findTracksByTitle(selectSongTitleStatementString, trackList, title);
		findTracksByTitle(selectVideoTitleStatementString, trackList, title);
		return trackList;
	}
	private List<Track> findTracksByTitle(String statementString, List<Track> trackList, String title)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = getConnection();
			statement = conn.prepareStatement(statementString);
			statement.setString(1, title);
			ResultSet rs = statement.executeQuery();
			if (statementString == selectSongTitleStatementString)
				addSongsFromDatabase(rs, trackList);
			else if (statementString == selectVideoTitleStatementString)
				addVideosFromDatabase(rs, trackList);
			return trackList;
		} 
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error communicating with database.", e);
		} 
		finally {
			closeStatement(statement);
			closeDatabase();
		}
		return null;			
	} 	
	/*
	 * Methode om alle resultaten in een list te stoppen.
	 */
	private void addSongsFromDatabase(ResultSet rs, List<Track> trackList) throws SQLException {
		while (rs.next())
			trackList.add(new Song(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
	}
	private void addVideosFromDatabase(ResultSet rs, List<Track> trackList) throws SQLException {
		while (rs.next())
			trackList.add(new Video(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
	}		
}
