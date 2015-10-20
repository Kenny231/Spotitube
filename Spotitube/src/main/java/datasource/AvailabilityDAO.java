package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Availability;

public class AvailabilityDAO extends Database implements IAvailabilityDAO {
	private static final Logger LOGGER = Logger.getLogger(TrackDAO.class.getName());
	// Statements	
	private static final String insertStatementString =
		"INSERT INTO availability VALUES(?, ?, ?)";
	private static final String deleteStatementString = 
		"DELETE FROM availability" + 
		" WHERE playlist_id=? AND track_id=?";	
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
