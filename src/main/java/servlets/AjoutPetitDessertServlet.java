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

		String nom = request.getParameter("nom");
		Double prix = null ;
		
		try {
			prix = Double.parseDouble(request.getParameter("prix"));
		} catch (NumberFormatException e) {

		}

		int j = Ensemble.getInstance().listerPetit_dessert().size();
				
		request.setAttribute("messageErreur", "");
		boolean Equal = false;
		for (int k=0;k<j;k++){
			if(Ensemble.getInstance().listerPetit_dessert().get(k).getNom().equals(nom))
			{
				Equal = true;
			}
		}
		
		if(j!=0){
			int lastId = Ensemble.getInstance().listerPetit_dessert().size();
			
		if (this.isNullOrEmpty(nom) || prix==null) {		
			request.setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPetitDessert.jsp");	
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
			request.setAttribute("messageErreur", "Ce petit dessert existe déjà !");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPetitDessert.jsp");	
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
				Petit_dessert nouvelPetit_dessert = new Petit_dessert(nom,prix, lastId+1);
				Ensemble.getInstance().ajouterPetit_dessert(nouvelPetit_dessert);
				request.setAttribute("messageErreur", "Petit dessert ajouté !");			
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPetitDessert.jsp");	
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
			if (this.isNullOrEmpty(nom) || prix==null) {		
				request.setAttribute("messageErreur", "Un des champs du formulaire n'a pas été bien renseigné");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPetitDessert.jsp");	
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
				request.setAttribute("messageErreur", "Ce petit dessert existe déjà !");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPetitDessert.jsp");	
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
					Petit_dessert nouvelPetit_dessert = new Petit_dessert(nom,prix,1);
					Ensemble.getInstance().ajouterPetit_dessert(nouvelPetit_dessert);
					request.setAttribute("messageErreur", "Petit dessert ajouté !");			
					RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutPetitDessert.jsp");	
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
