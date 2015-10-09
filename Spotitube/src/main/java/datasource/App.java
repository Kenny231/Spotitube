package datasource;

import java.sql.SQLException;
import java.util.List;

import domain.Playlist;
import domain.Track;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       /* PlaylistDAO p = new PlaylistDAO();
        p.insert("Kenny", "Playlist");
        List<Playlist> playlist = p.findPlaylistByOwner("Kenny"); */
        /*for (Playlist list : playlist)
        {
        	System.out.println(list.getId() + " - " + list.getName());
        }*/
    	TrackDAO t = new TrackDAO();
    	List<Track> list = t.findTracksByPlaylistId(2);
    	for (Track tr : list)
    		System.out.println(tr.getId());
    }
}
