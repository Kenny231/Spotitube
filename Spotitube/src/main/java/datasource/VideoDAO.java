package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
			" FROM video_view S";
	
	// package protected.
	protected VideoDAO() { }
	/*
	 * SELECT
	 */
	
	/*
	 * Method that'll execute the query and returns the list.
	 */	
	public void load(Map<Integer, Track> trackList)
	{
    	PreparedStatement statement = null;
    	try
    	{
    		Connection conn = getConnection();
    		statement = conn.prepareStatement(selectStatementString);
    		ResultSet rs = statement.executeQuery();
    		addVideosFromDatabase(rs, trackList);
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
	private void addVideosFromDatabase(ResultSet rs, Map<Integer, Track> trackList) throws SQLException {
		while (rs.next())
			trackList.put(rs.getInt(1), new Video(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
	}    
}
