package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Boissons;
import manager.Ensemble;

@WebServlet("/ajouterBoisson")
public class AjoutBoissonServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutBoisson.jsp");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*Récuperation des paramètres*/
		String nom = request.getParameter("nom");
		Double prix = null ;
		
		try {
			prix = Double.parseDouble(request.getParameter("prix"));
		} catch (NumberFormatException e) {

		}

		int j = Ensemble.getInstance().listerBoissons().size();
				
		request.setAttribute("messageErreur", "");
		
		/*On vérifie qu'il ne s'agit pas d'un nom qui existe déjà*/
		boolean Equal = false;
		for (int k=0;k<j;k++){
			if(Ensemble.getInstance().listerBoissons().get(k).getNom().equals(nom))
			{
				Equal = true;
			}
		}
		
		if(j!=0){
			/*S'il y a déjà des boissons dans la bdd*/
			int lastId = Ensemble.getInstance().listerBoissons().size();
			/*Champs non nuls*/
		if (this.isNullOrEmpty(nom) || prix==null) {		
			request.setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutBoisson.jsp");	
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
			request.setAttribute("messageErreur", "Cette boisson existe déjà !");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutBoisson.jsp");	
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
				/*Ajout de la boisson avec l'id de la derniere boisson +1*/
				Boissons nouvelBoisson = new Boissons(nom,prix, lastId+1);
				Ensemble.getInstance().ajouterBoissons(nouvelBoisson);
				request.setAttribute("messageErreur", "Boisson ajoutée !");			
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutBoisson.jsp");	
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
			/*S'il n'y a pas de boissons dans la bdd*/
			if (this.isNullOrEmpty(nom) || prix==null) {		
				request.setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutBoisson.jsp");	
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
				request.setAttribute("messageErreur", "Cette boisson existe déjà !");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutBoisson.jsp");	
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
					/*Id à 1 car il s'agit de la premiere boisson ajoutée à la bdd*/
					Boissons nouvelBoisson = new Boissons(nom,prix,1);
					Ensemble.getInstance().ajouterBoissons(nouvelBoisson);
					request.setAttribute("messageErreur", "Boisson ajoutée !");			
					RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutBoisson.jsp");	
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
