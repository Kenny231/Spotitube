package datasource;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import datasource.util.DatabaseProperties;

public abstract class Database {
	private static DatabaseProperties prop = new DatabaseProperties();
	/*
	 * Connection variabele, bovenaan declareren zodat,
	 * de disconnect methode zonder argument aangeroepen kan worden.
	 */
	private Connection conn = null;
	/*
	 * De driver hoeft maar één keer geladen te worden dus
	 * een statische variabele.
	 */
	private static boolean driverLoaded = false;
	private final static Logger LOGGER = Logger.getLogger(Database.class.getName());
	
	protected Database()
	{
		LoadDriver();
	}
	/*
	 * Methode om een database connectie te leggen.
	 * @Return Connection connectie.
	 */
	protected Connection getConnection()
	{
		String url = prop.getConnectionString();
		String username = prop.getUsername();
		String password = prop.getPassword();
		
		try {
			if (conn != null && !conn.isClosed())
				return conn;
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Can't establish a database connection.", e);
		} 
		return conn;
	}
	/*
	 * Methode om de database connectie en statement te sluiten.
	 */
	protected void closeStatement(PreparedStatement statement)
	{
		try {
			statement.close();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Can't close a database resource.", e);
		} catch (NullPointerException e) { /* niks doen */ }
	}
	protected void closeDatabase()
	{
		try {
			conn.close();
		} 
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Can't close a database resource.", e);
		} catch (NullPointerException e) { /* niks doen */ }
	}
	/*
	 * Methode om de database driver te laden.
	 * Dit hoeft maar één keer te gebeuren.
	 */
	private static void LoadDriver() {
		try {
			if (!driverLoaded)
				Class.forName(prop.getDriver());
		}
		catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "Can't load the database driver, check the database.properties file.", e);
		}
	}
}
