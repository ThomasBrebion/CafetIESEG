package dao; 

import utils.MotDePasseUtils; 
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.HashMap; 
import java.util.Map; 
import Entities.Utilisateur;
import dao.impl.DataSourceProvider;

public final class ConnexionForm { 
	
	private static final String CHAMP_EMAIL = "id"; 
	private static final String CHAMP_PASS = "mdp"; 
	private String resultat; 
	private Map<String, String> erreurs = new HashMap<String, String>(); 
	
	public String getResultat() { 
		
		return resultat;
	}

public Map<String, String> getErreurs() { 
	
	return erreurs; } 

public Utilisateur connecterUtilisateur(HttpServletRequest request) throws Exception { 
	
	/* Récupération des champs du formulaire */ 
	String email = getValeurChamp(request, CHAMP_EMAIL); 
	String motDePasse = getValeurChamp(request, CHAMP_PASS); 
	Utilisateur utilisateur = new Utilisateur(null, null, 0); 
	
	/* Validation du champ email. */ 
	try {
		System.out.println(); 
		validationEmail(email); } 
	
	catch (Exception e) { 
		setErreur(CHAMP_EMAIL, e.getMessage()); 
		
	} 
	
	utilisateur.setMail(email); 
	
	/* Validation du champ mot de passe. */ 
	try { 
		validationMotDePasse(motDePasse, email); } 
	catch (Exception e) { 
		setErreur(CHAMP_PASS, e.getMessage());
	}

	utilisateur.setMotDePasse(motDePasse); 

/* Initialisation du résultat global de la validation. */ 
if (erreurs.isEmpty()) { resultat = "Succès de la connexion.";} 
else { resultat = "Échec de la connexion."; } 
return utilisateur; } 

/** * Valide l'adresse email saisie. */ 
private void validationEmail(String email) throws Exception {
		try { 
			Connection connection = DataSourceProvider.getDataSource().getConnection(); 
			Statement stmt = connection.createStatement(); 
			ResultSet results = stmt.executeQuery("SELECT COUNT(*) AS total FROM utilisateurs WHERE mail = '" + email + "'"); 
			results.next(); 
			
			if (results.getInt(1) == 0) { 
				throw new Exception("Le compte n'existe pas"); } 
			stmt.close(); 
			connection.close(); } 
		catch (SQLException e) { 
			e.printStackTrace(); 
			throw new Exception("Echec de connexion à la base de données."); 
			}
	} 

/** * Valide le mot de passe saisi. */ 
private void validationMotDePasse(String motDePasse, String email) throws Exception { 
	if (motDePasse != null) {
			try { 
				Connection connection = DataSourceProvider.getDataSource().getConnection(); 
				Statement stmt = connection.createStatement(); 
				ResultSet results = stmt.executeQuery("SELECT mdp FROM utilisateurs WHERE mail = '" + email + "'"); 
				results.next(); 
				String utilisateur_mdp_cripte = results.getString("mdp"); 
				System.out.println("motdepassUtilisateur : " + utilisateur_mdp_cripte); 
				System.out.println("validermotdepasse : " + MotDePasseUtils.validerMotDePasse(motDePasse, utilisateur_mdp_cripte));
				
				if (!MotDePasseUtils.validerMotDePasse(motDePasse, utilisateur_mdp_cripte)) { 
					throw new Exception("Mot de passe incorect."); } 
				stmt.close(); connection.close(); } 
			catch (SQLException e) { 
				e.printStackTrace(); 
				throw new Exception("Echec de connexion à la base de données."); 
				} 
		} else { throw new Exception("Merci de saisir votre mot de passe."); } 
	} 

/* * Ajoute un message correspondant au champ spécifié à la map des erreurs. */ 
private void setErreur(String champ, String message) { 
	erreurs.put(champ, message); } 

/* * Méthode utilitaire qui retourne null si un champ est vide, et son contenu * sinon. */ 
private static String getValeurChamp(HttpServletRequest request, String nomChamp) { 
	String valeur = request.getParameter(nomChamp); 
	if (valeur == null || valeur.trim().length() == 0) { return null; } 
	else { return valeur; } 
	} 
}