package Entities;

public class Plat {

	private String nom;
	private double prix;
	
	public Plat(String nom,double prix){
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
