<html>
<head>
<title>Home Page</title>
</head>
<body>
	<h3>Training Portal</h3>
	<!-- read the favorite programming language cookie -->
	<%
		//the default...if there are no cookies
	String favLang = "Java";

	//get the cookies from the browser request
	Cookie[] theCookies = request.getCookies();

	//find our favorite language cookie
	if (theCookies != null) {
		for (Cookie tempCookie : theCookies) {
			if ("myApp.favoriteLanguage".equals(tempCookie.getName())) {
		favLang = tempCookie.getValue();
		break;
			}
		}
	}
	%>

	<!-- Personalized page for the use of "favLang" variable -->

	<!-- Books for this lang -->
	<h4>
		New books for
		<%=favLang%></h4>

	<ol>
		<li>blah blah blah</li>
		<li>blah blah blah</li>
		<li>blah blah blah</li>
	</ol>
	<br />
	<br />
	<!-- Jobs for this lang -->
	<h4>
		New Jobs for
		<%=favLang%></h4>

	<ol>
		<li>blah blah blah</li>
		<li>blah blah blah</li>
		<li>blah blah blah</li>
	</ol>
	<br />
	<br />
	<!-- News for this lang -->
	<h4>
		News for
		<%=favLang%></h4>
	
	<ol>
		<li>blah blah blah</li>
		<li>blah blah blah</li>
		<li>blah blah blah</li>
	</ol>
	<hr>
	<a href="cookies-personalized-form.html">Personalize the page</a>

</body>
</html>