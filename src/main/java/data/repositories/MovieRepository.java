package data.repositories;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.entities.Movie;
import data.interfaces.IMovieRepository;

public class MovieRepository implements IMovieRepository {
	private String password = "williamchang";
	private String username = "williamfunk";
	private String url = "jdbc:mysql://mysql.creativecrew.org/creativecrew_cinemaempire";
	
	public MovieRepository() {
		
	}
	
	public Movie createMovie(String title, String synopsis, int optimalSeason, int worstSeason, double costLicense, int licenseLength, String producedBy) {
		try {
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			
			double expectedPopularity = 0.5;
			double actualPopularity = 0.5;
			
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection connection = DriverManager.getConnection(url, username, password);
			Statement statement = connection.createStatement();
			
			int numRowsAffected = statement.executeUpdate("INSERT INTO Movies (Title, Synopsis, `Expected Popularity`, `Actual Popularity`, `Optimal Season`, `Worst Season`, `Cost License`, `License Length`, `Produced By`, `Date Created`, `Date Modified`) VALUES ('" + title + "', '" + synopsis + "', " + expectedPopularity + ", " + actualPopularity + ", " + optimalSeason + ", " + worstSeason + ", " + costLicense + ", " + licenseLength + ", '" + producedBy + "', '" + currentTime + "', '" + currentTime + "');", Statement.RETURN_GENERATED_KEYS);
			System.out.println(numRowsAffected);
			Movie movie;
			if(numRowsAffected > 0) {
				movie = new Movie(numRowsAffected, title, synopsis, expectedPopularity, actualPopularity, optimalSeason, worstSeason, costLicense, licenseLength, producedBy, currentTime, currentTime);
				return movie;
			}
			else {
				return null;
			}
		}
		catch (SQLException | ClassNotFoundException e) {
			String err = e.toString();
			return null;
		}
	}
	
	public Movie deleteMovie(int id) {
		return null;
	}

	public Movie editMovie(int id, String title, String synopsis, double expectedPopularity, double actualPopularity, int optimalSeason, int worstSeason, double costLicense, int licenseLength, String producedBy, String dateModified) {
		return null;
	}

	/**
	 * Get movie.
	 */
	public Movie getMovie(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			java.sql.Connection connection = DriverManager.getConnection(url, username, password);
			
			Statement statement = connection.createStatement();
			
			ResultSet myResult;
			myResult = statement.executeQuery("SELECT * FROM Movies WHERE `ID`='" + id + "';");
			
			Movie movie;
			if(myResult.next()) {
				movie = new Movie(	myResult.getInt("ID"),
									myResult.getString("Title"),
									myResult.getString("Synopsis"),
									myResult.getDouble("Expected Popularity"),
									myResult.getDouble("Actual Popularity"),
									myResult.getInt("Optimal Season"),
									myResult.getInt("Worst Season"),
									myResult.getDouble("Cost License"),
									myResult.getInt("License Length"),
									myResult.getString("Produced By"),
									myResult.getString("Date Created"),
									myResult.getString("Date Modified")
								);
				return movie;
			}
			else {
				return null;
			}
		}
		catch (SQLException | ClassNotFoundException e) {
			String err = e.toString();
			return null;
		}
	}

	/**
	 * Get all movies.
	 */
	public List<Movie> getMovies() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			java.sql.Connection connection = DriverManager.getConnection(url, username, password);
			
			Statement statement = connection.createStatement();
			
			ResultSet myResult;
			myResult = statement.executeQuery("SELECT * FROM Movies;");
			
			List<Movie> movies = new ArrayList<Movie>();
			while(myResult.next()) {
				Movie movie = new Movie(	myResult.getInt("ID"),
									myResult.getString("Title"),
									myResult.getString("Synopsis"),
									myResult.getDouble("Expected Popularity"),
									myResult.getDouble("Actual Popularity"),
									myResult.getInt("Optimal Season"),
									myResult.getInt("Worst Season"),
									myResult.getDouble("Cost License"),
									myResult.getInt("License Length"),
									myResult.getString("Produced By"),
									myResult.getString("Date Created"),
									myResult.getString("Date Modified")
								);
				movies.add(movie);
			}
			return movies;
		}
		catch (SQLException | ClassNotFoundException e) {
			String err = e.toString();
			return null;
		}
	}
}
