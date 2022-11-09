package fr.li3.coo.projet;

/**
 * Implementation des cartes des différents trésors du jeu.
 * @author olivier-twist
 *
 */
public class Cartes_tresor {
	private String nom;


	/**Constructeur de la classe Cartes_Tresor
	 * @param nom : le nom de la carte du tresor que le joueur a dans sa main
	 */
	public Cartes_tresor(String nom) {
		super();
		this.nom = nom;
		
	}


	/**
	 * @return le nom de la carte dans la main du joueur.
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @param nom : change le nom de la carte
	 */
	
	public void setNom(String nom) {
		this.nom = nom;
	}
}


