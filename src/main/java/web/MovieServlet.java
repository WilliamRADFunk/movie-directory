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
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieRepository movRepo = new MovieRepository();
		
		if(request.getParameter("id") != null && request.getParameter("id") != "") {
			int id = Integer.parseInt(request.getParameter("id"));
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
		MovieRepository movRepo = new MovieRepository();
		
		String msg = "";
		boolean success = false;
		if(		request.getParameter("title") != null &&
				request.getParameter("synopsis") != null &&
				request.getParameter("optimalSeason") != null &&
				request.getParameter("worstSeason") != null &&
				request.getParameter("costLicense") != null &&
				request.getParameter("licenseLength") != null &&
				request.getParameter("producedBy") != null)
		{
			Movie movie = movRepo.createMovie(request.getParameter("title"), request.getParameter("synopsis"), Integer.parseInt(request.getParameter("optimalSeason")), Integer.parseInt(request.getParameter("worstSeason")), Double.parseDouble(request.getParameter("costLicense")), Integer.parseInt(request.getParameter("licenseLength")), request.getParameter("producedBy"));
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
		else if(request.getParameter("title") == null || request.getParameter("title") == "")
		{
			msg = "Invalid title.";
		}
		else if(request.getParameter("synopsis") == null || request.getParameter("synopsis") == "")
		{
			msg = "Invalid synopsis.";
		}
		else if(request.getParameter("optimalSeason") == null ||
				Integer.parseInt(request.getParameter("optimalSeason")) < 0 ||
				Integer.parseInt(request.getParameter("optimalSeason")) > 3)
		{
			msg = "Invalid Optimal Season.";
		}
		else if(request.getParameter("worstSeason") == null ||
				Integer.parseInt(request.getParameter("worstSeason")) < 0 ||
				Integer.parseInt(request.getParameter("worstSeason")) > 3)
		{
			msg = "Invalid Worst Season.";
		}
		else if(request.getParameter("costLicense") == null ||
				Integer.parseInt(request.getParameter("costLicense")) < 1000 ||
				Integer.parseInt(request.getParameter("costLicense")) > 10000)
		{
			msg = "Invalid Cost of License.";
		}
		else if(request.getParameter("licenseLength") == null ||
				Integer.parseInt(request.getParameter("licenseLength")) < 12 ||
				Integer.parseInt(request.getParameter("licenseLength")) > 52)
		{
			msg = "Invalid License Duration.";
		}
		else if(request.getParameter("producedBy") == null || request.getParameter("producedBy") == "")
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
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/**
	 * Get movie.
	 */
	protected void getMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieRepository movRepo = new MovieRepository();
				
		if(request.getParameter("id") != null && request.getParameter("id") != "") {
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
