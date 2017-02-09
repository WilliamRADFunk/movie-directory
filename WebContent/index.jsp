<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World Java EE</title>
</head>
<body>
    <h1>Cinema Tycoon Movie Directory</h1>
	<form action="movie" method="post">
	    Enter a movie title: <input type="text" name="title" size="20">
	    <input type="submit" value="Call Servlet" />
	    <table>${movie}</table>
	</form>
	<%
        String[] id = request.getParameterValues("id");
		String fail = request.getParameter("HasFailed");
        if( id != null && fail == null) {
    %>
        <p>Id: ${id}</p>
        <p>Title: ${title}</p>
        <p>Synopsis: ${synopsis}</p>
        <p>Cost: ${costLicense}</p>
        <p>License Duration: ${licenseLength}</p>
        <p>Expected Popularity: ${expectedPopularity}</p>
        <p>Optimal Season: ${optimalSeason}</p>
        <p>Worst Season: ${worstSeason}</p>
        <p>Produced By: ${producedBy}</p>
        <p>Date Created: ${dateCreated}</p>
        <p>Date Last Modified: ${dateModified}</p>
    <%
        }
        else
        {
    %>
        <p>${msg}</p>
    <%
        }
    %>
</body>
</html>