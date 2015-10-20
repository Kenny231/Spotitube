package nl.ica.oose.dea.Spotitube;

import org.junit.*;

import static org.junit.Assert.*;
import datasource.test.FakePlaylistDAO;
import service.rest.IPlaylistResource;
import service.rest.PlaylistResource;

public class PlaylistServiceTest {
	private IPlaylistResource resource;
	/*
	 * Setup, resource aanmaken met een fake playlist DAO.
	 */
	@Before
    public void setUp() throws Exception {
		resource = new PlaylistResource(new FakePlaylistDAO());
    }
	
	@Test
	public void testFindAll()
	{
		assertEquals(0, resource.getAllPlaylists().size());
	}
	
	@Test
	public void testInsert()
	{
		resource.addPlaylist("Kenny", "myList");
		assertEquals(1, resource.getAllPlaylists().size());
	}
		
	@Test
	public void testGetters()
	{
		resource.addPlaylist("Kenny", "myList");
		assertEquals("myList", resource.getPlaylistById(0).getName());
		assertEquals(1, resource.getPlaylistByOwner("Kenny").size());		
	}
	
	@Test
	public void testUpdate()
	{
		resource.addPlaylist("Kenny", "myList");
		resource.changePlaylistName(0, "notMyList");
		assertEquals("notMyList", resource.getPlaylistById(0).getName());		
	}
}
