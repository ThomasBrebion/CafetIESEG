package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Article;
import manager.Ensemble;

/**
 * Servlet implementation class SupprimerArticleServlet
 */
@WebServlet("/modifierArticle")
public class ModifierArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		boolean problem = false;
		
		//Recuperation du parametre GET
		String value = request.getParameter("articleId");
		int articleId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				articleId = Integer.parseInt(value);
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
				request.setAttribute("article", Ensemble.getInstance().getArticle(articleId));
			}
			request.setAttribute("message", message);			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierArticle.jsp");
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
		String sTitre = request.getParameter("titre");
		String sAuteur = request.getParameter("auteur");
		String sText = request.getParameter("text");
		
		try {
			Article article = new Article(Integer.parseInt(sId), sText, sAuteur, sTitre);
			Ensemble.getInstance().majArticle(article);
			request.setAttribute("message", "Article mis a jour !");			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierArticle.jsp");
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
