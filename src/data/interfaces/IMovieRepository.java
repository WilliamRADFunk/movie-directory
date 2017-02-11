package data.interfaces;

import java.util.List;

import data.entities.Movie;

public interface IMovieRepository {
	/**
	 * Create movie.
	 */
	public Movie createMovie(int id, String title, String synopsis, double expectedPopularity, double actualPopularity, int optimalSeason, int worstSeason, double costLicense, int licenseLength, String producedBy, String dateCreated, String dateModified);
	
	/**
	 * Delete movie.
	 */
	public Movie deleteMovie(int id);
	
	/**
	 * Edit movie.
	 */
	public Movie editMovie(int id, String title, String synopsis, double expectedPopularity, double actualPopularity, int optimalSeason, int worstSeason, double costLicense, int licenseLength, String producedBy, String dateCreated, String dateModified);
	
	/**
	 * Get movie.
	 */
	public Movie getMovie(int id);
	
	/**
	 * Get all movies.
	 */
	public List<Movie> getMovies();
}