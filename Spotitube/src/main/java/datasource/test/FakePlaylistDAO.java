package datasource.test;

import java.util.ArrayList;
import java.util.List;

import datasource.IPlaylistDAO;
import domain.Playlist;

public class FakePlaylistDAO implements IPlaylistDAO{
	
	private List<Playlist> playlist;
	/*
	 * Aanmaken 'database'.
	 */
	public FakePlaylistDAO()
	{
		playlist = new ArrayList<Playlist>();
	}
	
	@Override
	public List<Playlist> findAllPlaylists() {
		return playlist;
	}
	
	@Override
	public List<Playlist> findPlaylistByOwner(String name) {
		List<Playlist> ownerList = new ArrayList<Playlist>();
		for (Playlist play : playlist)
		{
			if (play.getOwner() == name)
				ownerList.add(play);
		}
		return ownerList;
	}

	@Override
	public Playlist findPlaylistById(int id) {
		if (id > playlist.size())
			return null;
		return playlist.get(id);
	}

	@Override
	public void insert(String owner, String name) {
		playlist.add(new Playlist(playlist.size()+1, owner, name));
	}

	@Override
	public void update(int id, String name) {
		if (id > playlist.size())
			return;		
		Playlist play = playlist.get(id);
		play.setName(name);
	}

	@Override
	public void delete(int id) {
		// 
	}

}
