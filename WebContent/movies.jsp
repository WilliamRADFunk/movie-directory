<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cinema Tycoon Movie Directory</title>
	<style>
		input[type="text"].hidden
		{
			display: none;
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
			<li><input type="submit" value="Get Movie" onclick="window.location='/movie-directory/movie.jsp'"/></li>
			<li><input type="submit" class="active" value="Get All Movies"></li>
			<li><input type="submit" value="Create Movie" onclick="window.location='/movie-directory/create.jsp'"/></li>
			<li><input type="submit" value="Edit Movie" onclick="window.location='/movie-directory/edit.jsp'"/></li>
			<li><input type="submit" value="Delete Movie" onclick="window.location='/movie-directory/delete.jsp'"/></li>
			<li><input type="submit" value="Play Cinema Tycoon" onclick="window.location='http://www.williamrobertfunk.com/applications/cinema-tycoon/'"/></li>
		</ul>
	</div>
	<%
		String[] movieCount = request.getParameterValues("count");
		ArrayList<Integer> ids = (ArrayList<Integer>)request.getAttribute("ids");
		ArrayList<String> titles = (ArrayList<String>) request.getAttribute("titles");
		ArrayList<String> synopsises = (ArrayList<String>) request.getAttribute("synopsises");
		ArrayList<Double> expectedPopularities = (ArrayList<Double>) request.getAttribute("expectedPopularities");
		ArrayList<Integer> optimalSeasons = (ArrayList<Integer>) request.getAttribute("optimalSeasons");
		ArrayList<Integer> worstSeasons = (ArrayList<Integer>) request.getAttribute("worstSeasons");
		ArrayList<Double> costLicenses = (ArrayList<Double>) request.getAttribute("costLicenses");
		ArrayList<Integer> licenseLengths = (ArrayList<Integer>) request.getAttribute("licenseLengths");
		ArrayList<String> producedBys = (ArrayList<String>) request.getAttribute("producedBys");
		ArrayList<String> dateCreateds = (ArrayList<String>) request.getAttribute("dateCreateds");
		ArrayList<String> dateModifieds = (ArrayList<String>) request.getAttribute("dateModifieds");
		String fail = request.getParameter("HasFailed");
        if( movieCount != null && fail == null)
        {
        	int count = Integer.parseInt(movieCount[0]);
        	for(int i = 0; i < count; i++)
        	{
        		int id = ids.get(i);
        		String title = titles.get(i);
        		String synopsis = synopsises.get(i);
        		double expectedPopularity = expectedPopularities.get(i);
        		int optimalSeason = optimalSeasons.get(i);
        		int worstSeason = worstSeasons.get(i);
        		double costLicense = costLicenses.get(i);
        		int licenseLength = licenseLengths.get(i);
        		String producedBy = producedBys.get(i);
        		String dateCreated = dateCreateds.get(i);
        		String dateModified = dateModifieds.get(i);
	%>
		        <p>Id: <% out.print(id); %></p>
		        <p>Title: <% out.print(title); %></p>
		        <p>Synopsis: <% out.print(synopsis); %></p>
		        <p>Cost: <% out.print(costLicense); %></p>
		        <p>License Duration: <% out.print(licenseLength); %></p>
		        <p>Expected Popularity: <% out.print(expectedPopularity); %></p>
		        <p>Optimal Season: <% out.print(optimalSeason); %></p>
		        <p>Worst Season: <% out.print(worstSeason); %></p>
		        <p>Produced By: <% out.print(producedBy); %></p>
		        <p>Date Created: <% out.print(dateCreated); %></p>
		        <p>Date Last Modified: <% out.print(dateModified); %></p>
		        <hr/>
    <%
        	}
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