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
	/*
	 * Getters en setters
	 */
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
	public String getUrl()
	{
		return url;
	}
	public int getDuration()
	{
		return duration;
	}
}
