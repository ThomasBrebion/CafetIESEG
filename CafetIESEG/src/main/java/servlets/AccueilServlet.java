package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Article;
import manager.Ensemble;

@WebServlet("/accueil")
public class AccueilServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		/*Listing des articles et mise en attribut*/
		List<Article> articles = Ensemble.getInstance().listerArticles();
		request.setAttribute("listeArticles", articles);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		view.forward(request, response);
	}

}
