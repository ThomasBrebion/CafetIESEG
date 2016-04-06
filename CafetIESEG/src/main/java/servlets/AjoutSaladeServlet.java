package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Salades;
import manager.Ensemble;

@WebServlet("/ajouterSalade")
public class AjoutSaladeServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutSalade.jsp");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nom = request.getParameter("nom");
		Double prix_solo = null ;
		Double prix_menu = null;
		
		try {
			prix_solo = Double.parseDouble(request.getParameter("prix_solo"));
			prix_menu = Double.parseDouble(request.getParameter("prix_menu"));
		} catch (NumberFormatException e) {

		}

		if (prix_solo == null || prix_menu == null || this.isNullOrEmpty(nom)) {
			request.getSession().setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");
			response.sendRedirect("ajouterSalade");
		} else {
			Salades nouveauSalade = new Salades(nom,prix_solo,prix_menu);
			Ensemble.getInstance().ajouterSalades(nouveauSalade);
			response.sendRedirect("modificationOK");
			
		}
		
		
	}



	private boolean isNullOrEmpty(String chaine) {
		return chaine == null || "".equals(chaine);
	}

}
