package data.interfaces;

import java.util.List;

import data.entities.Movie;

public interface IMovieRepository {
	/**
	 * Create movie.
	 */
	public Movie createMovie(Movie m);
	
	/**
	 * Delete movie.
	 */
	public Movie deleteMovie(int id);
	
	/**
	 * Edit movie.
	 */
	public Movie editMovie(Movie m);
	
	/**
	 * Get movie.
	 */
	public Movie getMovie(int id);
	
	/**
	 * Get all movies.
	 */
	public List<Movie> getMovies();
}
