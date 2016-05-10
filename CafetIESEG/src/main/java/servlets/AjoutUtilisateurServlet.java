package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Utilisateur;
import manager.Ensemble;

/**
 * Servlet implementation class SupprimerUtilisateurServlet
 */
@WebServlet("/ajoutUtilisateur")
public class AjoutUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutUtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);			
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutUtilisateur.jsp");
		try {
			view.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sMail = request.getParameter("mail");
		String sMdp = request.getParameter("mdp");
		
		int j = Ensemble.getInstance().listerUtilisateurs().size();
				
		request.setAttribute("message", "");
		boolean Equal = false;
		for (int k=0;k<j;k++){
			if(Ensemble.getInstance().listerUtilisateurs().get(k).getMail().equals(sMail))
			{
				Equal = true;
			}
		}
		
		if(j!=0){
			int lastId = Ensemble.getInstance().listerUtilisateurs().get(j-1).getId();
			
		if (this.isNullOrEmpty(sMail) || this.isNullOrEmpty(sMdp)) {		
			request.setAttribute("message", "Un des champs du formulaire n'a pas été bien renseigné");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutUtilisateur.jsp");	
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
			request.setAttribute("message", "Cet utilisateur existe déjà !");		
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutUtilisateur.jsp");	
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
				Utilisateur nouvelUtilisateur = new Utilisateur(sMail,sMdp, lastId+1);
				Ensemble.getInstance().ajouterUtilisateur(nouvelUtilisateur);
				request.setAttribute("message", "Utilisateur ajouté !");			
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutUtilisateur.jsp");	
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
			if (this.isNullOrEmpty(sMail) || this.isNullOrEmpty(sMdp)) {		
				request.setAttribute("message", "Un des champs du formulaire n'a pas été bien renseigné");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutUtilisateur.jsp");	
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
				request.setAttribute("message", "Cet utilisateur existe déjà !");		
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutUtilisateur.jsp");	
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
					Utilisateur nouvelUtilisateur = new Utilisateur(sMail,sMdp,1);
					Ensemble.getInstance().ajouterUtilisateur(nouvelUtilisateur);
					request.setAttribute("message", "Utilisateur ajouté !");			
					RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ajoutUtilisateur.jsp");	
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