<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
		p
		{
			margin-top: 50px;
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
			<li>
				<form action="api/v1/movies" method="get">
					<input type="submit" value="Get All Movies"/>
					<input class="hidden" type="text" name="action" size="20" value="getMovies">
				</form>
			</li>
			<li><input type="submit" value="Create Movie" onclick="window.location='/movie-directory/create.jsp'"/></li>
			<li><input type="submit" value="Edit Movie" onclick="window.location='/movie-directory/edit.jsp'"/></li>
			<li><input type="submit" value="Delete Movie" onclick="window.location='/movie-directory/delete.jsp'"/></li>
			<li><input type="submit" value="Play Cinema Tycoon" onclick="window.location='http://www.williamrobertfunk.com/applications/cinema-tycoon/'"/></li>
		</ul>
	</div>
	<p>This movie directory is a raw Java MVC application using RESTful API principles to display the player-created movies in Cinema Tycoon,
	a game described below and available to play by pressing the button above.</p>
	<p>Cinema Tycoon is an AngularJs 1 game that allows the player to take control of a run-down Movie Theater.
	The player can:</p>
	<ul>
		<li> Alter ticket prices</li>
		<li> Upgrade the parking area</li>
		<li> Purchase more concession snack options</li>
		<li> Invest in more video games for the arcade</li>
		<li> Hire or Fire employees</li>
		<li> Run promotional advertisements</li>
		<li> Build additional movie salons</li>
		<li> Buy more seats for each of their salons</li>
		<li> Improve screen size, projector quality, and sound intensity in each salon</li>
		<li> Purchase movie licenses</li>
		<li> Make their own database-persistent movies</li>
		<li> Race against a ticking clock</li>
		<li> Navigate random occurring events that improve or destabilize player's position</li>
		<li> Compete for highest wealth in the shortest time on a leaderboard</li>
	</ul>
</body>
</html>