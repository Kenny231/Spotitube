package service.rest;

public interface IAvailabilityResource {
	public void deleteTrackFromPlaylist(int playlistId, int trackId);
	
	public void addTrackToPlaylist(int playlistId, int trackId);
}
