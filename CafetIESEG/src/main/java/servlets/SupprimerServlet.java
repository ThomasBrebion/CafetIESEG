package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Produits;
import manager.Ensemble;

@WebServlet("/supprimer")
public class SupprimerServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		List<Produits> produits = Ensemble.getInstance().listerProduits();
		request.setAttribute("listeProduits", produits);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/supprimer.jsp");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nom = request.getParameter("nom");
		Integer quantite = null;

		try {
			quantite = Integer.parseInt(request.getParameter("quantite"));
		} catch (NumberFormatException e) {

		}

		List<Produits> listProduits = Ensemble.getInstance().listerProduits();
		int j=0;
		
		for(int i = 0;i<listProduits.size();i++){
			if(listProduits.get(i).getNom() == nom){
				j=i;
		}
		}

		if (this.isNullOrEmpty(nom) || quantite == null ) {
			request.getSession().setAttribute("messageErreur1", "Un des champs du formulaire n'a pas été bien renseigné");
			response.sendRedirect("supprimer");
		} else if(quantite>listProduits.get(j).getQuantite()){
			request.getSession().setAttribute("messageErreur1", "La quantité rentrée est trop grande.");
			response.sendRedirect("supprimer");
		} else if(quantite==listProduits.get(j).getQuantite()){
			Ensemble.getInstance().supprimerProduit(nom);
			response.sendRedirect("modificationOK");
		}
		else if(quantite<listProduits.get(j).getQuantite()){
			Ensemble.getInstance().mettreAjourProduit(nom,quantite);
			response.sendRedirect("modificationOK");
		}
	}

	private boolean isNullOrEmpty(String chaine) {
		return chaine == null || "".equals(chaine);
	}

}
