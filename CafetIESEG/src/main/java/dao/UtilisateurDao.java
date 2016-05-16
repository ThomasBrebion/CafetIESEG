package dao;

import java.util.List;
import Entities.Utilisateur;

/* Interface avec toutes les méthodes utilisée pour les utilisateurs */

public interface UtilisateurDao {
	public List<Utilisateur> listerUtilisateurs();

	public Utilisateur getUtilisateur(int id);

	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur);
	
	public void supprimerUtilisateur(int id);
	
	public void majUtilisateur(Utilisateur utilisateur);
}
