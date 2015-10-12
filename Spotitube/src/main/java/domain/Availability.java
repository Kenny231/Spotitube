package domain;

public class Availability {
	private boolean offlineAvailable;
	
	public Availability(boolean offlineAvailable)
	{
		this.offlineAvailable = offlineAvailable;
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
