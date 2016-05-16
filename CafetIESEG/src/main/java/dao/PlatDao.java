package dao;

import java.util.List;
import Entities.Plat;

/* Interface avec toutes les méthodes utilisée pour les plats */

public interface PlatDao {
	public List<Plat> listerPlat();

	public Plat getPlat(String nom);
}
