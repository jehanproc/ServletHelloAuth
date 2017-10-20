<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Titre View helloAuth.jsp</title>
</head>
<body>

<h2>Bonjour à vous <b>${nom}</b> date de connexion: <b>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
out.write(sdf.format(session.getAttribute("dateConnexion")));
%>
</b> </h2>

</body>
</html>