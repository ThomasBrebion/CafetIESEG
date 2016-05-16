package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Salades;
import manager.Ensemble;

/**
 * Servlet implementation class SupprimerSaladeServlet
 */
@WebServlet("/modifierSalade")
public class ModifierSaladeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierSaladeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean problem = false;
		
		//Recuperation du parametre GET
		String value = request.getParameter("saladeId");
		int saladeId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				saladeId = Integer.parseInt(value);
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
				request.setAttribute("salade", Ensemble.getInstance().getSalade(saladeId));
			}
			request.setAttribute("message", message);			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierSalade.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Recuperation des parametres du formulaire*/
		System.out.println(request.getParameter("id"));
		Integer sId = Integer.parseInt(request.getParameter("id"));
		String sNom = request.getParameter("nom");
		Double sPrix_solo = Double.parseDouble(request.getParameter("prix_solo"));
		Double sPrix_menu = Double.parseDouble(request.getParameter("prix_menu"));
		
		try {
			/*Mise à jour*/
			Salades salade = new Salades(sNom, sPrix_solo, sPrix_menu,sId);
			Ensemble.getInstance().majSalade(salade);
			request.setAttribute("message", "Salade mis a jour ! / Salade updated !");			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierSalade.jsp");
			view.forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}