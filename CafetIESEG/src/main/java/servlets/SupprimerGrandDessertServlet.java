package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Grand_dessert;
import manager.Ensemble;

@WebServlet("/supprimerGrandDessert")
public class SupprimerGrandDessertServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		List<Grand_dessert> grand_dessert = Ensemble.getInstance().listerGrand_dessert();
		request.setAttribute("listeGrand_dessert", grand_dessert);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerGrandDessert.jsp");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nom = request.getParameter("nom");

		if (this.isNullOrEmpty(nom)) {
			request.getSession().setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");
			response.sendRedirect("supprimerGrandDessert");
			} else {
			Ensemble.getInstance().supprimerGrandDessert(nom);
			response.sendRedirect("modificationOK");
		}
	}

	private boolean isNullOrEmpty(String chaine) {
		return chaine == null || "".equals(chaine);
	}

}
