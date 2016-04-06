package Entities;

import java.util.Date;

public class Produits {
	
	private int id;
	private String nom;
	private int quantite;
	private Date date;
	private double prix;
	
	public Produits(int id,String nom,int quantite,Date date,double prix){
		super();
		this.id=id;
		this.nom=nom;
		this.quantite=quantite;
		this.date=date;
		this.prix=prix;
		
	}
	
	
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
