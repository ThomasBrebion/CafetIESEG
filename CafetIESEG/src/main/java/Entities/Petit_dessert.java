package Entities;

public class Petit_dessert {

	private String nom;
	private double prix;
	private int id;
	
	public Petit_dessert(String nom,double prix,int id){
		super();
		this.nom=nom;
		this.prix=prix;
		this.id=id;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
