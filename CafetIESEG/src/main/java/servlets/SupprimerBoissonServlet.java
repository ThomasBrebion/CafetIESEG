package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Boissons;
import manager.Ensemble;

@WebServlet("/supprimerBoisson")
public class SupprimerBoissonServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		List<Boissons> boisson = Ensemble.getInstance().listerBoissons();
		request.setAttribute("listeBoissons", boisson);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerBoisson.jsp");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nom = request.getParameter("nom");

		if (this.isNullOrEmpty(nom)) {
			request.getSession().setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");
			response.sendRedirect("supprimerBoisson");
			} else {
			Ensemble.getInstance().supprimerBoisson(nom);
			response.sendRedirect("modificationOK");
		}
	}

	private boolean isNullOrEmpty(String chaine) {
		return chaine == null || "".equals(chaine);
	}

}
