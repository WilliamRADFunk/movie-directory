<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cinema Tycoon Movie Directory</title>
	<style>
		body
		{
			width: 100%;
		}
		input[type="text"]
		{
			min-width: 300px;
		}
		input[type="text"].hidden
		{
			display: none;
		}
		input[type="number"]
		{
			min-width: 300px;
		}
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
		#create-movie
		{
			display: flex;
			flex-direction: column;
			justify-content: center;
			margin-top: 40px;
		}
		#create-movie > table > tr > td
		{
			margin-top: 20px;
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
			<li><input type="submit" value="Get Movie" onclick="window.location='/movie.jsp'"/></li>
			<li>
				<form action="/api/v1/movies" method="get">
					<input type="submit" value="Get All Movies"/>
					<input class="hidden" type="text" name="action" size="20" value="getMovies">
				</form>
			</li>
			<li><input type="submit" class="active" value="Create Movie"></li>
			<li><input type="submit" value="Edit Movie" onclick="window.location='/edit.jsp'"/></li>
			<li><input type="submit" value="Delete Movie" onclick="window.location='/delete.jsp'"/></li>
			<li><input type="submit" value="Play Cinema Tycoon" onclick="window.location='http://www.williamrobertfunk.com/applications/cinema-tycoon/'"/></li>
		</ul>
	</div>
	<form id="#create-movie" action="/api/v1/movies" method="post">
		<table>
	    	<tr>
	    		<td>Title: </td><td><input type="text" name="title" size="35"></td>
	    	</tr>
	    	<tr>
	    		<td>Synopsis: </td><td><input type="text" name="synopsis" size="35"></td>
	    	</tr>
	    	<tr>
	    		<td>Optimal Season (0-3): </td><td><input type="number" min="0" max="3" step="1" name="optimalSeason" size="35"></td>
	    	</tr>
	    	<tr>
	    		<td>Worst Season (0-3): </td><td><input type="number" min="0" max="3" step="1" name="worstSeason" size="35"></td>
	    	</tr>
	    	<tr>
	    		<td>Cost ($1,000 - $10,000): </td><td><input type="number" min="1000" max="10000" step="100" name="costLicense" size="35"></td>
	    	</tr>
	    	<tr>
	    		<td>Duration (weeks): </td><td><input type="number" min="12" max="52" step="1" name="licenseLength" size="35"></td>
	    	</tr>
	    	<tr>
	    		<td>Your Name: </td><td><input type="text" name="producedBy" size="35"></td>
	    	</tr>
	    </table>
	    <input type="submit" value="Submit Movie" />
	</form>
	<%
		String[] id = request.getParameterValues("id");
		String fail = request.getParameter("HasFailed");
        if(fail == null)
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