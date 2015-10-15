package datasource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Playlist;

public class PlaylistDAO extends Database implements IPlaylistDAO {
	private final static Logger LOGGER = Logger.getLogger(PlaylistDAO.class.getName());
	
	private AvailabilityDAO availabilityDAO = new AvailabilityDAO();
	private TrackDAO trackDAO = new TrackDAO();
	/*
	 * Op playlist owner zoeken, de selectie van de juiste playlist
	 * (op naam) gebeurd later.
	 */
	private final static String findStatementString =
		"SELECT id, owner, name" +
		" FROM Playlist" +
		" WHERE owner = ?";
	private final static String findAllStatementString = 
		"SELECT id, owner, name" +
		" FROM playlist";
	private final static String insertStatementString = 
		"INSERT INTO Playlist VALUES (0, ?, ?)";
	private final static String deleteStatementString = 
		"DELETE FROM Playlist" + 
		" WHERE id = ?";
	private final static String updateStatementString = 
		"UPDATE Playlist" +
		" SET owner=?, name=?" +
		" WHERE id = ?";
	/*
	 * SELECT
	 */
	public List<Playlist> findAllPlaylists()
	{
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			Connection conn = getConnection();
			statement = conn.prepareStatement(findAllStatementString);
			rs = statement.executeQuery();
			List<Playlist> list = addPlaylistsFromDatabase(rs);
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
	
	public List<Playlist> findPlaylistByOwner(String name)
	{
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			Connection conn = getConnection();
			statement = conn.prepareStatement(findStatementString);
			statement.setString(1, name);
			rs = statement.executeQuery();
			List<Playlist> list = addPlaylistsFromDatabase(rs);
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
	public Playlist findPlaylistById(int id)
	{
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			Connection conn = getConnection();
			statement = conn.prepareStatement("SELECT * FROM Playlist WHERE id=?");
			statement.setInt(1, id);
			rs = statement.executeQuery();
			Playlist playlist = null;
			if (rs.next())
				playlist = new Playlist(rs.getInt(1), rs.getString(2), rs.getString(3), trackDAO.findTracksByPlaylistId(rs.getInt(1)));
			return playlist;
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
	 * Query kan meerdere resultaten teruggeven.
	 */
	private List<Playlist> addPlaylistsFromDatabase(ResultSet rs) throws SQLException {
		List<Playlist> list = new ArrayList<Playlist>();
		while (rs.next())
		{
			list.add(new Playlist(rs.getInt(1), rs.getString(2), rs.getString(3), trackDAO.findTracksByPlaylistId(rs.getInt(1))));
		}
		return list;
	}
	/*
	 * INSERT
	 */
	public void insert(String owner, String name)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = getConnection();
			statement = conn.prepareStatement(insertStatementString);
			statement.setString(1, owner);
			statement.setString(2, name);
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
	 * UPDATE
	 */
	public void update(int id, String owner, String name)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = getConnection();
			statement = conn.prepareStatement(updateStatementString);
			statement.setString(1, owner);
			statement.setString(2, name);
			statement.setInt(3, id);
			statement.execute();
		}
		catch(SQLException e) {
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
	public void delete(int id)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = getConnection();
			statement = conn.prepareStatement(deleteStatementString);
			statement.setInt(1, id);
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
	 * Methode om een track toe te voegen aan een playlist
	 */
	public void addTrackToPlaylist(int playlistId, int trackId, int availability)
	{
		availabilityDAO.insert(playlistId, trackId, availability);
	}
	/*
	 * Methode om een track te verwijderen van een playlist
	 */
	public void deleteTrackFromPlaylist(int playlistId, int trackId)
	{
		availabilityDAO.delete(playlistId, trackId);
	}	 
}
