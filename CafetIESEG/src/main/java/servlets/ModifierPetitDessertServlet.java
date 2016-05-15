package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Petit_dessert;
import manager.Ensemble;

/**
 * Servlet implementation class SupprimerPetitDessertServlet
 */
@WebServlet("/modifierPetitDessert")
public class ModifierPetitDessertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierPetitDessertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean problem = false;
		
		//Recuperation du parametre GET
		String value = request.getParameter("petit_dessertId");
		int petit_dessertId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				petit_dessertId = Integer.parseInt(value);
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
				request.setAttribute("petit_dessert", Ensemble.getInstance().getPetit_dessert(petit_dessertId));
			}
			request.setAttribute("message", message);			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierPetitDessert.jsp");
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
			Petit_dessert petit_dessert = new Petit_dessert(sNom, sPrix,sId);
			Ensemble.getInstance().majPetit_dessert(petit_dessert);
			request.setAttribute("message", "Petit dessert mis a jour ! / Petit dessert updated !");			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierPetitDessert.jsp");
			view.forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
