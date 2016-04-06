package dao;

import java.util.List;

import Entities.Produits;

public interface ProduitsDao {
	public List<Produits> listerProduits();

	public Produits getProduit(Integer id);

	public Produits ajouterProduit(Produits produits);
	
	public void supprimerProduit(String nom);

	public void mettreAjourQuantiteProduit(String nom, Integer quantite);

	public void mettreAjourPrixProduit(String nom, Double prix);
}
