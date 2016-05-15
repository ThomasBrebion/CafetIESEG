package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Plat_chaud;
import manager.Ensemble;

@WebServlet("/plat")
public class PlatServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		List<Plat_chaud> plat_chauds = Ensemble.getInstance().listerPlat_chauds();
		request.setAttribute("listePlat_chauds", plat_chauds);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/plat.jsp");
		view.forward(request, response);
	}

}
