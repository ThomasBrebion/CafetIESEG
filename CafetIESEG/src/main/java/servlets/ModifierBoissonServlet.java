package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Boissons;
import manager.Ensemble;

/**
 * Servlet implementation class SupprimerBoissonServlet
 */
@WebServlet("/modifierBoisson")
public class ModifierBoissonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierBoissonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean problem = false;
		
		//Recuperation du parametre GET
		String value = request.getParameter("boissonId");
		int boissonId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				boissonId = Integer.parseInt(value);
			} catch (NumberFormatException | NullPointerException e) {
				problem = true;
			}
			String message = "";
			if (problem)
			{
				message = "Probleme avec le parametre GET : \"" + value + "\" / Problem with the parameter GET : \"" + value + "\"";
			}
			else
			{
				request.setAttribute("boisson", Ensemble.getInstance().getBoissons(boissonId));
			}
			request.setAttribute("message", message);			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierBoisson.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("id"));
		Integer sId = Integer.parseInt(request.getParameter("id"));
		String sNom = request.getParameter("nom");
		Double sPrix = Double.parseDouble(request.getParameter("prix"));
		
		
		try {
			Boissons boisson = new Boissons(sNom, sPrix,sId);
			Ensemble.getInstance().majBoissons(boisson);
			request.setAttribute("message", "Boisson mis a jour ! / Boisson updated !");			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierBoisson.jsp");
			view.forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
