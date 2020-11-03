<html>
<title>Student Confirmation Title</title>
<body>The student is confirmed: ${param.firstName}
	${param.lastName}
	<br/><br/>
	Country: ${param.country}
	<br/><br/>
	Favorite Programming Language: ${param.favoriteLanguage}
	
	<!-- Display list of favoriteSports -->
	<ul>
		<%
            String[] langs = request.getParameterValues("favoriteLanguage");
        
            if (langs != null) {
                for (String tempLang : langs) {
                    out.println("<li>" + tempLang + "</li>");
                }
            }
        %>
	</ul>
	
</body>
</html>