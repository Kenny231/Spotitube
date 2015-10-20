package domain;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	private String owner;
	private String name;
	private int id;
		
	public Playlist(int id, String owner, String name)
	{
		this.id = id;
		this.owner = owner;
		this.name = name;
	}
	/*
	 * Getters and setters.
	 */
	public long getId()
	{
		return id;
	}
	public String getOwner()
	{
		return owner;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}
