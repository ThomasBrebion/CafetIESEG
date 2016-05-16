package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Salades;
import manager.Ensemble;

@WebServlet("/salade")
public class SaladeServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		/*Listing des salades et mise en attribut*/
		List<Salades> salades = Ensemble.getInstance().listerSalades();
		request.setAttribute("listeSalades", salades);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/salade.jsp");
		view.forward(request, response);
	}

}
