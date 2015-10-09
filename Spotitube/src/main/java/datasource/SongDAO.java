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

public class SongDAO extends Database{
	private static Logger logger = Logger.getLogger(SongDAO.class.getName());
	// Songs table.
	private static final String selectStatementString = 
		"SELECT *" + 
		" FROM song_view S" +
		" JOIN Availability A ON A.track_id = S.id" +
		" WHERE A.playlist_id = ?";
	private static final String insertStatementString = 
		"CALL sp_insert_song(?, ?, ?, ?, ?)";
	private static final String updateStatementString = 
		"UPDATE Songs " + 
		" SET album=?" +
		" WHERE id = ?";
	
	/*
	 * SELECT
	 */
	
	/*
	 * Method that'll execute the query and returns the list.
	 */
	public void findTracksByPlaylistId(long playlistId, List<Track> track_list)
	{
    	PreparedStatement statement = null;
    	try
    	{
    		Connection conn = getConnection();
    		statement = conn.prepareStatement(selectStatementString);
    		statement.setLong(1,playlistId);
    		ResultSet rs = statement.executeQuery();
    		addSongsFromDatabase(rs, track_list);
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
    private void addSongsFromDatabase(ResultSet rs, List<Track> track_list) throws SQLException {
    	while (rs.next())
    		track_list.add(new Song(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
    }	
    
    /*
     * INSERT
     */
    public void insert(String performer, String title, String url, int duration, String album)
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
    		statement.setString(5, album);
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
    public void update(long id, String album)
    {
    	PreparedStatement statement = null;
    	try
    	{
    		Connection conn = getConnection();
    		statement = conn.prepareStatement(updateStatementString);
    		statement.setString(1, album);
    		statement.setLong(2, id);    		
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
