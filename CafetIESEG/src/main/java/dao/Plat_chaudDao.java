package dao;

import java.util.List;

import Entities.Plat_chaud;

public interface Plat_chaudDao {
	public List<Plat_chaud> listerPlat_chaud();

	public Plat_chaud getPlat_chaud(String nom);

	public Plat_chaud ajouterPlat_chaud(Plat_chaud plat_chaud);

	public void supprimerPlat_chaud(String nom);
}
