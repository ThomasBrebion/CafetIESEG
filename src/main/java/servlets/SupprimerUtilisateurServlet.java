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
 * Servlet implementation class SupprimerUtilisateurServlet
 */
@WebServlet("/supprimerUtilisateur")
public class SupprimerUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerUtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean problem = false;
		
		//Recuperation du parametre GET
		String value = request.getParameter("askUtilisateurId");
		int utilisateurId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				utilisateurId = Integer.parseInt(value);
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
				//Ensemble.getInstance().supprimerUtilisateur(utilisateurId);
				message = "Voulez-vous supprimer l'utilisateur avec l'id " + utilisateurId + " ? ";
				asksConfirmation = true;
			}
			request.setAttribute("message", message);
			request.setAttribute("utilisateurId", utilisateurId);
			request.setAttribute("confirmation", asksConfirmation);
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerUtilisateur.jsp");
			view.forward(request, response);
		}
		
		problem = false;
		value = request.getParameter("utilisateurId");
		utilisateurId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				utilisateurId = Integer.parseInt(value);
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
				Ensemble.getInstance().supprimerUtilisateur(utilisateurId);
				message = "L'utilisateur avec l'id " + utilisateurId + " a ete supprime";
			}
			request.setAttribute("message", message);
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerUtilisateur.jsp");
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
