package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Ensemble;

/**
 * Servlet implementation class SupprimerPetitDessertServlet
 */
@WebServlet("/supprimerPetit_dessert")
public class SupprimerPetitDessertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerPetitDessertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean problem = false;
		
		//Recuperation du parametre GET
		String value = request.getParameter("askPetit_dessertId");
		int Petit_dessertId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				Petit_dessertId = Integer.parseInt(value);
			} catch (NumberFormatException | NullPointerException e) {
				problem = true;
			}
			String message = "";
			boolean asksConfirmation = false;
			if (problem)
			{
				message = "Probleme avec le parametre GET : \"" + value + "\"";
			}
			else
			{
				message = "Voulez-vous supprimer le Petit Dessert avec l'id " + Petit_dessertId + " ? ";
				asksConfirmation = true;
			}
			request.setAttribute("message", message);
			request.setAttribute("Petit_dessertId", Petit_dessertId);
			request.setAttribute("confirmation", asksConfirmation);
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerPetitDessert.jsp");
			view.forward(request, response);
		}
		
		problem = false;
		value = request.getParameter("Petit_dessertId");
		Petit_dessertId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				Petit_dessertId = Integer.parseInt(value);
			} catch (NumberFormatException | NullPointerException e) {
				problem = true;
			}
			String message = "";
			if (problem)
			{
				message = "Probleme avec le parametre GET : \"" + value + "\"";
			}
			else
			{
				Ensemble.getInstance().supprimerPetit_dessert(Petit_dessertId);
				message = "Le petit dessert avec l'id " + Petit_dessertId + " a ete supprime";
			}
			request.setAttribute("message", message);
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerPetitDessert.jsp");
			view.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
