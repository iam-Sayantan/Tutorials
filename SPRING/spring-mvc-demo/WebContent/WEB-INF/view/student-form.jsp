<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Registration Form</title>
</head>
<body>
	<form:form action="processForm" modelAttribute="student">
	
	First Name: <form:input path="firstName"/>
	<br><br>
	Last Name: <form:input path="lastName"/>
	<br><br>
	
	<form:select path="country">
	
	<!-- Itesm refer to the collection of data and Spring will call student.getCountryOptions() -->
		<form:options items="${student.countryOptions}"/>
		
		
		<!-- <form:option value="France" label="France"/>
		<form:option value="Germany" label="Germany"/>
		<form:option value="India" label="India"/> -->
		
	</form:select>
	
	<br><br>
	
	Favorite Language: 
	
	<!-- On submission, Spring will call student.setFavoriteLanguage(..) -->
	Java<form:radiobutton path="favoriteLanguage" value="Java"/>
	Python<form:radiobutton path="favoriteLanguage" value="Python"/>
	C++<form:radiobutton path="favoriteLanguage" value="C++"/>
	C#<form:radiobutton path="favoriteLanguage" value="C#"/>
	
	<br><br>
	
	Favorite OS:
	
	<!-- On submission, Spring will call student.setOperatingSystem(..) -->
	Linux <form:checkbox path="operatingSystem" value="Linux"/>
	Mac OS <form:checkbox path="operatingSystem" value="Mac OS"/>
	Windows <form:checkbox path="operatingSystem" value="MS Windows"/>
	
	<br><br>
	<input type="submit" value="Submit">
		
	
	</form:form>
</body>
</html>