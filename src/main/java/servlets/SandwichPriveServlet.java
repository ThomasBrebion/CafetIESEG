package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Sandwich;
import manager.Ensemble;

@WebServlet("/sandwichprive")
public class SandwichPriveServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		List<Sandwich> sandwichs = Ensemble.getInstance().listerSandwichs();
		request.setAttribute("listeSandwichs", sandwichs);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/sandwich_prive.jsp");
		view.forward(request, response);
	}

}