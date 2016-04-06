package manager;

import java.util.List;

import Entities.Article;
import Entities.Boissons;
import Entities.Grand_dessert;
import Entities.Petit_dessert;
import Entities.Plat;
import Entities.Plat_chaud;
import Entities.Produits;
import Entities.Salades;
import Entities.Sandwich;
import Entities.Utilisateur;
import dao.ArticleDao;
import dao.BoissonsDao;
import dao.Grand_dessertDao;
import dao.Petit_dessertDao;
import dao.PlatDao;
import dao.Plat_chaudDao;
import dao.ProduitsDao;
import dao.SaladesDao;
import dao.SandwichDao;
import dao.UtilisateurDao;
import dao.impl.ArticleDaoImpl;
import dao.impl.BoissonDaoImpl;
import dao.impl.Grand_dessertDaoImpl;
import dao.impl.Petit_dessertDaoImpl;
import dao.impl.PlatDaoImpl;
import dao.impl.Plat_chaudDaoImpl;
import dao.impl.ProduitsDaoImpl;
import dao.impl.SaladeDaoImpl;
import dao.impl.SandwichDaoImpl;
import dao.impl.UtilisateurDaoImpl;

public class Ensemble {

	private static Ensemble instance;

	public static Ensemble getInstance() {
		if (instance == null) {
			instance = new Ensemble();
		}
		return instance;
	}

	private ProduitsDao produitsDao = new ProduitsDaoImpl();
	private Grand_dessertDao grand_dessertDao = new Grand_dessertDaoImpl();
	private Petit_dessertDao petit_dessertDao = new Petit_dessertDaoImpl();
	private BoissonsDao boissonDao = new BoissonDaoImpl();
	private Plat_chaudDao plat_chaudsDao = new Plat_chaudDaoImpl();
	private SaladesDao saladesDao = new SaladeDaoImpl();
	private SandwichDao sandwichsDao = new SandwichDaoImpl();
	private PlatDao platDao = new PlatDaoImpl();
	private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();
	private ArticleDao articleDao = new ArticleDaoImpl();

	private Ensemble() {
	}

	public List<Produits> listerProduits() {
		return produitsDao.listerProduits();
	}

	public Produits getProduits(Integer id) {
		return produitsDao.getProduit(id);
	}

	public Produits ajouterProduit(Produits produits) {
		return produitsDao.ajouterProduit(produits);
	}

	public void supprimerProduit(String nom) {
	}
	
	public List<Grand_dessert> listerGrand_dessert() {
		return grand_dessertDao.listerGrand_dessert();
	}

	public Grand_dessert getGrand_dessert(String nom) {
		return grand_dessertDao.getGrand_dessert(nom);
	}

	public Grand_dessert ajouterGrand_dessert(Grand_dessert grand_dessert) {
		return grand_dessertDao.ajouterGrand_dessert(grand_dessert);
	}
	
	public List<Petit_dessert> listerPetit_dessert() {
		return petit_dessertDao.listerPetit_dessert();
	}

	public Petit_dessert getPetit_dessert(String nom) {
		return petit_dessertDao.getPetit_dessert(nom);
	}

	public Petit_dessert ajouterPetit_dessert(Petit_dessert petit_dessert) {
		return petit_dessertDao.ajouterPetit_dessert(petit_dessert);
	}
	
	public List<Boissons> listerBoissons() {
		return boissonDao.listerBoissons();
	}

	public Boissons getBoissons(String nom) {
		return boissonDao.getBoissons(nom);
	}

	public Boissons ajouterBoissons(Boissons boissons) {
		return boissonDao.ajouterBoissons(boissons);
	}
	
	public List<Plat_chaud> listerPlat_chauds() {
		return plat_chaudsDao.listerPlat_chaud();
	}

	public Plat_chaud getPlat_chaud(String nom) {
		return plat_chaudsDao.getPlat_chaud(nom);
	}

	public Plat_chaud ajouterPlat_chaud(Plat_chaud plat_chaud) {
		return plat_chaudsDao.ajouterPlat_chaud(plat_chaud);
	}
	
	public List<Salades> listerSalades() {
		return saladesDao.listerSalades();
	}

	public Salades getSalades(String nom) {
		return saladesDao.getSalade(nom);
	}

	public Salades ajouterSalades(Salades salades) {
		return saladesDao.ajouterSalade(salades);
	}
	
	public List<Sandwich> listerSandwichs() {
		return sandwichsDao.listerSandwichs();
	}

	public Sandwich getSandwichs(String nom) {
		return sandwichsDao.getSandwich(nom);
	}

	public Sandwich ajouterSandwich(Sandwich sandwich) {
		return sandwichsDao.ajouterSandwich(sandwich);
	}
	
	public List<Plat> listerPlat() {
		return platDao.listerPlat();
	}

	public Plat getPlat(String nom) {
		return platDao.getPlat(nom);
	}
	
	public List<Utilisateur> listerUtilisateurs() {
		return utilisateurDao.listerUtilisateurs();
	}

	public Utilisateur getUtilisateur(String id) {
		return utilisateurDao.getUtilisateur(id);
	}

	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.ajouterUtilisateur(utilisateur);
	}
	
	public List<Article> listerArticles() {
		return articleDao.listerArticles();
	}

	public Article getArticle(Integer id) {
		return articleDao.getArticle(id);
	}

	public Article ajouterArticle(Article article) {
		return articleDao.ajouterArticle(article);
	}

	public void supprimerArticle(String titre) {
		
	}

	public void supprimerBoisson(String nom) {
		
	}

	public void supprimerPetitDessert(String titre) {
		
	}

	public void supprimerGrandDessert(String nom) {
		
	}

	public void supprimerSalade(String titre) {
		
	}

	public void supprimerSandwich(String nom) {
		
	}

	public void supprimerPlatChaud(String nom) {
		
	}

	public void mettreAjourProduit(String nom, Integer quantite) {
		// TODO Auto-generated method stub
		
	}

}
