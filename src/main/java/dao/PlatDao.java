package dao;

import java.util.List;

import Entities.Plat;

public interface PlatDao {
	public List<Plat> listerPlat();

	public Plat getPlat(String nom);
}
