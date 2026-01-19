<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="in.sp.modal.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>

<%
    User user = (User) session.getAttribute("session_user");
/* 	•	Take the object stored in the session with the name "session_user".
	•	Convert (cast) it into a User object.
	•	Store it in a variable called user.
👉 In plain English:
“Get the logged-in user’s data from server memory.”
 */
%>

<h2>WELCOME</h2>

<h3>Name: <%= user.getName() %></h3>
<%-- 
	•	Calls getName() from the User object.
	•	Prints the user’s name on the web page.
👉 <%= %> means:
“Print this Java value in HTML.”
 --%>
 
<h3>Email: <%= user.getEmail() %></h3>
<h3>City: <%= user.getCity() %></h3>
<a href="Logout">Logout</a>

</body>
</html>