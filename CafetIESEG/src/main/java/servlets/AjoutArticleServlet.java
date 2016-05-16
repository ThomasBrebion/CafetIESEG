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
@WebServlet("/ajoutArticle")
public class AjoutArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);			
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutArticle.jsp");
		try {
			view.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*Récuperation des paramètres*/
		String sTitre = request.getParameter("titre");
		String sAuteur = request.getParameter("auteur");
		String sText = request.getParameter("text");
		
		int j = Ensemble.getInstance().listerArticles().size();
				
		request.setAttribute("message", "");
		
		/*On vérifie qu'il ne s'agit pas d'un titre qui existe déjà*/
		boolean Equal = false;
		for (int k=0;k<j;k++){
			if(Ensemble.getInstance().listerArticles().get(k).getTitre().equals(sTitre))
			{
				Equal = true;
			}
		}
		
		if(j!=0){
			/*S'il y a déjà des articles dans la bdd*/
			int lastId = Ensemble.getInstance().listerArticles().get(j-1).getId();
			/*Champs non nuls*/
		if (this.isNullOrEmpty(sTitre) || this.isNullOrEmpty(sAuteur) || this.isNullOrEmpty(sText)) {		
			request.setAttribute("message", "Un des champs du formulaire n'a pas été bien renseigné");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutArticle.jsp");	
			try {
				view.forward(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			} else if(Equal==true){		
				/*Si le titre existe déjà*/
			request.setAttribute("message", "Cet article existe déjà !");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutArticle.jsp");	
			try {
				view.forward(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	} else {
			try{
				/*Ajout de l'article avec l'id du dernier article +1*/
				Article nouvelArticle = new Article(lastId+1,sText, sAuteur, sTitre);
				Ensemble.getInstance().ajouterArticle(nouvelArticle);
				request.setAttribute("message", "Article ajouté !");			
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutArticle.jsp");	
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
		else {
			/*S'il n'y a pas d'articles dans la bdd*/
			if (this.isNullOrEmpty(sTitre) || this.isNullOrEmpty(sAuteur) || this.isNullOrEmpty(sText)) {		
				request.setAttribute("message", "Un des champs du formulaire n'a pas été bien renseigné");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutArticle.jsp");	
				try {
					view.forward(request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				} else if(Equal==true){		
				request.setAttribute("message", "Cet article existe déjà !");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutArticle.jsp");	
				try {
					view.forward(request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
				try{
					/*Id à 1 car il s'agit du premier article ajouté à la bdd*/
					Article nouvelArticle = new Article(1,sText, sAuteur, sTitre);
					Ensemble.getInstance().ajouterArticle(nouvelArticle);
					request.setAttribute("message", "Article ajouté !");			
					RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutArticle.jsp");	
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
	}
	
	private boolean isNullOrEmpty(String chaine) {
		return chaine == null || "".equals(chaine);
	}

}
