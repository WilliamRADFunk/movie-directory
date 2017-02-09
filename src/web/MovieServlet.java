package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

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
@WebServlet("/movie")
public class MovieServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieServlet() { super(); }
    
    /**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		MovieRepository movRepo = new MovieRepository();
		Movie movie;
		
		int id = Integer.parseInt(request.getParameter("id"));
        movie = movRepo.getMovie(id);
        if(movie != null)
        {
            movie = movRepo.getMovie(id);
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
        else
        {
            request.setAttribute("msg", "No movie exists with that id.");
            request.getRequestDispatcher("/movie.jsp?HasFailed=1").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

}
