package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Petit_dessert;
import manager.Ensemble;

@WebServlet("/ajouterPetitDessert")
public class AjoutPetitDessertServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPetitDessert.jsp");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nom = request.getParameter("nom");
		Double prix = null ;
		
		try {
			prix = Double.parseDouble(request.getParameter("prix"));
		} catch (NumberFormatException e) {

		}

		if (prix == null || this.isNullOrEmpty(nom)) {
			request.getSession().setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");
			response.sendRedirect("ajouterPetitDessert");
		} else {
			Petit_dessert petit_dessert = new Petit_dessert(nom,prix);
			Ensemble.getInstance().ajouterPetit_dessert(petit_dessert);
			response.sendRedirect("modificationOK");
			
		}
		
		
	}



	private boolean isNullOrEmpty(String chaine) {
		return chaine == null || "".equals(chaine);
	}

}
