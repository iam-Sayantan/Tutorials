<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.*,com.sayantan.Student"%>

<%
	//just create sample data ...normally provided by MVC
List<Student> data = new ArrayList<>();

data.add(new Student("Sayantan", "Sengupta", true));
data.add(new Student("Sajeed", "Sarkar", false));
data.add(new Student("Sourav", "Sinha", false));

pageContext.setAttribute("myStudents", data);
%>

<html>
<body>
	<table border="2">
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Gold Customer</th>
		</tr>

		<c:forEach var="tempStudent" items="${myStudents}">
			<tr>
				<td>${tempStudent.firstName}</td>
				<td>${tempStudent.lastName}</td>
				<td>${tempStudent.goldCustomer}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>