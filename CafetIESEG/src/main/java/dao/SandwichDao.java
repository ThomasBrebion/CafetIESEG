package dao;

import java.util.List;

import Entities.Sandwich;

public interface SandwichDao {
	public List<Sandwich> listerSandwichs();

	public Sandwich getSandwich(int id);

	public Sandwich ajouterSandwich(Sandwich sandwich);

	public void supprimerSandwich(int id);
	
	public void majSandwich(Sandwich sandwich);
}
