package servlets;

import java.util.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Ensemble;
import Entities.Produits;

@WebServlet("/espaceprive")
public class EspacePriveServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		List<Produits> produits = Ensemble.getInstance().listerProduits();
		request.setAttribute("listeProduits", produits);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/espaceprive.jsp");
		view.forward(request, response);
	}
	
	


}
