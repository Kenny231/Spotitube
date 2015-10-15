<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>

<table>
 	<thead>
    	<th>Id</th>
    	<th>Title</th>
    	<th>Duration</th>
    </thead>
    <tbody>
    	<c:forEach items="${tracksByPlaylist}" var="current">
      	<tr>
            <td>
                    <c:out value="${current.id}"/>
            </td>
            <td>
                    <c:out value="${current.title}"/>
            </td>
            <td>
                    <c:out value="${current.duration}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>