package domain;

public class Playlist {
	private String owner;
	private String name;
	private long id;
	
	public Playlist(long id, String owner, String name)
	{
		this.id = id;
		this.owner = owner;
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
