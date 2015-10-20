package datasource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Playlist;

public class PlaylistDAO extends Database implements IPlaylistDAO {
	private final static Logger LOGGER = Logger.getLogger(PlaylistDAO.class.getName());
	// Statements
	private final static String findStatementString =
		"SELECT id, owner, name" +
		" FROM Playlist" +
		" WHERE owner = ?";
	private final static String findAllStatementString = 
		"SELECT id, owner, name" +
		" FROM playlist";
	private final static String findByIdStatementString = 
		"SELECT id, owner, name " +
		" FROM Playlist " +
		" WHERE id=?";
	private final static String insertStatementString = 
		"INSERT INTO Playlist VALUES (0, ?, ?)";
	private final static String deleteStatementString = 
		"DELETE FROM Playlist" + 
		" WHERE id = ?";
	private final static String updateStatementString = 
		"UPDATE Playlist" +
		" SET name=?" +
		" WHERE id=?";
	/*
	 * SELECT
	 */
	
	/*
	 * Methode om alle playlists op te halen.
	 */
	public List<Playlist> findAllPlaylists()
	{
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			Connection conn = getConnection();
			statement = conn.prepareStatement(findAllStatementString);
			rs = statement.executeQuery();
			List<Playlist> list = new ArrayList<Playlist>();
			addPlaylistsFromDatabase(rs, list);
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
	 * Methode om all playlists van een bepaalde owner op te halen.
	 */
	public List<Playlist> findPlaylistByOwner(String name)
	{
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			Connection conn = getConnection();
			statement = conn.prepareStatement(findStatementString);
			statement.setString(1, name);
			rs = statement.executeQuery();
			List<Playlist> list = new ArrayList<Playlist>();
			addPlaylistsFromDatabase(rs, list);
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
	 * Methode om een playlist bij een bepaalde id op te halen.
	 */
	public Playlist findPlaylistById(int id)
	{
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			Connection conn = getConnection();
			statement = conn.prepareStatement(findByIdStatementString);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			
			Playlist playlist = null;
			if (rs.next())
				playlist = new Playlist(rs.getInt(1), rs.getString(2), rs.getString(3));
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
	 * Methode om alle results in een list te stoppen.
	 */
	private void addPlaylistsFromDatabase(ResultSet rs, List<Playlist> playlist) throws SQLException {
		while (rs.next())
			playlist.add(new Playlist(rs.getInt(1), rs.getString(2), rs.getString(3)));
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
	public void update(int id, String name)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = getConnection();
			statement = conn.prepareStatement(updateStatementString);
			statement.setString(1, name);
			statement.setInt(2, id);
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
}
