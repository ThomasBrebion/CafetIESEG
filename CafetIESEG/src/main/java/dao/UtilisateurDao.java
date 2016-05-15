package dao;

import java.util.List;

import Entities.Utilisateur;

public interface UtilisateurDao {
	public List<Utilisateur> listerUtilisateurs();

	public Utilisateur getUtilisateur(int id);

	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur);
	
	public void supprimerUtilisateur(int id);
	
	public void majUtilisateur(Utilisateur utilisateur);
}
