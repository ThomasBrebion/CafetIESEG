package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Plat_chaud;
import manager.Ensemble;

@WebServlet("/ajouterPlatChaud")
public class AjoutPlatChaudServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPlatChaud.jsp");
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

		int j = Ensemble.getInstance().listerPlat_chauds().size();
				
		request.setAttribute("messageErreur", "");
		
		/*On vérifie qu'il ne s'agit pas d'un nom qui existe déjà*/
		boolean Equal = false;
		for (int k=0;k<j;k++){
			if(Ensemble.getInstance().listerPlat_chauds().get(k).getNom().equals(nom))
			{
				Equal = true;
			}
		}
		
		if(j!=0){
			/*S'il y a déjà des plats dans la bdd*/
			int lastId = Ensemble.getInstance().listerPlat_chauds().size();
			
		if (this.isNullOrEmpty(nom) || prixsolo==null || prixmenu==null) {		
			request.setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPlatChaud.jsp");	
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
			request.setAttribute("messageErreur", "Ce plat chaud existe déjà !");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPlatChaud.jsp");	
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
				/*Ajout du dessert avec l'id du dernier dessert +1*/
				Plat_chaud nouvelPlat_chaud = new Plat_chaud(nom,prixsolo,prixmenu, lastId+1);
				Ensemble.getInstance().ajouterPlat_chaud(nouvelPlat_chaud);
				request.setAttribute("messageErreur", "Plat chaud ajouté !");			
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPlatChaud.jsp");	
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
			/*S'il n'y a pas de plats dans la bdd*/
			if (this.isNullOrEmpty(nom) || prixsolo==null || prixmenu==null) {		
				request.setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPlatChaud.jsp");	
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
				request.setAttribute("messageErreur", "Ce plat chaud existe déjà !");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPlatChaud.jsp");	
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
					/*Id à 1 car il s'agit du premier plat ajouté à la bdd*/
					Plat_chaud nouvelPlat_chaud = new Plat_chaud(nom,prixsolo,prixmenu,1);
					Ensemble.getInstance().ajouterPlat_chaud(nouvelPlat_chaud);
					request.setAttribute("messageErreur", "Plat chaud ajouté !");			
					RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPlatChaud.jsp");	
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
