package Entities;

public class Petit_dessert {

	private String nom;
	private double prix;
	
	public Petit_dessert(String nom,double prix){
		super();
		this.nom=nom;
		this.prix=prix;
		
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

}
