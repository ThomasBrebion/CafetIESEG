package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Salades;
import manager.Ensemble;

@WebServlet("/ajouterSalade")
public class AjoutSaladeServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutSalade.jsp");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*Récuperation des paramètres*/
		String nom = request.getParameter("nom");
		Double prixsolo = null ;
		Double prixmenu = null ;
		
		try {
			prixsolo = Double.parseDouble(request.getParameter("prix_solo"));
			prixmenu = Double.parseDouble(request.getParameter("prix_menu"));
		} catch (NumberFormatException e) {

		}

		int j = Ensemble.getInstance().listerSalades().size();
				
		request.setAttribute("messageErreur", "");
		
		/*On vérifie qu'il ne s'agit pas d'un nom qui existe déjà*/
		boolean Equal = false;
		for (int k=0;k<j;k++){
			if(Ensemble.getInstance().listerSalades().get(k).getNom().equals(nom))
			{
				Equal = true;
			}
		}
		
		if(j!=0){
			/*S'il y a déjà des salades dans la bdd*/
			int lastId = Ensemble.getInstance().listerSalades().size();
			
		if (this.isNullOrEmpty(nom) || prixsolo==null || prixmenu==null) {		
			request.setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutSalade.jsp");	
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
			request.setAttribute("messageErreur", "Cette salade existe déjà !");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutSalade.jsp");	
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
				/*Ajout de la salade avec l'id de la derniere salade +1*/
				Salades nouvelSalade = new Salades(nom,prixsolo,prixmenu, lastId+1);
				Ensemble.getInstance().ajouterSalades(nouvelSalade);
				request.setAttribute("messageErreur", "Salade ajoutée !");			
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutSalade.jsp");	
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
			/*S'il n'y a pas de salades dans la bdd*/
			if (this.isNullOrEmpty(nom) || prixsolo==null || prixmenu==null) {		
				request.setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutSalade.jsp");	
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
				request.setAttribute("messageErreur", "Cette salade existe déjà !");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutSalade.jsp");	
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
					/*Id à 1 car il s'agit de la premiere salade ajoutée à la bdd*/
					Salades nouvelSalade = new Salades(nom,prixsolo,prixmenu,1);
					Ensemble.getInstance().ajouterSalades(nouvelSalade);
					request.setAttribute("messageErreur", "Salade ajoutée !");			
					RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutSalade.jsp");	
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
