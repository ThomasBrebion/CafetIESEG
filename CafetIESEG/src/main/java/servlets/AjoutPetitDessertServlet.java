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

		int j = Ensemble.getInstance().listerPetit_dessert().size();

		boolean bool = true;
		for(int k=0;k<j;k++){
			if(nom.equals(Ensemble.getInstance().listerPetit_dessert().get(k).getNom())){
				bool = false;
			}
		}
		
		if(j!=0){
			int lastId = Ensemble.getInstance().listerPetit_dessert().size();

			
			if(bool==false){
				request.getSession().setAttribute("messageErreur", "Ce petit dessert existe déjà");
				response.sendRedirect("ajouterPetitDessert");
			} else if (this.isNullOrEmpty(nom) || prix == null) {
				request.getSession().setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");
				response.sendRedirect("ajouterPetitDessert");
			} else {
				Petit_dessert nouveauPetit_dessert = new Petit_dessert(nom, prix,lastId+1);
				Ensemble.getInstance().ajouterPetit_dessert(nouveauPetit_dessert);
				response.sendRedirect("modificationOK");
			}
				}
			else{
				if (this.isNullOrEmpty(nom) || prix == null) {
					request.getSession().setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");
					response.sendRedirect("ajouterPetitDessert");
				} else {
					Petit_dessert nouveauPetit_dessert = new Petit_dessert(nom, prix,1);
					Ensemble.getInstance().ajouterPetit_dessert(nouveauPetit_dessert);
					response.sendRedirect("modificationOK");
				}
				
			}
		
		
	}



	private boolean isNullOrEmpty(String chaine) {
		return chaine == null || "".equals(chaine);
	}

}
