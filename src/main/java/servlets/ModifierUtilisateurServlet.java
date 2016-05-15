package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Utilisateur;
import manager.Ensemble;

/**
 * Servlet implementation class SupprimerUtilisateurServlet
 */
@WebServlet("/modifierUtilisateur")
public class ModifierUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierUtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		boolean problem = false;
		
		//Recuperation du parametre GET
		String value = request.getParameter("utilisateurId");
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
			if (problem)
			{
				message = "Probleme avec le parametre GET : \"" + value + "\"";
			}
			else
			{
				request.setAttribute("utilisateur", Ensemble.getInstance().getUtilisateur(utilisateurId));
			}
			request.setAttribute("message", message);			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierUtilisateur.jsp");
			try {
				view.forward(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		System.out.println(request.getParameter("id"));
		String sId = request.getParameter("id");
		String sMail = request.getParameter("mail");
		String sMdp = request.getParameter("mdp");
		
		try {
			Utilisateur utilisateur = new Utilisateur(sMdp, sMail,Integer.parseInt(sId));
			Ensemble.getInstance().majUtilisateur(utilisateur);
			request.setAttribute("message", "Utilisateur mis a jour !");			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierUtilisateur.jsp");
			try {
				view.forward(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
