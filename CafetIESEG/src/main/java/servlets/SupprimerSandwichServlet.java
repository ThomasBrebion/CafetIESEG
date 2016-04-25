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
 * Servlet implementation class SupprimerSandwichServlet
 */
@WebServlet("/supprimerSandwich")
public class SupprimerSandwichServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerSandwichServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean problem = false;
		
		//Recuperation du parametre GET
		String value = request.getParameter("askSandwichId");
		int sandwichId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				sandwichId = Integer.parseInt(value);
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
				message = "Voulez-vous supprimer le sandwich avec l'id " + sandwichId + " ? ";
				asksConfirmation = true;
			}
			request.setAttribute("message", message);
			request.setAttribute("sandwichId", sandwichId);
			request.setAttribute("confirmation", asksConfirmation);
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerSandwich.jsp");
			view.forward(request, response);
		}
		
		problem = false;
		value = request.getParameter("sandwichId");
		sandwichId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				sandwichId = Integer.parseInt(value);
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
				Ensemble.getInstance().supprimerSandwich(sandwichId);
				message = "L'article avec l'id " + sandwichId + " a ete supprime";
			}
			request.setAttribute("message", message);
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerSandwich.jsp");
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
