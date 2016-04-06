package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Article;
import manager.Ensemble;

@WebServlet("/ajoutArticle")
public class AjoutArticleServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutArticle.jsp");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String titre = request.getParameter("titre");
		String auteur = request.getParameter("auteur");
		String texte = request.getParameter("texte");
		
		int j = Ensemble.getInstance().listerArticles().size();

		if(j!=0){
			int lastId = Ensemble.getInstance().listerProduits().get(j-1).getId();
		if (this.isNullOrEmpty(titre) || this.isNullOrEmpty(auteur) || this.isNullOrEmpty(texte)) {
			request.getSession().setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");
			response.sendRedirect("ajoutArticle");
		} else {
			Article nouvelArticle = new Article(lastId+1,texte, auteur, titre);
			Ensemble.getInstance().ajouterArticle(nouvelArticle);
			response.sendRedirect("modificationOK");
			
		}}
		else{
			if (this.isNullOrEmpty(titre) || this.isNullOrEmpty(auteur) || this.isNullOrEmpty(texte)) {
				request.getSession().setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");
				response.sendRedirect("ajoutArticle");
			} else {
				Article nouvelArticle = new Article(1,texte, auteur, titre);
				Ensemble.getInstance().ajouterArticle(nouvelArticle);
				response.sendRedirect("modificationOK");
				
			}
			
		}
		
		
	}



	private boolean isNullOrEmpty(String chaine) {
		return chaine == null || "".equals(chaine);
	}

}
