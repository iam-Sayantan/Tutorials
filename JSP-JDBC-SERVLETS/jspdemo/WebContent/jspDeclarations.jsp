<html>
<body>
	<h3>Hello world of Java</h3>
	<%!String makeItLower(String data) {
		return data.toLowerCase();
	}%>
	Lower case "GETTING OVER IT WITH BENNETT FODDY": "<%= makeItLower("GETTING OVER IT WITH BENNETT FODDY")%>"
</body>
</html>