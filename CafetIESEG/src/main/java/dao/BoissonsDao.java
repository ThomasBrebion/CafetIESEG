package dao;

import java.util.List;

import Entities.Boissons;

public interface BoissonsDao {
	public List<Boissons> listerBoissons();

	public Boissons getBoissons(String nom);

	public Boissons ajouterBoissons(Boissons boissons);
	
	public void supprimerBoissons(String nom);
}
