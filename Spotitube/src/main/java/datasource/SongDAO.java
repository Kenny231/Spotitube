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

public class SongDAO extends Database{
	private static Logger logger = Logger.getLogger(SongDAO.class.getName());
	// Songs table.
	private static final String selectStatementString = 
		"SELECT *" + 
		" FROM song_view S";
	
	// package protected.
	protected SongDAO() { }
	
	public void load(Map<Integer, Track> trackList)
	{
    	PreparedStatement statement = null;
    	try
    	{
    		Connection conn = getConnection();
    		statement = conn.prepareStatement(selectStatementString);
    		ResultSet rs = statement.executeQuery();
    		addSongsFromDatabase(rs, trackList);
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
    private void addSongsFromDatabase(ResultSet rs, Map<Integer, Track> trackList) throws SQLException {
    	while (rs.next())
    		trackList.put(rs.getInt(1), new Song(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
    }
}
