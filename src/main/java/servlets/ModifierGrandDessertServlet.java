package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Grand_dessert;
import manager.Ensemble;

/**
 * Servlet implementation class SupprimerPetitDessertServlet
 */
@WebServlet("/modifierGrandDessert")
public class ModifierGrandDessertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierGrandDessertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean problem = false;
		
		//Recuperation du parametre GET
		String value = request.getParameter("grand_dessertId");
		int grand_dessertId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				grand_dessertId = Integer.parseInt(value);
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
				request.setAttribute("grand_dessert", Ensemble.getInstance().getGrand_dessert(grand_dessertId));
			}
			request.setAttribute("message", message);			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierGrandDessert.jsp");
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
			Grand_dessert grand_dessert = new Grand_dessert(sNom, sPrix,sId);
			Ensemble.getInstance().majGrand_dessert(grand_dessert);
			request.setAttribute("message", "Grand dessert mis a jour ! / Grand dessert updated !");			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierGrandDessert.jsp");
			view.forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
