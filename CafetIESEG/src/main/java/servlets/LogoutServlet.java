
package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deconnexion")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 790144859160282054L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*Déconnexion retire l'attribut administrateur*/
		request.getSession().removeAttribute("utilisateurConnecte");

		response.sendRedirect("espace");
	}
}