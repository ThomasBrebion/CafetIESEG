package dao;

import java.util.List;

import Entities.Utilisateur;

public interface UtilisateurDao {
	public List<Utilisateur> listerUtilisateurs();

	public Utilisateur getUtilisateur(String nom);

	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur);
}
