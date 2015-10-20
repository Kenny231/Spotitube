<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
    	<title></title>
	</head>
	<body>
		<form action="viewTracks" method="GET">
			<h2> Search </h2>
						
			<p>Zoek op titel: <input type="text" name="title">
			<button type="submit">Submit</button></p>
			
			<h2> Results </h2>
			
			<table>
	 			<thead>
	    			<th>Id</th>
	    			<th>Performer</th>
	    			<th>Title</th>
	    			<th>URL</th>
	    			<th>Duration</th>
	    		</thead>
	    		<tbody>
	    			<c:forEach items="${tracks}" var="current">
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
	        			</tr>
	    			</c:forEach>
	    		</tbody>
	    	</form>	
		</table>
		<br>
		<input type="button" onclick="location.href='index.jsp';" value="Terug" />	
	</body>
</html>