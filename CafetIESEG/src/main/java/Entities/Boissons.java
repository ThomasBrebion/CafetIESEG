package Entities;

/* Propriétés d'une boisson et méthodes pour récupérer les propriétés */

public class Boissons {

    private int id;
    private String nom;
	private double prix;
	
	public Boissons(String nom,double prix,int id){
		super();
		this.nom=nom;
		this.prix=prix;
		this.setId(id);
		
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}
