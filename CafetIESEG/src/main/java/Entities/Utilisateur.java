package Entities;

public class Utilisateur {

    private String motDePasse;
    private String identifiant;
    
    public Utilisateur(String motDePasse,String identifiant){
		super();
		this.motDePasse=motDePasse;
		this.identifiant=identifiant;
		
	}

    public void setMotDePasse(String motDePasse) {
	this.motDePasse = motDePasse;
    }
    public String getMotDePasse() {
	return motDePasse;
    }

    public void setIdentifiant(String identifiant) {
	this.identifiant = identifiant;
    }
    public String getIdentifiant() {
	return identifiant;
    }
}