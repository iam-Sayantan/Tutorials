
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.List, java.util.ArrayList"%>


<%
	//just create some sample data..normally provided by MVC

String[] cities = { "Mumbai", "Kolkata", "Delhi" };

pageContext.setAttribute("myCities", cities); //Name = myCities and value is reference to that City's array.
%>


<html>

<body>

	<ol>

		<c:forEach var="tempCity" items="${myCities}">

			<li>${tempCity}</li>

		</c:forEach>

	</ol>


</body>

</html>