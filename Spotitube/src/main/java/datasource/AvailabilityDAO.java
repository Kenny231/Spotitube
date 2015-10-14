package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AvailabilityDAO extends Database {
	private static final Logger LOGGER = Logger.getLogger(TrackDAO.class.getName());
		
	private static final String selectStatementString = 
		"SELECT track_id" +
		" FROM availability" +
		" WHERE playlist_id = ?";
	private static final String insertStatementString =
		"INSERT INTO availability VALUES(?, ?, ?)";
	private static final String deleteStatementString = 
		"DELETE FROM availability" + 
		" WHERE playlist_id=? AND track_id=?";
	
	// package protected.
	protected AvailabilityDAO() { }
	
	/*
	 * Methode om alle track_ids op te halen die bij één
	 * bepaalde track horen.
	 */
	public List<Integer> getTrackIdsByPlaylist(int playlistId)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = getConnection();
			statement = conn.prepareStatement(selectStatementString);
			statement.setInt(1, playlistId);
			ResultSet rs = statement.executeQuery();
			
			List<Integer> list = new ArrayList<Integer>();
			while (rs.next())
				list.add(rs.getInt(1));
			return list;
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
	 * INSERT
	 */	
	public void insert(int playlistId, int trackId, int availability)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = getConnection();
			statement = conn.prepareStatement(insertStatementString);
			statement.setInt(1, playlistId);
			statement.setInt(2, trackId);
			statement.setInt(3, availability);
			statement.execute();
		} 
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error communicating with database.", e);
		} 
		finally {
			closeStatement(statement);
			closeDatabase();
		}
	}
	/*
	 * DELETE
	 */
	public void delete(int playlistId, int trackId)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = getConnection();
			statement = conn.prepareStatement(deleteStatementString);
			statement.setInt(1, playlistId);
			statement.setInt(2, trackId);
			statement.execute();
		}
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error communicating with database.", e);
		}
		finally {
			closeStatement(statement);
			closeDatabase();
		}
	}	
}
