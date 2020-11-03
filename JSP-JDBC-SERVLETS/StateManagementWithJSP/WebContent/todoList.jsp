<%@ page import="java.util.*"%>
<html>
<body>

	<!-- Step 1: Create HTML Form -->

	<form action="todoList.jsp">
		Add new item: <input type="text" name="theItem" /> <input
			type="submit" value="Submit" /> <br /> Item Entered:
		<%=request.getParameter("theItem")%>
	</form>

	<!-- Step 2: Add new items to "TO DO" list -->

	<%
		// get the TO DO items from the session
	List<String> items = (List<String>) session.getAttribute("myToDoList");

	// if there is no items, create a new one

	if (items == null) {
		items = new ArrayList<String>();
		session.setAttribute("myToDoList", items);
	}

	//see if there is any form data to add
	String theItem = request.getParameter("theItem");
	if (theItem != null && !items.contains(theItem)) {
		items.add(theItem);
		
		response.sendRedirect("todoList.jsp"); 
	}
	%>

	<!-- Step 3: Display all "To Do" item from session -->

	<hr>
	<b>To List Items</b>

	<ol>
		<%
			for (String Temp : items) {
			out.println("<li>" + Temp + "</li>");
		}
		%>
	</ol>

</body>
</html>