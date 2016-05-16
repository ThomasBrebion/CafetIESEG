package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Ensemble;

import java.io.IOException;

@WebFilter({"/accueilprive",
        "/ajouterBoisson",
        "/ajouterGrandDessert",
        "/ajouterPetitDessert",
        "/ajouterSalade",
        "/ajouterSandwich",
        "/ajouterPlat",
        "/ajouterArticle",
        "/ajouterUtilisateur",
        "/ajouterProduit",
        "/supprimerPlat",
        "/supprimerSandwich",
        "/supprimerSalade",
        "/supprimerPetitDessert",
        "/supprimerGrandDessert",
        "/supprimerBoisson",
        "/supprimerArticle",
        "/supprimerUtilisateur",
        "/supprimerProduit",
        "/modifierBoisson",
        "/modifierSalade",
        "/modifierSandwich",
        "/modifierGrandDessert",
        "/modifierPetitDessert",
        "/modifierPlat",
        "/modifierArticle",
        "/modifierUtilisateur",
        "/modifierProduit",
        "/espaceprive",
        "/accueilprive",
        "/menuprive",
        "/contactprive",
        "/carteprive",
        "/boissonprive",
        "/granddessertprive",
        "/petitdessertprive",
        "/platprive",
        "/sandwichprive",
        "/saladeprive"
        })
public class RestrictionFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) req;

		//Récuperation des attributs
		String identifiant = (String) httpRequest.getSession().getAttribute("utilisateurConnecte");
		Integer id_utilisateur = (Integer) httpRequest.getSession().getAttribute("id_utilisateur");

		if (identifiant == null || "".equals(identifiant)) {
			//Non connecté
			System.err.println("Il faut être connecté pour accéder à cette page !\n");

			HttpServletResponse httpResponse = (HttpServletResponse) res;
			httpResponse.sendRedirect("espace");
			return;
		}
		
		if (id_utilisateur != Ensemble.getInstance().listerUtilisateurs().get(0).getId()) {
			//Non connecté
			System.err.println("Seuls les administrateurs peuvent accéder à cette page !\n");

			HttpServletResponse httpResponse = (HttpServletResponse) res;
			httpResponse.sendRedirect("espace");
			return;
		}
		
		//Connecté, renvoie vers la page recherchée
		chain.doFilter(req, res);

	}

	@Override
	public void destroy() {
	}

}