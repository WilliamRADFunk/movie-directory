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
	
	public Movie createMovie(int id, String title, String synopsis, double expectedPopularity, double actualPopularity, int optimalSeason, int worstSeason, double costLicense, int licenseLength, String producedBy, String dateCreated, String dateModified) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Movie deleteMovie(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Movie editMovie(int id, String title, String synopsis, double expectedPopularity, double actualPopularity,
			int optimalSeason, int worstSeason, double costLicense, int licenseLength, String producedBy,
			String dateCreated, String dateModified) {
		// TODO Auto-generated method stub
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
