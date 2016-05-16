package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Grand_dessert;
import manager.Ensemble;

@WebServlet("/granddessert")
public class GrandDessertServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		/*Listing des grand desserts et mise en attribut*/
		List<Grand_dessert> grand_dessert = Ensemble.getInstance().listerGrand_dessert();
		request.setAttribute("listeGrand_dessert", grand_dessert);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/grand-dessert.jsp");
		view.forward(request, response);
	}

}
