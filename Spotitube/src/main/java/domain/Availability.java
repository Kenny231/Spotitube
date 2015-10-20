package domain;

public class Availability {
	private int playlistId;
	private int trackId;
	private boolean offlineAvailable;
	
	public Availability(int trackId, int playlistId, boolean offlineAvailable)
	{
		this.trackId = trackId;
		this.playlistId = playlistId;
		this.offlineAvailable = offlineAvailable;
	}
	/* 
	 * Getters en setters
	 */
	public int getPlaylistId()
	{
		return playlistId;
	}
	public int getTrackId()
	{
		return trackId;
	}

	
	public void toggle()
	{
		offlineAvailable = !offlineAvailable;
	}
	public boolean isOfflineAvailable()
	{
		return offlineAvailable;
	}
}
