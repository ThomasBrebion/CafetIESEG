package Entities;

/* Propriétés d'un grand dessert et méthodes pour récupérer les propriétés */

public class Grand_dessert {

	private int id;
	private String nom;
	private double prix;
	
	public Grand_dessert(String nom,double prix,int id){
		super();
		this.nom=nom;
		this.prix=prix;
		this.setId(id);
		
	}
	
	
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}
