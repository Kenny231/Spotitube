<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
  	 	 <title></title>
	</head>
	<body>
		<h2> Playlist bewerken </h2>
		<form action="viewPlaylistTracks" method="POST">
			<p>Verander naam: <input type="text" name="playlistName">
			<button type="submit">Submit</button></p>				
			<input type="hidden" name="playlistId" value="${playlistId}">
		</form>	
		<p>Playlist bewerken: <input type="button" onclick="location.href='addTracksView?playlistId=${playlistId}';" value="Bewerken" /></p>
		<form action="deletePlaylistView" method="POST">
			<p>Playlist verwijderen: <button type="submit" name="playlistId" value="${playlistId}">Verwijderen</button></p>	
		</form>	
		<h2> Mijn tracks </h2>
		<form action="viewPlaylistTracks" method="POST">
		<table>
 			<thead>
	    		<th>Id</th>
	    		<th>Performer</th>
	    		<th>Title</th>
	    		<th>URL</th>
	    		<th>Duration</th>
    		</thead>
    		<tbody>
    			<c:forEach items="${tracksByPlaylist}" var="current">
      				<tr>
        		    	<td>
        	            	<c:out value="${current.id}"/>
            			</td>
            			<td>
            				<c:out value="${current.performer}"/>
            			</td>
            			<td>
                    		<c:out value="${current.title}"/>
            			</td>
            			<td>
            				<c:out value="${current.url}"/>
            			</td>
            			<td>
                    		<c:out value="${current.duration}"/>
            			</td>
            			<td>
            				<button type="submit" name="trackId" value="${current.id}">Verwijder</button>
            			</td>            
        			</tr>
    			</c:forEach>
    		</tbody>
		</table>
		<input type="hidden" name="playlistId" value="${playlistId}">
		<br>
		<input type="button" onclick="location.href='index.jsp';" value="Terug" />	
		</form>
	</body>
</html>