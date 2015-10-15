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
    	<th>Owner</th>
    	<th>Title</th>
    </thead>
    <tbody>
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
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>