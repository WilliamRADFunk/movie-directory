package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.entities.Movie;
import data.repositories.MovieRepository;

/**
 * Servlet implementation class MovieServlet
 */
@WebServlet("/api/v1/movies")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("getMovie")) {
			getMovie(request, response);
		}
		else if(action.equals("getMovies")) {
			getMovies(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("createMovie")) {
			createMovie(request, response);
		}
		else if(action.equals("deleteMovie")) {
			deleteMovie(request, response);
		}
		else if(action.equals("editMovie")) {
			editMovie(request, response);
		}
	}
	
	/**
	 * Create movie.
	 */
	private void createMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieRepository movRepo = new MovieRepository();
		
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		
		double expectedPopularity = ((double)((int)Math.ceil(Math.random() * 10))) / 10.0;
		double actualPopularity = ((double)((int)Math.ceil(Math.random() * 10))) / 10.0;
		
		String title = request.getParameter("title");
		String synopsis = request.getParameter("synopsis");
		String optimalSeason = request.getParameter("optimalSeason");
		String worstSeason = request.getParameter("worstSeason");
		String costLicense = request.getParameter("costLicense");
		String licenseLength = request.getParameter("licenseLength");
		String producedBy = request.getParameter("producedBy");
		
		String msg = "";
		boolean success = false;
		if(		title != null &&
				synopsis != null &&
				optimalSeason != null &&
				worstSeason != null &&
				costLicense != null &&
				licenseLength != null &&
				producedBy != null)
		{
			Movie mov = new Movie(0, title, synopsis, expectedPopularity, actualPopularity, Integer.parseInt(optimalSeason), Integer.parseInt(worstSeason), Double.parseDouble(costLicense), Integer.parseInt(licenseLength), producedBy, currentTime, currentTime);
			Movie movie = movRepo.createMovie(mov);
			if(movie != null) {

	            request.setAttribute("id", movie.getId());
	            request.setAttribute("title", movie.getTitle());
	            request.setAttribute("synopsis", movie.getSynopsis());
	            request.setAttribute("expectedPopularity", movie.getExpectedPopularity());
	            request.setAttribute("optimalSeason", movie.getOptimalSeason());
	            request.setAttribute("worstSeason", movie.getWorstSeason());
	            request.setAttribute("costLicense", movie.getCostLicense());
	            request.setAttribute("licenseLength", movie.getLicenseLength());
	            request.setAttribute("producedBy", movie.getProducedBy());
	            request.setAttribute("dateCreated", movie.getDateCreated());
	            request.setAttribute("dateModified", movie.getDateModified());
	            request.getRequestDispatcher("/create.jsp").forward(request, response);
	            success = true;
	        }
			else {
	            request.setAttribute("msg", "Failed to create your movie.");
	            request.getRequestDispatcher("/create.jsp?HasFailed=1").forward(request, response);
	        }
		}
		else if(title == null)
		{
			msg = "Invalid title.";
		}
		else if(synopsis == null)
		{
			msg = "Invalid synopsis.";
		}
		else if(optimalSeason == null ||
				Integer.parseInt(optimalSeason) < 0 ||
				Integer.parseInt(optimalSeason) > 3)
		{
			msg = "Invalid Optimal Season.";
		}
		else if(worstSeason == null ||
				Integer.parseInt(worstSeason) < 0 ||
				Integer.parseInt(worstSeason) > 3)
		{
			msg = "Invalid Worst Season.";
		}
		else if(costLicense == null ||
				Integer.parseInt(costLicense) < 1000 ||
				Integer.parseInt(costLicense) > 10000)
		{
			msg = "Invalid Cost of License.";
		}
		else if(licenseLength == null ||
				Integer.parseInt(licenseLength) < 12 ||
				Integer.parseInt(licenseLength) > 52)
		{
			msg = "Invalid License Duration.";
		}
		else if(request.getParameter("producedBy") == null)
		{
			msg = "Invalid name.";
		}
		else
		{
			msg = "Invalid input.";
		}
		if(!success) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/create.jsp?HasFailed=1").forward(request, response);
		}
	}
	
	/**
	 * Delete movie.
	 */
	private void deleteMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieRepository movRepo = new MovieRepository();
		
		String idStr = request.getParameter("id");
		
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			Movie movie = movRepo.deleteMovie(id);
			
	        if(movie != null) {
	            request.setAttribute("id", movie.getId());
	            request.setAttribute("title", movie.getTitle());
	            request.setAttribute("synopsis", movie.getSynopsis());
	            request.setAttribute("expectedPopularity", movie.getExpectedPopularity());
	            request.setAttribute("optimalSeason", movie.getOptimalSeason());
	            request.setAttribute("worstSeason", movie.getWorstSeason());
	            request.setAttribute("costLicense", movie.getCostLicense());
	            request.setAttribute("licenseLength", movie.getLicenseLength());
	            request.setAttribute("producedBy", movie.getProducedBy());
	            request.setAttribute("dateCreated", movie.getDateCreated());
	            request.setAttribute("dateModified", movie.getDateModified());
	            request.getRequestDispatcher("/delete.jsp").forward(request, response);
	        }
	        else {
	            request.setAttribute("msg", "No movie exists with that id.");
	            request.getRequestDispatcher("/delete.jsp?HasFailed=1").forward(request, response);
	        }
		}
	}
	
	/**
	 * Edit movie.
	 */
	private void editMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieRepository movRepo = new MovieRepository();
		
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String synopsis = request.getParameter("synopsis");
		String optimalSeason = request.getParameter("optimalSeason");
		String worstSeason = request.getParameter("worstSeason");
		String costLicense = request.getParameter("costLicense");
		String licenseLength = request.getParameter("licenseLength");
		String producedBy = request.getParameter("producedBy");
		
		if(request.getParameter("id") != null) {
			String msg = "";
			boolean success = false;
			if(		id != null &&
					title != null &&
					synopsis != null &&
					optimalSeason != null &&
					worstSeason != null &&
					costLicense != null &&
					licenseLength != null &&
					producedBy != null)
			{
				Movie mov = new Movie(Integer.parseInt(id), title, synopsis, 0.0, 0.0, Integer.parseInt(optimalSeason), Integer.parseInt(worstSeason), Double.parseDouble(costLicense), Integer.parseInt(licenseLength), producedBy, currentTime, currentTime);
				Movie movie = movRepo.editMovie(mov);
				if(movie != null) {
	
		            request.setAttribute("id", movie.getId());
		            request.setAttribute("title", movie.getTitle());
		            request.setAttribute("synopsis", movie.getSynopsis());
		            request.setAttribute("expectedPopularity", movie.getExpectedPopularity());
		            request.setAttribute("optimalSeason", movie.getOptimalSeason());
		            request.setAttribute("worstSeason", movie.getWorstSeason());
		            request.setAttribute("costLicense", movie.getCostLicense());
		            request.setAttribute("licenseLength", movie.getLicenseLength());
		            request.setAttribute("producedBy", movie.getProducedBy());
		            request.setAttribute("dateCreated", movie.getDateCreated());
		            request.setAttribute("dateModified", movie.getDateModified());
		            request.getRequestDispatcher("/edit.jsp").forward(request, response);
		            success = true;
		        }
				else {
		            request.setAttribute("msg", "Failed to edit your movie.");
		            request.getRequestDispatcher("/edit.jsp?HasFailed=1").forward(request, response);
		        }
			}
			else if(id == null) {
				msg = "Invalid id.";
			}
			else if(title == null) {
				msg = "Invalid title.";
			}
			else if(synopsis == null) {
				msg = "Invalid synopsis.";
			}
			else if(optimalSeason == null || Integer.parseInt(optimalSeason) < 0 ||	Integer.parseInt(optimalSeason) > 3) {
				msg = "Invalid Optimal Season.";
			}
			else if(worstSeason == null || Integer.parseInt(worstSeason) < 0 ||	Integer.parseInt(worstSeason) > 3)
			{
				msg = "Invalid Worst Season.";
			}
			else if(costLicense == null || Integer.parseInt(costLicense) < 1000 || Integer.parseInt(costLicense) > 10000)
			{
				msg = "Invalid Cost of License.";
			}
			else if(licenseLength == null || Integer.parseInt(licenseLength) < 12 || Integer.parseInt(licenseLength) > 52)
			{
				msg = "Invalid License Duration.";
			}
			else if(producedBy == null)
			{
				msg = "Invalid name.";
			}
			else
			{
				msg = "Invalid input.";
			}
			if(!success) {
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/edit.jsp?HasFailed=1").forward(request, response);
			}
		}
	}
	
	/**
	 * Get movie.
	 */
	protected void getMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieRepository movRepo = new MovieRepository();
				
		if(request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			Movie movie = movRepo.getMovie(id);
			
	        if(movie != null) {
	            request.setAttribute("id", movie.getId());
	            request.setAttribute("title", movie.getTitle());
	            request.setAttribute("synopsis", movie.getSynopsis());
	            request.setAttribute("expectedPopularity", movie.getExpectedPopularity());
	            request.setAttribute("optimalSeason", movie.getOptimalSeason());
	            request.setAttribute("worstSeason", movie.getWorstSeason());
	            request.setAttribute("costLicense", movie.getCostLicense());
	            request.setAttribute("licenseLength", movie.getLicenseLength());
	            request.setAttribute("producedBy", movie.getProducedBy());
	            request.setAttribute("dateCreated", movie.getDateCreated());
	            request.setAttribute("dateModified", movie.getDateModified());
	            request.getRequestDispatcher("/movie.jsp").forward(request, response);
	        }
	        else {
	            request.setAttribute("msg", "No movie exists with that id.");
	            request.getRequestDispatcher("/movie.jsp?HasFailed=1").forward(request, response);
	        }
		}
	}
	
	/**
	 * Get all movies.
	 */
	private void getMovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieRepository movRepo = new MovieRepository();
		
		List<Movie> movies = new ArrayList<Movie>();
		movies = movRepo.getMovies();
		
		List<Integer> ids = new ArrayList<Integer>();
		List<String> titles = new ArrayList<String>();
		List<String> synopsises = new ArrayList<String>();
		List<Double> expectedPopularities = new ArrayList<Double>();
		List<Integer> optimalSeasons = new ArrayList<Integer>();
		List<Integer> worstSeasons = new ArrayList<Integer>();
		List<Double> costLicenses = new ArrayList<Double>();
		List<Integer> licenseLengths = new ArrayList<Integer>();
		List<String> producedBys = new ArrayList<String>();
		List<String> dateCreateds = new ArrayList<String>();
		List<String> dateModifieds = new ArrayList<String>();
		
		
		if(movies != null) {
			for(Movie movie : movies) {
				ids.add(movie.getId());
				titles.add(movie.getTitle());
				synopsises.add(movie.getSynopsis());
				expectedPopularities.add(movie.getExpectedPopularity());
				optimalSeasons.add(movie.getOptimalSeason());
				worstSeasons.add(movie.getWorstSeason());
				costLicenses.add(movie.getCostLicense());
				licenseLengths.add(movie.getLicenseLength());
				producedBys.add(movie.getProducedBy());
				dateCreateds.add(movie.getDateCreated());
				dateModifieds.add(movie.getDateModified());
			}
			request.setAttribute("ids", ids);
			request.setAttribute("titles", titles);
			request.setAttribute("synopsises", synopsises);
			request.setAttribute("expectedPopularities", expectedPopularities);
			request.setAttribute("optimalSeasons", optimalSeasons);
			request.setAttribute("worstSeasons", worstSeasons);
			request.setAttribute("costLicenses", costLicenses);
			request.setAttribute("licenseLengths", licenseLengths);
			request.setAttribute("producedBys", producedBys);
			request.setAttribute("dateCreateds", dateCreateds);
			request.setAttribute("dateModifieds", dateModifieds);
			request.getRequestDispatcher("/movies.jsp?count=" + ids.size()).forward(request, response);
		}
		else {
            request.setAttribute("msg", "No movies in the database.");
            request.getRequestDispatcher("/movies.jsp?HasFailed=1").forward(request, response);
        }
	}
}
