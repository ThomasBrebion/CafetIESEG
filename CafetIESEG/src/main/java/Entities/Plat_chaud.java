package Entities;

public class Plat_chaud {

	private String nom;
	private double prix_solo;
	private double prix_menu;
	
	public Plat_chaud(String nom,double prix_solo,double prix_menu){
		super();
		this.nom=nom;
		this.prix_solo=prix_solo;
		this.prix_menu=prix_menu;
		
	}
	
	public double getPrix_solo() {
		return prix_solo;
	}

	public void setPrix_solo(double prix_solo) {
		this.prix_solo = prix_solo;
	}

	public double getPrix_menu() {
		return prix_menu;
	}

	public void setPrix_menu(double prix_menu) {
		this.prix_menu = prix_menu;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

}
