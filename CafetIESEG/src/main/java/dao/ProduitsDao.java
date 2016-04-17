package dao;

import java.util.List;

import Entities.Produits;

public interface ProduitsDao {
	public List<Produits> listerProduits();

	public Produits getProduit(Integer id);

	public Produits ajouterProduit(Produits produits);
	
	public void supprimerProduit(int id);
	
	public void majProduit(Produits produit);
}
