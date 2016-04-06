package dao;

import java.util.List;

import Entities.Sandwich;

public interface SandwichDao {
	public List<Sandwich> listerSandwichs();

	public Sandwich getSandwich(String nom);

	public Sandwich ajouterSandwich(Sandwich sandwich);

	public void supprimerSandwich(String nom);
}
