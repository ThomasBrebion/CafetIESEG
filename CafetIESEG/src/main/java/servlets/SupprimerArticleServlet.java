package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Article;
import manager.Ensemble;

@WebServlet("/supprimerArticle")
public class SupprimerArticleServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		boolean problem = false;

		String value = request.getParameter("id");
		int id = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				id = Integer.parseInt(value);
			} catch (NumberFormatException | NullPointerException e) {
				problem = true;
			}
			String message = "";
			boolean asksConfirmation = false;
			if (problem)
			{
				message = "Probleme avec le parametre GET : \"" + value + "\" / Problem with the parameter GET : \"" + value + "\"";
			}
			else
			{
				Article article = Ensemble.getInstance().getArticle(id);
				request.setAttribute("article", article);	
			}
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerArticle.jsp");
			view.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean problem = false;

		String value = request.getParameter("id");
		int id = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				id = Integer.parseInt(value);
			} catch (NumberFormatException | NullPointerException e) {
				problem = true;
			}
			String message = "";
			boolean asksConfirmation = false;
			if (problem)
			{
				message = "Probleme avec le parametre POST : \"" + value + "\"";
			}
			else
			{
				Ensemble.getInstance().supprimerArticle(id);
				message = "Article supprime";
			}
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimerArticle.jsp");
			view.forward(request, response);
		}
	}



	private boolean isNullOrEmpty(String chaine) {
		return chaine == null || "".equals(chaine);
	}

}
