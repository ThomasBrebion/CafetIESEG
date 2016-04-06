package dao;

import java.util.List;

import Entities.Grand_dessert;

public interface Grand_dessertDao {
	public List<Grand_dessert> listerGrand_dessert();

	public Grand_dessert getGrand_dessert(String nom);

	public Grand_dessert ajouterGrand_dessert(Grand_dessert grand_dessert);

	public void supprimerGrand_dessert(String nom);
}
