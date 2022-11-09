package fr.li3.coo.projet;


/**
 * Cette classe est l'implémentation des fonctionnalités d'un trésor.
 * @author 
 *
 */
public class Tresor {

	private String nom;
	private boolean vivant;

	/**Constructeur de la classe Trésor.
	 * @param nom : le nom du trésor.
	 * @param vivant : définit s'il est vivant.
	 */
	public Tresor(String nom, boolean vivant) {
		super();
		this.nom = nom;
		this.vivant = vivant;
	}

	/**Accesseur en lecture du nom du trésor.
	 * @return le nom du trésor.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Permet de savoir si le trésor est vivant ou non.
	 * @return un booléen: vrai s'il vit, faux sinon.
	 */
	public boolean isVivant() {
		return vivant;
	}
	
	/**
	 * Surcharge de la méthode toString.
	 */
	@Override
	public String toString() {
		return "Tresor [nom=" + nom + ", vivant=" + vivant + "]";
	}
}
