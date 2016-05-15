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
 * Servlet implementation class SupprimerProduitServlet
 */
@WebServlet("/supprimerProduit")
public class SupprimerProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerProduitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean problem = false;
		
		//Recuperation du parametre GET
		String value = request.getParameter("askProduitId");
		int produitId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				produitId = Integer.parseInt(value);
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
				//Ensemble.getInstance().supprimerProduit(produitId);
				message = "Voulez-vous supprimer le produit avec l'id " + produitId + " ? ";
				asksConfirmation = true;
			}
			request.setAttribute("message", message);
			request.setAttribute("produitId", produitId);
			request.setAttribute("confirmation", asksConfirmation);
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerProduit.jsp");
			view.forward(request, response);
		}
		
		problem = false;
		value = request.getParameter("produitId");
		produitId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				produitId = Integer.parseInt(value);
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
				Ensemble.getInstance().supprimerProduit(produitId);
				message = "Le produit avec l'id " + produitId + " a ete supprime";
			}
			request.setAttribute("message", message);
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerProduit.jsp");
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
