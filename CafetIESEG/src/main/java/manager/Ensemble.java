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
	
	public void majProduit(Produits produit) {
		produitsDao.majProduit(produit);
	}
	
	public void supprimerProduit(int id) {
		produitsDao.supprimerProduit(id);
	}
	
	
	
	
	
	public List<Grand_dessert> listerGrand_dessert() {
		return grand_dessertDao.listerGrand_dessert();
	}

	public Grand_dessert getGrand_dessert(int id) {
		return grand_dessertDao.getGrand_dessert(id);
	}

	public Grand_dessert ajouterGrand_dessert(Grand_dessert grand_dessert) {
		return grand_dessertDao.ajouterGrand_dessert(grand_dessert);
	}
	
	public void supprimerGrand_dessert(int id) {
		grand_dessertDao.supprimerGrand_dessert(id);
	}
	
	public void majGrand_dessert(Grand_dessert grand_dessert) {
		grand_dessertDao.majGrand_dessert(grand_dessert);
	}
	
	
	
	
	
	
	public Petit_dessert getPetit_dessert(int id) {
		return petit_dessertDao.getPetit_dessert(id);
	}

	public Petit_dessert ajouterPetit_dessert(Petit_dessert petit_dessert) {
		return petit_dessertDao.ajouterPetit_dessert(petit_dessert);
	}
	
	public List<Petit_dessert> listerPetit_dessert() {
		return petit_dessertDao.listerPetit_dessert();
	}
	
	public void supprimerPetit_dessert(int id) {
		petit_dessertDao.supprimerPetit_dessert(id);
	}
	
	public void majPetit_dessert(Petit_dessert petit_dessert) {
		petit_dessertDao.majPetit_dessert(petit_dessert);
	}
	
	
	
	
	
	public List<Boissons> listerBoissons() {
		return boissonDao.listerBoissons();
	}

	public Boissons getBoissons(int id) {
		return boissonDao.getBoissons(id);
	}

	public Boissons ajouterBoissons(Boissons boissons) {
		return boissonDao.ajouterBoissons(boissons);
	}

	public void supprimerBoisson(int id) {
		boissonDao.supprimerBoissons(id);		
	}
	
	public void majBoissons(Boissons boissons) {
		boissonDao.majBoissons(boissons);
	}
	
	
	
	
	
	public List<Plat_chaud> listerPlat_chauds() {
		return plat_chaudsDao.listerPlat_chaud();
	}

	public Plat_chaud getPlat_chaud(int id) {
		return plat_chaudsDao.getPlat_chaud(id);
	}

	public Plat_chaud ajouterPlat_chaud(Plat_chaud plat_chaud) {
		return plat_chaudsDao.ajouterPlat_chaud(plat_chaud);
	}
	
	public void supprimerPlat_chaud(int id) {
		plat_chaudsDao.supprimerPlat_chaud(id);
	}
	
	public void majPlat_chaud(Plat_chaud plat_chaud) {
		plat_chaudsDao.majPlat_chaud(plat_chaud);
	}
	
	
	
	
	
	public List<Salades> listerSalades() {
		return saladesDao.listerSalades();
	}

	public Salades getSalade(int id) {
		return saladesDao.getSalade(id);
	}

	public Salades ajouterSalades(Salades salades) {
		return saladesDao.ajouterSalade(salades);
	}
	
	public void supprimerSalade(int id) {
		saladesDao.supprimerSalade(id);
	}
	
	public void majSalade(Salades salade) {
		saladesDao.majSalade(salade);
	}
	
	
	
	
	
	public List<Sandwich> listerSandwichs() {
		return sandwichsDao.listerSandwichs();
	}

	public Sandwich getSandwichs(int id) {
		return sandwichsDao.getSandwich(id);
	}

	public Sandwich ajouterSandwich(Sandwich sandwich) {
		return sandwichsDao.ajouterSandwich(sandwich);
	}
	
	public void supprimerSandwich(int id) {
		sandwichsDao.supprimerSandwich(id);
		
	}
	
	public void majSandwich(Sandwich sandwich) {
		sandwichsDao.majSandwich(sandwich);
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

	public Utilisateur getUtilisateur(int id) {
		return utilisateurDao.getUtilisateur(id);
	}

	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.ajouterUtilisateur(utilisateur);
	}

	public void majUtilisateur(Utilisateur utilisateur) {
		utilisateurDao.majUtilisateur(utilisateur);
	}
	
	public void supprimerUtilisateur(int id) {
		utilisateurDao.supprimerUtilisateur(id);
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
	
	public void supprimerArticle(int id) {
		articleDao.supprimerArticle(id);
	}

	public void majArticle(Article article) {
		articleDao.majArticle(article);
	}

}