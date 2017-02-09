<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cinema Tycoon Movie Directory</title>
	<style>
		h1
		{
			background-color: #008d8d;
			height: 60px;
			margin: 0px;
			padding-top: 30px;
			text-align: center;
		}
		hr
		{
			background-color: #000000;
			border: none;
			height: 2px;
			margin: 0px;
			padding: 0px;
		}
		input[type="submit"]
		{
			background-color: "#008b8b";
			height: 35px;
			margin-top: 0px;
		}
		input[type="submit"].active
		{
			background-color: "#0000FF";
			height: 35px;
			margin-top: 0px;
		}
		#get-movie
		{
			margin-top: 100px;
		}
		#nav
		{
			background-color: #008d8d;
			height: 60px;
			margin: 0 auto;
			text-align: center;
		}
		#nav ul
		{
			list-style: none;
			margin-top: 0px;
			padding-top: 12px;
		}
		#nav ul li
		{
			display: inline-block;
			margin-right: 10px;
			margin-top: 0px;
		}
	</style>
</head>
<body>
	<h1>Cinema Tycoon Movie Directory</h1>
	<hr/>
	<div id="nav">
		<ul>
			<li><input type="submit" class="active" value="Get Movie"></li>
			<li><form action="movies.jsp" method="get"><input type="submit" value="Get All Movies"/></form></li>
			<li><form action="create.jsp" method="get"><input type="submit" value="Create Movie"/></form></li>
			<li><form action="edit.jsp" method="get"><input type="submit" value="Edit Movie"/></form></li>
			<li><form action="delete.jsp" method="get"><input type="submit" value="Delete Movie"/></form></li>
			<li><input type="submit" value="Play Cinema Tycoon" onclick="window.location='http://www.williamrobertfunk.com/applications/cinema-tycoon/'"/></li>
		</ul>
	</div>
	<form id="get-movie" action="movie" method="get">
	    Enter a movie id: <input type="text" name="id" size="20">
	    <input type="submit" value="Get Movie" />
	    <table>${movie}</table>
	</form>
	<%
		String[] id = request.getParameterValues("id");
		String fail = request.getParameter("HasFailed");
        if( id != null && fail == null)
        {
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