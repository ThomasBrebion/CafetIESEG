package servlets;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Utilisateur;
import manager.Ensemble;

public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String un=request.getParameter("id");
		String pw=request.getParameter("mdp");
		List<Utilisateur> utilisateur = Ensemble.getInstance().listerUtilisateurs();
		String id = utilisateur.get(0).getIdentifiant();
		String mdp = utilisateur.get(0).getMotDePasse();
		
		if(un.equals(id) && pw.equals(mdp))
		{
			response.sendRedirect("espaceprive");
			return;
		}
		else
		{
			
			response.sendRedirect("espace");
			request.getSession().setAttribute("messageErreur", "Mauvais identifiant ou mot de passe.");
			return;
		}
	}
}