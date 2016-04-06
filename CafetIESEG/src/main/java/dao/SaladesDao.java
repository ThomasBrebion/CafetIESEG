package dao;

import java.util.List;

import Entities.Salades;

public interface SaladesDao {
	public List<Salades> listerSalades();

	public Salades getSalade(String nom);

	public Salades ajouterSalade(Salades salades);

	public void supprimerSalade(String nom);
}
