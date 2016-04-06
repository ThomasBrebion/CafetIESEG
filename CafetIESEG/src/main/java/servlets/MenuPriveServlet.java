package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Boissons;
import Entities.Grand_dessert;
import Entities.Petit_dessert;
import Entities.Plat;
import Entities.Plat_chaud;
import Entities.Salades;
import Entities.Sandwich;
import manager.Ensemble;

@WebServlet("/menuprive")
public class MenuPriveServlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		List<Boissons> boissons = Ensemble.getInstance().listerBoissons();
		request.setAttribute("listeBoissons", boissons);
		
		List<Grand_dessert> grand_dessert = Ensemble.getInstance().listerGrand_dessert();
		request.setAttribute("listeGrand_dessert", grand_dessert);
		
		List<Petit_dessert> petit_dessert = Ensemble.getInstance().listerPetit_dessert();
		request.setAttribute("listePetit_dessert", petit_dessert);
		
		List<Plat_chaud> plat_chauds = Ensemble.getInstance().listerPlat_chauds();
		request.setAttribute("listePlat_chauds", plat_chauds);
		
		List<Salades> salades = Ensemble.getInstance().listerSalades();
		request.setAttribute("listeSalades", salades);
		
		List<Sandwich> sandwichs = Ensemble.getInstance().listerSandwichs();
		request.setAttribute("listeSandwichs", sandwichs);
		
		Plat plat = Ensemble.getInstance().getPlat("3 fromages");
		request.setAttribute("plat", plat);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/menu_prive.jsp");
		view.forward(request, response);
	}

}