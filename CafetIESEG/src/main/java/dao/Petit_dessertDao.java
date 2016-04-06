package dao;

import java.util.List;

import Entities.Petit_dessert;

public interface Petit_dessertDao {
	public List<Petit_dessert> listerPetit_dessert();

	public Petit_dessert getPetit_dessert(String nom);

	public Petit_dessert ajouterPetit_dessert(Petit_dessert petit_dessert);

	public void supprimerPetit_dessert(String nom);
}
