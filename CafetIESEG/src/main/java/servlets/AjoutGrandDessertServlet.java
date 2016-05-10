package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Grand_dessert;
import manager.Ensemble;

@WebServlet("/ajouterGrandDessert")
public class AjoutGrandDessertServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutGrandDessert.jsp");
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

		int j = Ensemble.getInstance().listerGrand_dessert().size();

		boolean bool = true;
		for(int k=0;k<j;k++){
			if(nom.equals(Ensemble.getInstance().listerGrand_dessert().get(k).getNom())){
				bool = false;
			}
		}
		
		if(j!=0){
			int lastId = Ensemble.getInstance().listerGrand_dessert().size();

			
			if(bool==false){
				request.getSession().setAttribute("messageErreur", "Ce grand dessert existe déjà");
				response.sendRedirect("ajouterGrandDessert");
			} else if (this.isNullOrEmpty(nom) || prix == null) {
				request.getSession().setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");
				response.sendRedirect("ajouterGrandDessert");
			} else {
				Grand_dessert nouveauGrand_dessert = new Grand_dessert(nom, prix,lastId+1);
				Ensemble.getInstance().ajouterGrand_dessert(nouveauGrand_dessert);
				response.sendRedirect("modificationOK");
			}
				}
			else{
				if (this.isNullOrEmpty(nom) || prix == null) {
					request.getSession().setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");
					response.sendRedirect("ajouterGrandDessert");
				} else {
					Grand_dessert nouveauGrand_dessert = new Grand_dessert(nom, prix,1);
					Ensemble.getInstance().ajouterGrand_dessert(nouveauGrand_dessert);
					response.sendRedirect("modificationOK");
				}
				
			}
		
		
	}



	private boolean isNullOrEmpty(String chaine) {
		return chaine == null || "".equals(chaine);
	}

}
