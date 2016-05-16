package servlets;

import java.io.IOException;
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

@WebServlet("/ajouter")
public class AjoutServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajout.jsp");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		/*Récuperation des paramètres*/
		String nom = request.getParameter("nom2");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date1=request.getParameter("date");
		Date date = null;

		Integer quantite = null;
		Double prix = null;

		try {
			quantite = Integer.parseInt(request.getParameter("quantite2"));
			prix = Double.parseDouble(request.getParameter("prix"));
			date = sdf.parse(date1);
		} catch (NumberFormatException e) {

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int j = Ensemble.getInstance().listerProduits().size();
				
		request.setAttribute("messageErreur0", "");
		
		/*On vérifie qu'il ne s'agit pas d'un nom qui existe déjà*/
		boolean Equal = false;
		for (int k=0;k<j;k++){
			if(Ensemble.getInstance().listerProduits().get(k).getNom().equals(nom))
			{
				Equal = true;
			}
		}
		
		if(j!=0){
			/*S'il y a déjà des produits dans la bdd*/
			int lastId = Ensemble.getInstance().listerProduits().size();
			
		if (this.isNullOrEmpty(nom) || date == null || quantite == null || prix == null) {		
			request.setAttribute("messageErreur0", "Un des champs du formulaire n'a pas été bien renseigné");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajout.jsp");	
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
				/*Si le nom existe déjà*/
			request.setAttribute("messageErreur0", "Ce produit existe déjà !");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajout.jsp");	
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
				/*Ajout du produit avec l'id du dernier produit +1*/
				Produits nouvelProduit = new Produits(lastId+1,nom, quantite, date, prix);
				Ensemble.getInstance().ajouterProduit(nouvelProduit);
				request.setAttribute("messageErreur0", "Produit ajouté !");			
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajout.jsp");	
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
			/*S'il n'y a pas de produits dans la bdd*/
			if (this.isNullOrEmpty(nom) || date == null || quantite == null || prix == null) {		
				request.setAttribute("messageErreur0", "Un des champs du formulaire n'a pas été bien renseigné");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajout.jsp");	
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
				request.setAttribute("messageErreur0", "Ce produit existe déjà !");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajout.jsp");	
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
					/*Id à 1 car il s'agit du premier produit ajouté à la bdd*/
					Produits nouvelProduit = new Produits(1,nom, quantite, date, prix);
					Ensemble.getInstance().ajouterProduit(nouvelProduit);
					request.setAttribute("messageErreur0", "Produit ajouté !");			
					RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajout.jsp");	
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