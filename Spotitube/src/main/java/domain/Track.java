package domain;

public abstract class Track {
	private int id;
	private String performer;
	private String title;
	private String url;
	private int duration;
	
	protected Track(int id, String performer, String title, String url, int duration)
	{
		this.id = id;
		this.performer = performer;
		this.title = title;
		this.url = url;
		this.duration = duration;
	}
	
	public int getId()
	{
		return id;
	}
	public String getPerformer()
	{
		return performer;
	}
	public String getTitle()
	{
		return title;
	}
	public String getURL()
	{
		return url;
	}
	public int getDuration()
	{
		return duration;
	}
}
