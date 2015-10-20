<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
    	<title></title>
	</head>
	<body>
		<h2> Playlist </h2>
		<input type="button" onclick="location.href='addPlaylistView?owner=${owner}';" value="Playlist toevoegen" /><br>
		<h2> Uw playlists </h2>
		<table>
 			<thead>
    			<th>Id</th>
    			<th>Owner</th>
    			<th>Title</th>
    		</thead>
    		<tbody>
    			<form action="viewPlaylistTracks" method="GET">
    				<c:forEach items="${all}" var="current">
      					<tr>
            				<td>
                   				<c:out value="${current.id}"/>
            				</td>
            				<td>
                    			<c:out value="${current.owner}"/>
            				</td>
            				<td>
                    			<c:out value="${current.name}"/>
            				</td>
            				<td>
            					<button type="submit" name="playlistId" value="${current.id}">Bekijk</button>
            				</td>
        				</tr>
    				</c:forEach>
    			</form>
    		</tbody>
		</table>
		<br>
		<input type="button" onclick="location.href='index.jsp';" value="Terug" />	
	</body>
</html>