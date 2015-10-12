package domain;

import java.util.ArrayList;
import java.util.List;

import datasource.PlaylistDAO;

public class Playlist {
	private String owner;
	private String name;
	private int id;
	
	private List<Track> trackList = new ArrayList<Track>();
	
	private PlaylistDAO playlistDAO;
		
	public Playlist(int id, String owner, String name, List<Track> trackList, PlaylistDAO playlistDAO)
	{
		this.id = id;
		this.owner = owner;
		this.name = name;
	}
	
	public void addTrack(Track track)
	{
		trackList.add(track);
		playlistDAO.addTrackToPlaylist(id, track.getId(), 0);
	}
	public void changeName(String name)
	{
		this.name = name;
		playlistDAO.update(id, owner, name);
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
