package service.guice;

import presentation.AddPlaylistController;
import presentation.AddTrackController;
import presentation.DeletePlaylistController;
import presentation.PlaylistController;
import presentation.PlaylistTracksController;
import presentation.TrackController;
import service.rest.AvailabilityResource;
import service.rest.IAvailabilityResource;
import service.rest.IPlaylistResource;
import service.rest.ITrackResource;
import service.rest.PlaylistResource;
import service.rest.TrackResource;

import com.google.inject.servlet.ServletModule;

import datasource.AvailabilityDAO;
import datasource.IAvailabilityDAO;
import datasource.IPlaylistDAO;
import datasource.ITrackDAO;
import datasource.PlaylistDAO;
import datasource.TrackDAO;

public class SpotitubeModule extends ServletModule {
	@Override
	protected void configureServlets() {
		super.configureServlets();
		
		serve("/viewPlaylists").with(PlaylistController.class);
		serve("/addPlaylistView").with(AddPlaylistController.class);
		serve("/addTracksView").with(AddTrackController.class);
		serve("/deletePlaylistView").with(DeletePlaylistController.class);
		serve("/viewPlaylistTracks").with(PlaylistTracksController.class);
		serve("/viewTracks").with(TrackController.class);
		
		bind(IPlaylistResource.class).to(PlaylistResource.class);
		bind(ITrackResource.class).to(TrackResource.class);
		bind(IAvailabilityResource.class).to(AvailabilityResource.class);
		
		bind(IPlaylistDAO.class).to(PlaylistDAO.class);
		bind(ITrackDAO.class).to(TrackDAO.class);
		bind(IAvailabilityDAO.class).to(AvailabilityDAO.class);
	}
}

