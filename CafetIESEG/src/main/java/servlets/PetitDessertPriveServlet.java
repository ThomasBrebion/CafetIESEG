package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Petit_dessert;
import manager.Ensemble;

@WebServlet("/petitdessertprive")
public class PetitDessertPriveServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		/*Listing des petit desserts et mise en attribut*/		
		List<Petit_dessert> petit_dessert = Ensemble.getInstance().listerPetit_dessert();
		request.setAttribute("listePetit_dessert", petit_dessert);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/petit-dessert_prive.jsp");
		view.forward(request, response);
	}

}
