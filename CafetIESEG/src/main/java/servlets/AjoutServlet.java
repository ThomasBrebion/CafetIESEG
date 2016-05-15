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
		int j = Ensemble.getInstance().listerPlat_chauds().size();
				
		request.setAttribute("messageErreur0", "");
		boolean Equal = false;
		for (int k=0;k<j;k++){
			if(Ensemble.getInstance().listerPlat_chauds().get(k).getNom().equals(nom))
			{
				Equal = true;
			}
		}
		
		if(j!=0){
			int lastId = Ensemble.getInstance().listerPlat_chauds().size();
			
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