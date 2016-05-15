package servlets;    

import manager.Ensemble;
import Entities.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/espace")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -8480593309360208929L;

	private Map<String, String> utilisateursAutorises;

	@Override
	public void init() throws ServletException {
		
		utilisateursAutorises = new HashMap<>();

		List<Utilisateur> utilisateurs = Ensemble.getInstance().listerUtilisateurs();
		for (int i = 0; i < utilisateurs.size(); i++) {
			utilisateursAutorises.put(utilisateurs.get(i).getMail(), utilisateurs.get(i).getMotDePasse());
		}
		System.out.println(utilisateursAutorises);

		/*
		 * try { // Créer une nouvelle connexion à la BDD Connection connection
		 * = DataSourceProvider.getDataSource().getConnection(); Statement stmt
		 * = connection.createStatement(); ResultSet rs = stmt.executeQuery(
		 * "SELECT username, password FROM agriculteur"); while (rs.next()) {
		 * utilisateursAutorises.put(rs.getString(1), rs.getString(2));
		 * System.out.println(utilisateursAutorises); } stmt.close();
		 * connection.close(); } catch (SQLException e) { e.printStackTrace(); }
		 */
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String utilisateur = (String) request.getSession().getAttribute("utilisateurConnecte");
		Integer id_utilisateur = (Integer) request.getSession().getAttribute("id_utilisateur");

		if (utilisateur == null || "".equals(utilisateur)) {

			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/espace.jsp");
			view.forward(request, response);
		}

		if (id_utilisateur == Ensemble.getInstance().listerUtilisateurs().get(0).getId()) {
			try {
				response.sendRedirect("espaceprive");
			} catch (Exception e) {
				// e.printStackTrace();
				System.err.println("Verification si administrateur en session ! Sinon, utilisateur...");
			}
		}

		else {
			try {
				response.sendRedirect("espace");
			} catch (Exception e) {
				// e.printStackTrace();
				System.err.println("Aucun utilisateur en session !");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mailRenseigne = request.getParameter("mail");
		String motDePasseRenseigne = request.getParameter("mdp");

		try {
			if (mailRenseigne.equals(Ensemble.getInstance().listerUtilisateurs().get(0).getMail()) && motDePasseRenseigne.equals(Ensemble.getInstance().listerUtilisateurs().get(0).getMotDePasse())) {

				System.out.println("Enregistrement de l'utilisateur en session...");
				request.getSession().setAttribute("utilisateurConnecte", mailRenseigne);

				// Stockage de l'id de l'utilisateur en session

				Integer id_utilisateur = (Integer) Ensemble.getInstance().listerUtilisateurs().get(0).getId();
				request.getSession().setAttribute("id_utilisateur", id_utilisateur);
				System.out.println("Id en session: " + id_utilisateur);

			} else {
				System.err.println("Erreur d'identification !!");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		this.doGet(request, response);

	}
}