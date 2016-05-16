package dao;

import java.util.List;
import Entities.Plat_chaud;

/* Interface avec toutes les méthodes utilisée pour les plats chauds */


public interface Plat_chaudDao {
	public List<Plat_chaud> listerPlat_chaud();

	public Plat_chaud getPlat_chaud(int id);

	public Plat_chaud ajouterPlat_chaud(Plat_chaud plat_chaud);

	public void supprimerPlat_chaud(int id);
	
	public void majPlat_chaud(Plat_chaud plat_chaud);
}
