package Entities;

public class Article {

    private Integer id;
    private String text;
    private String auteur;
    private String titre;
    
    public Article(Integer id,String text,String auteur,String titre){
		super();
		this.id=id;
		this.text=text;
		this.auteur=auteur;
		this.titre=titre;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
}