package Entities;

public class Boissons {

	private String nom;
	private double prix;
	
	public Boissons(String nom,double prix){
		super();
		this.nom=nom;
		this.prix=prix;
		
	}
	
	
	public double getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

}
