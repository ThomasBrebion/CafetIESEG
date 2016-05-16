package dao;

import java.util.List;
import Entities.Grand_dessert;

/* Interface avec toutes les méthodes utilisée pour les grands desserts */


public interface Grand_dessertDao {
	public List<Grand_dessert> listerGrand_dessert();

	public Grand_dessert getGrand_dessert(int id);

	public Grand_dessert ajouterGrand_dessert(Grand_dessert grand_dessert);

	public void supprimerGrand_dessert(int id);
	
	public void majGrand_dessert(Grand_dessert grand_dessert);
}
