package dao;

import java.util.List;
import Entities.Petit_dessert;

/* Interface avec toutes les méthodes utilisée pour les petits desserts */


public interface Petit_dessertDao {
	public List<Petit_dessert> listerPetit_dessert();

	public Petit_dessert getPetit_dessert(int id);

	public Petit_dessert ajouterPetit_dessert(Petit_dessert petit_dessert);

	public void supprimerPetit_dessert(int id);
	
	public void majPetit_dessert(Petit_dessert petit_dessert);
}
