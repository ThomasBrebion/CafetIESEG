package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Article;
import manager.Ensemble;

@WebServlet("/article")
public class ArticleServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Article article = Ensemble.getInstance().getArticle(id);
			request.setAttribute("article", article);

			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/article.jsp");
			view.forward(request, response);
		} catch (NumberFormatException e) {
			response.sendRedirect("accueil");
		}
	}

}
