package datasource;

import domain.Availability;

public interface IAvailabilityDAO {
	public void insert(int playlistId, int trackId, int availability);
	
	public void delete(int playlistId, int trackId);
}
