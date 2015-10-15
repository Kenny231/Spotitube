package guice;

import presentation.PlaylistView;
import service.rest.IPlaylistResource;
import service.rest.PlaylistResource;

import com.google.inject.servlet.ServletModule;

import datasource.IPlaylistDAO;
import datasource.ITrackDAO;
import datasource.PlaylistDAO;
import datasource.TrackDAO;

public class SpotitubeModule extends ServletModule {
	@Override
	protected void configureServlets() {
		super.configureServlets();
		
		serve("/playlistView").with(PlaylistView.class);
		
		bind(IPlaylistResource.class).to(PlaylistResource.class);
		
		bind(IPlaylistDAO.class).to(PlaylistDAO.class);
		bind(ITrackDAO.class).to(TrackDAO.class);
	}
}

