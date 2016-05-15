package dao;

import java.util.List;

import Entities.Salades;

public interface SaladesDao {
	public List<Salades> listerSalades();

	public Salades getSalade(int id);

	public Salades ajouterSalade(Salades salades);

	public void supprimerSalade(int id);
	
	public void majSalade(Salades salade);
}
