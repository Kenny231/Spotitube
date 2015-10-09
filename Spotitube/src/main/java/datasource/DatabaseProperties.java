package datasource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {
	private static final String FILE_NAME = "database.properties";
	
	private String driver;
	private String connection_string;
	private String username;
	private String password;
		
	private Properties prop;
	
	public DatabaseProperties()
	{
		loadPropertyFile();
		setPropValues();
	}
		
	private void loadPropertyFile()
	{
		prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		
		try {
			if (inputStream != null) 
				prop.load(inputStream);
			else
				throw new FileNotFoundException("property file '" + FILE_NAME + "' not found in the classpath");			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				inputStream.close();
			} catch (IOException e) { }
		}
	}
	
	private void setPropValues()
	{
		driver = prop.getProperty("driver");
		connection_string = prop.getProperty("connection_string");
		username = prop.getProperty("username");
		password = prop.getProperty("password");	
	}
	
	public String getDriver()
	{
		return driver;
	}
	public String getConnectionString()
	{
		return connection_string;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
}

