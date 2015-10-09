package domain;

public abstract class Track {
	private long id;
	private String performer;
	private String title;
	private String url;
	private int duration;
	
	protected Track(long id, String performer, String title, String url, int duration)
	{
		this.id = id;
		this.performer = performer;
		this.title = title;
		this.url = url;
		this.duration = duration;
	}
	
	public long getId()
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
