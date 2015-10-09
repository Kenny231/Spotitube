package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Song;
import domain.Track;
import domain.Video;

public class VideoDAO extends Database{
	private static Logger logger = Logger.getLogger(VideoDAO.class.getName());
	// Videos table
	private static final String selectStatementString = 
			"SELECT *" + 
			" FROM video_view S" +
			" JOIN Availability A ON A.track_id = S.id" +
			" WHERE A.playlist_id = ?";
	private static final String insertStatementString = 
		"CALL sp_insert_video(?, ?, ?, ?, ?, ?, ?)";
	private static final String updateStatementString = 
		"UPDATE Videos" +
		" SET playcount=?, publication_date=?, description=?" + 
		" WHERE id = ?";	
	/*
	 * SELECT
	 */
	
	/*
	 * Method that'll execute the query and returns the list.
	 */	
	public void findTracksByPlaylistId(Long playlistId, List<Track> track_list)
	{
    	PreparedStatement statement = null;
    	try
    	{
    		Connection conn = getConnection();
    		statement = conn.prepareStatement(selectStatementString);
    		statement.setLong(1, playlistId);
    		ResultSet rs = statement.executeQuery();
    		addVideosFromDatabase(rs, track_list);
    	} 
    	catch (SQLException e) {
    		logger.log(Level.SEVERE, "Error communicating with database.", e);
    	} 
    	finally {
    		closeStatement(statement);
    		closeDatabase();
    	}		
	}
	/*
	 * Methods that adds the tracks from the resultset to the list.
	 */	
	private void addVideosFromDatabase(ResultSet rs, List<Track> track_list) throws SQLException {
		while (rs.next())
			track_list.add(new Video(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
	}
	
    /*
     * INSERT
     */
    public void insert(String performer, String title, String url, int duration, int playcount, String publicationDate, String description)
    {
    	PreparedStatement statement = null;
    	try
    	{
    		Connection conn = getConnection();
    		statement = conn.prepareStatement(insertStatementString);
    		statement.setString(1, performer);
    		statement.setString(2, title);
    		statement.setString(3, url);
    		statement.setInt(4, duration);
    		statement.setInt(5, playcount);
    		statement.setString(6, publicationDate);
    		statement.setString(7,  description);
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
    /*
     * UPDATE
     */
    public void update(long id, int playcount, String publicationDate, String description)
    {
    	PreparedStatement statement = null;
    	try
    	{
    		Connection conn = getConnection();
    		statement = conn.prepareStatement(updateStatementString);
    		statement.setInt(1, playcount);
    		statement.setString(2, publicationDate);
    		statement.setString(3, description);
    		statement.setLong(4, id);    		
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
}
