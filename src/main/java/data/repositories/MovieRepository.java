package data.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	public Movie createMovie(Movie m) {
		Connection sqlConnection = null;
		PreparedStatement sqlStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			sqlConnection = DriverManager.getConnection(url, username, password);
			
			sqlStatement = sqlConnection.prepareStatement("INSERT INTO Movies (Title, Synopsis, `Expected Popularity`, `Actual Popularity`, `Optimal Season`, `Worst Season`, `Cost License`, `License Length`, `Produced By`, `Date Created`, `Date Modified`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			sqlStatement.setString(1, m.getTitle().toString());
            sqlStatement.setString(2, m.getSynopsis().toString());
            sqlStatement.setDouble(3, m.getExpectedPopularity());
            sqlStatement.setDouble(4, m.getActualPopularity());
            sqlStatement.setInt(5, m.getOptimalSeason());
            sqlStatement.setInt(6, m.getWorstSeason());
            sqlStatement.setDouble(7, m.getCostLicense());
            sqlStatement.setInt(8, m.getLicenseLength());
            sqlStatement.setString(9, m.getProducedBy().toString());
            sqlStatement.setString(10, m.getDateCreated().toString());
            sqlStatement.setString(11, m.getDateModified().toString());
			int numRowsAffected = sqlStatement.executeUpdate();

			Movie movie;
			ResultSet rs;
			if(numRowsAffected > 0) {
				rs = sqlStatement.getGeneratedKeys();
				rs.next();
				movie = new Movie(rs.getInt(1), m.getTitle(), m.getSynopsis(), m.getExpectedPopularity(), m.getActualPopularity(), m.getOptimalSeason(), m.getWorstSeason(), m.getCostLicense(), m.getLicenseLength(), m.getProducedBy(), m.getDateModified(), m.getDateModified());
				sqlConnection.close();
				sqlStatement.close();
				rs.close();
				return movie;
			}
			else {
				sqlConnection.close();
				sqlStatement.close();
				return null;
			}
		}
		catch (SQLException | ClassNotFoundException e) {
			return null;
		}
	}
	
	/**
	 * Delete movie.
	 */
	public Movie deleteMovie(int id) {
		Connection sqlConnection = null;
		PreparedStatement sqlStatement = null;
		try {			
			Class.forName("com.mysql.jdbc.Driver");
			sqlConnection = DriverManager.getConnection(url, username, password);
			
			ResultSet myResult;
			sqlStatement = sqlConnection.prepareStatement("SELECT * FROM Movies WHERE `ID`=?;");
			sqlStatement.setInt(1, id);
			myResult = sqlStatement.executeQuery();
			
			if(myResult.next()) {
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
				sqlStatement = sqlConnection.prepareStatement("DELETE FROM Movies WHERE `ID`=?;");
				sqlStatement.setInt(1, id);
				int numRowsAffected = sqlStatement.executeUpdate();
				
				sqlConnection.close();
				sqlStatement.close();
				myResult.close();

				if(numRowsAffected > 0) {
					return movie;
				}
				else {
					return null;
				}
			}
			else {
				sqlConnection.close();
				sqlStatement.close();
				myResult.close();
				return null;
			}
		}
		catch (SQLException | ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * Edit movie.
	 */
	public Movie editMovie(Movie m) {
		Connection sqlConnection = null;
		PreparedStatement sqlStatement = null;
		try {			
			Class.forName("com.mysql.jdbc.Driver");
			sqlConnection = DriverManager.getConnection(url, username, password);
			sqlStatement = sqlConnection.prepareStatement("UPDATE Movies SET Title=?, Synopsis=?, `Optimal Season`=?, `Worst Season`=?, `Cost License`=?, `License Length`=?, `Produced By`=?, `Date Modified`=? WHERE `ID`=?;");
			sqlStatement.setString(1, m.getTitle().toString());
            sqlStatement.setString(2, m.getSynopsis().toString());
            sqlStatement.setInt(3, m.getOptimalSeason());
            sqlStatement.setInt(4, m.getWorstSeason());
            sqlStatement.setDouble(5, m.getCostLicense());
            sqlStatement.setInt(6, m.getLicenseLength());
            sqlStatement.setString(7, m.getProducedBy().toString());
            sqlStatement.setString(8, m.getDateModified().toString());
            sqlStatement.setInt(9, m.getId());
			
			int numRowsAffected = sqlStatement.executeUpdate();
			
			if(numRowsAffected > 0) {
				ResultSet myResult;
				sqlStatement = sqlConnection.prepareStatement("SELECT * FROM Movies WHERE `ID`=?;");
				sqlStatement.setInt(1, m.getId());
				myResult = sqlStatement.executeQuery();

				if(myResult.next()) {
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
					sqlConnection.close();
					sqlStatement.close();
					myResult.close();
					return movie;
				}
				else {
					sqlConnection.close();
					sqlStatement.close();
					myResult.close();
					return null;
				}
			}
			else {
				return null;
			}
		}
		catch (SQLException | ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * Get movie.
	 */
	public Movie getMovie(int id) {
		Connection sqlConnection = null;
		PreparedStatement sqlStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			sqlConnection = DriverManager.getConnection(url, username, password);
			
			ResultSet myResult;
			sqlStatement = sqlConnection.prepareStatement("SELECT * FROM Movies WHERE `ID`=?;");
			sqlStatement.setInt(1, id);
			myResult = sqlStatement.executeQuery();
			
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
				sqlConnection.close();
				sqlStatement.close();
				myResult.close();
				return movie;
			}
			else {
				sqlConnection.close();
				sqlStatement.close();
				myResult.close();
				return null;
			}
		}
		catch (SQLException | ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * Get all movies.
	 */
	public List<Movie> getMovies() {
		Connection sqlConnection = null;
		PreparedStatement sqlStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			sqlConnection = DriverManager.getConnection(url, username, password);
			
			ResultSet myResult;
			sqlStatement = sqlConnection.prepareStatement("SELECT * FROM Movies;");
			myResult = sqlStatement.executeQuery();
			
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
			sqlConnection.close();
			sqlStatement.close();
			myResult.close();
			return movies;
		}
		catch (SQLException | ClassNotFoundException e) {
			return null;
		}
	}
}
