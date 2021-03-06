package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Boissons;
import manager.Ensemble;

@WebServlet("/boisson")
public class BoissonServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		/*Listing des boissons et mise en attribut*/
		List<Boissons> boissons = Ensemble.getInstance().listerBoissons();
		request.setAttribute("listeBoissons", boissons);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/boisson.jsp");
		view.forward(request, response);
	}

}
