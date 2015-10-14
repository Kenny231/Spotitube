package domain;

public class Video extends Track{
	private int playcount;
	private String publicationDate;
	private String description;
	
	public Video(int id, String performer, String title, String url, int duration, int playcount, String publicationDate, String description)
	{
		super(id, performer, title, url, duration);
		this.playcount = playcount;
		this.publicationDate = publicationDate;
		this.description = description;
	}
	
	public int getPlaycount()
	{
		return playcount;
	}
	public String getPublicationDate()
	{
		return publicationDate;
	}
	public String getDescription()
	{
		return description;
	}
}
