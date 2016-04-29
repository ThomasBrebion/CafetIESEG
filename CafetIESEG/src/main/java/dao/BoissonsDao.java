package dao;

import java.util.List;

import Entities.Boissons;

public interface BoissonsDao {
	public List<Boissons> listerBoissons();

	public Boissons getBoissons(int id);

	public Boissons ajouterBoissons(Boissons boissons);
	
	public void supprimerBoissons(int id);
	
	public void majBoissons(Boissons boissons);
}
