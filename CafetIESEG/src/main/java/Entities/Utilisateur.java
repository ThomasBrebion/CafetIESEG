package Entities;

public class Utilisateur {

    private String mdp;
    private String mail;
    private int id;
    
    public Utilisateur(String mdp,String mail, int id){
		super();
		this.mdp=mdp;
		this.mail=mail;
		this.id=id;
		
	}

    public void setMotDePasse(String mdp) {
	this.mdp = mdp;
    }
    public String getMotDePasse() {
	return mdp;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}