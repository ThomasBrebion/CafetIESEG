package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Produits;
import manager.Ensemble;

/**
 * Servlet implementation class SupprimerProduitServlet
 */
@WebServlet("/modifierProduit")
public class ModifierProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProduitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean problem = false;
		
		//Recuperation du parametre GET
		String value = request.getParameter("produitId");
		int produitId = -1;
		if (value == null || value.trim().length() == 0)
			problem = true;
		else
			value = value.trim();
		if (!problem)
		{
			try {
				produitId = Integer.parseInt(value);
			} catch (NumberFormatException | NullPointerException e) {
				problem = true;
			}
			String message = "";
			if (problem)
			{
				message = "Probleme avec le parametre GET : \"" + value + "\" / Problem with the parameter GET : \"" + value + "\"";
			}
			else
			{
				request.setAttribute("produit", Ensemble.getInstance().getProduits(produitId));
			}
			request.setAttribute("message", message);			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierProduit.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Recuperation des parametres du formulaire*/
		System.out.println(request.getParameter("id"));
		String sId = request.getParameter("id");
		String sNom = request.getParameter("nom2");
		String sDate_peremption = request.getParameter("date");
		String sQuantite = request.getParameter("quantite2");
		String sPrix = request.getParameter("prix");
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		
		try {
			/*Mise à jour*/
			Produits produit = new Produits(Integer.parseInt(sId), sNom, Integer.parseInt(sQuantite), 
					format.parse(sDate_peremption), Double.parseDouble(sPrix));
			Ensemble.getInstance().majProduit(produit);
			request.setAttribute("message", "Produit mis a jour ! / Product updated !");			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/modifierProduit.jsp");
			view.forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
