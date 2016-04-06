package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Produits;
import manager.Ensemble;

@WebServlet("/ajout")
public class AjoutServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajout.jsp");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nom = request.getParameter("nom2");
		Date date = null;
		try {
			date = dateFormat.parse(request.getParameter("date"));
		} catch (ParseException e) {
		}
		Integer quantite = null;
		Double prix = null;

		try {
			quantite = Integer.parseInt(request.getParameter("quantite2"));
			prix = Double.parseDouble(request.getParameter("prix"));
		} catch (NumberFormatException e) {

		}
		
		int j = Ensemble.getInstance().listerProduits().size();
		if(j!=0){
		int lastId = Ensemble.getInstance().listerProduits().get(j-1).getId();

		if (this.isNullOrEmpty(nom) || date == null || quantite == null || prix == null) {
			request.getSession().setAttribute("messageErreur0", "Un des champs du formulaire n'a pas été bien renseigné");
			response.sendRedirect("ajout");
		} else {
			Produits nouveauProduit = new Produits(lastId+1,nom, quantite, date, prix);
			Ensemble.getInstance().ajouterProduit(nouveauProduit);
			response.sendRedirect("modificationOK");
			
		}}
		else{
			if (this.isNullOrEmpty(nom) || date == null || quantite == null || prix == null) {
				request.getSession().setAttribute("messageErreur0", "Un des champs du formulaire n'a pas été bien renseigné");
				response.sendRedirect("ajout");
			} else {
				Produits nouveauProduit = new Produits(1,nom, quantite, date, prix);
				Ensemble.getInstance().ajouterProduit(nouveauProduit);
				response.sendRedirect("modificationOK");
			}
			
		}
		
		
	}



	private boolean isNullOrEmpty(String chaine) {
		return chaine == null || "".equals(chaine);
	}

}
