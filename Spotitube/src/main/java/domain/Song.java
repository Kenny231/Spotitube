package domain;

public class Song extends Track{
	private String album;
	
	public Song(int id, String performer, String title, String url, int duration, String album)
	{
		super(id, performer, title, url, duration);
		this.album = album;
	}
	
	public String getAlbum()
	{
		return album;
	}
}
