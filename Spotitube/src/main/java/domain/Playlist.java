package domain;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	private String owner;
	private String name;
	private int id;
	
	private List<Track> trackList = new ArrayList<Track>();
		
	public Playlist(int id, String owner, String name, List<Track> trackList)
	{
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.trackList = trackList;
	}
	
	public void addTrack(Track track)
	{
		trackList.add(track);
	}
	public void changeName(String name)
	{
		this.name = name;
	}
	
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
}
