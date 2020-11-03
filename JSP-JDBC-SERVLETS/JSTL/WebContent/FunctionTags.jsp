<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<html>
<body>
	<c:set var="data" value="SayantanSengupta"/>
	
	Length of the string <b>${data}</b>: ${fn:length(data)}
	<br/><br/>
	Uppercase version of the string <b>${data}</b>: ${fn:toUpperCase(data)}
	<br/><br/>
	Does the string <b>${data}</b> start with <b>Sayantan</b>?: ${fn:startsWith(data, "Sayantan" )}
	
</body>
</html>