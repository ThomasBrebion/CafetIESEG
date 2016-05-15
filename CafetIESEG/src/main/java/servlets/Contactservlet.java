package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Utilisateur;
import manager.Ensemble;

@WebServlet("/contact")
public class Contactservlet extends GenericServlet {

	private static final long serialVersionUID = 6880801727716084460L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		List<Utilisateur> utilisateurs = Ensemble.getInstance().listerUtilisateurs();
		request.setAttribute("listeUtilisateurs", utilisateurs);
		
		String mail = utilisateurs.get(0).getMail();
		request.getSession().setAttribute("mail", mail);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/contact.jsp");
		view.forward(request, response);
	}

}
