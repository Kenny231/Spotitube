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

public class TrackDAO extends Database{
	private static Logger logger = Logger.getLogger(TrackDAO.class.getName());
	// Dao's.
	private SongDAO songDAO = new SongDAO();
	private VideoDAO videoDAO = new VideoDAO();
	// Track table.
	private static final String updateStatementString = 
		"UPDATE Track " + 
		" SET performer=?, title=?, url=?, duration=?" +
		" WHERE id = ?";
	private static final String deleteStatementString = 
		"DELETE FROM Track" + 
		" WHERE id = ?";	
	// Availability table
	private static final String selectTrackStatementString = 
		"SELECT playlist_id, track_id, available" +
		" FROM Availability" +
		" WHERE playlist_id = ?";
	/*
	 * SELECT
	 */
	
	/*
	 * Method to find all the tracks (songs and videos)
	 * for a specific playlist.
	 */
    public List<Track> findTracksByPlaylistId(long playlistId)
    {
			List<Track> track_list = new ArrayList<Track>();
	    	songDAO.findTracksByPlaylistId(playlistId, track_list);
	    	videoDAO.findTracksByPlaylistId(playlistId, track_list);
			return track_list;
    }
    
    /*
     * INSERT
     */
    public void insert(String performer, String title, String url, int duration, String album)
    {
    	songDAO.insert(performer, title, url, duration, album);
    }
    public void insert(String performer, String title, String url, int duration, int playcount, String publicationDate, String description)
    {
    	videoDAO.insert(performer, title, url, duration, playcount, publicationDate, description);
    }
    /*
     * UPDATE
     */
    private void update(long id, String performer, String title, String url, int duration)
    {
    	PreparedStatement statement = null;
    	try
    	{
			Connection conn = getConnection();
			statement = conn.prepareStatement(updateStatementString);
			statement.setString(1, performer);
			statement.setString(2, title);
			statement.setString(3, url);
			statement.setInt(4, duration);
			statement.setLong(5, id);
			statement.execute(); 
    	}
    	catch(SQLException e) {
    		logger.log(Level.SEVERE, "Error communicating with database.", e);
    	}
    	finally {
    		closeStatement(statement);
    		closeDatabase();
    	}    	
    }
    public void update(long id, String performer, String title, String url, int duration, String album)
    {
    	update(id, performer, title, url, duration);
    	songDAO.update(id, album);
    }
    public void update(long id, String performer, String title, String url, int duration, int playcount, String publicationDate, String description)
    {
    	update(id, performer, title, url, duration);
    	videoDAO.update(id, playcount, publicationDate, description);    		
    }
    /*
     * DELETE
     */
    public void delete(long id)
    {
        PreparedStatement statement = null;
    	try
    	{
    		Connection conn = getConnection();
    		statement = conn.prepareStatement(deleteStatementString);
    		statement.setLong(1, id);
    		statement.execute();
    	}
    	catch (SQLException e) {
    		logger.log(Level.SEVERE, "Error communicating with database.", e);
    	}
    	finally {
    		closeStatement(statement);
    		closeDatabase();
    	}
	}
}
