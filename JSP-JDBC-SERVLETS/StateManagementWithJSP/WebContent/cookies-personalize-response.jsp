<html>
<head>
<title>Confirmation</title>
</head>
<%
	//read form data
String favLang = request.getParameter("favoriteLanguage");

//create cookie
Cookie theCookie = new Cookie("myApp.favoriteLanguage", favLang);

//set the life span of the cookie i.e. the total no of seconds
theCookie.setMaxAge(60 * 60 * 24 * 365);

//send cookie to browser
response.addCookie(theCookie);
%>
<body>
	Thanks! We set your favorite language to:
	<%=request.getParameter("favoriteLanguage")%>
	<br />
	<br />
	<a href="cookies-homepage.jsp">Return to homepage</a>
</body>
</html>