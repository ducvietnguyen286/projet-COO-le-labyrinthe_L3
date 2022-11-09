package fr.li3.coo.projet;

import java.util.Stack;

/**
 * Cette classe est l'implementation des fonctionnalités de chacun des joueurs.
 * 
 * @author Theo Gayant, Olivier Buhwenda
 */
public class Joueur {

	private int id;
	private int pos_x;
	private int pos_y;
	private int pos_init_x;
	private int pos_init_y;
	private Stack<Cartes_tresor> cartes;
	boolean terminer;

	/**
	 * Constructeur de la classe Joueur.
	 * 
	 * @param id : identifiant du joueur (1,2,3 ou 4)
	 * @param x  : position x de départ du joueur.
	 * @param y  : position x de départ du joueur.
	 */
	public Joueur(int id, int x, int y) {

		this.id = id;
		this.pos_x = x;
		this.pos_init_x = x;
		this.pos_y = y;
		this.pos_init_y = y;
		this.cartes = new Stack<Cartes_tresor>();
		this.terminer = false;
	}

	/**
	 * Accesseur en lecture de l'identifiant du joueur.
	 * 
	 * @return un nombre entier qui est l'identifiant du joueur.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Accesseur en lecture de la cordonnée x du joueur.
	 * 
	 * @return la coordonnée x du joueur, de type entier.
	 */
	public int getPos_x() {
		return pos_x;
	}

	/**
	 * Permet de remplacer la coordonnée x du joueur.
	 * 
	 * @param pos_x : le nombre qui va remplacer la coordonnée x actuelle.
	 */
	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	/**
	 * Permet d'accéder en lecture à la coordonnée y du Joueur.
	 * 
	 * @return la coordonnée y de type entier.
	 */
	public int getPos_y() {
		return pos_y;
	}

	/**
	 * Permet de changer l'ancienne coordonée y par celle donnée en parametre.
	 * 
	 * @param pos_y : la coordonnée y de remplacement de type entier.
	 */
	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	/**
	 * Permet de recuperer le nombre de cartes que le joueur a en main.
	 * 
	 * @return le nombre de cartes dans la main du jouer, c'est-à-diren un entier.
	 */
	public int getNb_cartes() {
		return this.cartes.size();
	}

	/**
	 * @return the cartes
	 */
	public Stack<Cartes_tresor> getCartes() {
		return cartes;
	}

	/**
	 * Permet de savoir quel trésor le joueur doit trouver
	 * 
	 * @return le nom du tresor que le joueur doit trouver
	 */
	public String getCarteATrouver() {
		if (this.getNb_cartes() > 0) {
			return this.cartes.peek().getNom();
		}
		return "depart";
	}

	/** Permet de trouver un trésor sur la carte donnée avec son nom
	 * @param nom_carte : le nom de la carte supposée avoir un trésor
	 * @return vrai si il a trouvé un trésor et faux sinon
	 */
	public boolean tresor_trouve(String nom_carte) {
		if (this.getNb_cartes() > 0 && this.cartes.peek().getNom() == nom_carte) {
			// System.out.println("Trouvé " + nom_carte);
			this.cartes.pop();
			return true;
		}
		if (this.getNb_cartes() == 0 && this.pos_x == this.pos_init_x && this.pos_y == this.pos_init_y) {
			// System.out.println("Tous trouvé");
			this.terminer = true;
		}
		return false;
	}
	
	/**Enlève la carte au sommet de la pile
	 * 
	 */
	public void enleveSommet() {
		this.cartes.pop();
	}

	/**Retourne la position x initiale
	 * @return pos_init_x
	 */
	public int getPos_init_x() {
		return pos_init_x;
	}

	/**Retourne la position y initiale
	 * @return pos_init_y
	 */
	public int getPos_init_y() {
		return pos_init_y;
	}

	/**Ajoute une carte au jeu
	 * @param nom_carte : le nom de la carte
	 */
	public void ajout_carte(String nom_carte) {
		this.cartes.push(new Cartes_tresor(nom_carte));
	}

	public boolean estCoordValide(int x, int y) {
		return (x >= 0 && x < 7 && y >= 0 && y < 7);
	}

	/**
	 * Vérifie d'abord si x et y ne sont pas en dehors du plateau de jeu puis
	 * affecte les nouvelles coordonnées
	 * 
	 * @param x : la coordonnée x
	 * @param y : la coordonnée y
	 * @return boolean: true si déplacement possible, false sinon
	 */
	public boolean deplacer(int x, int y) {
		if (this.estCoordValide(x, y)) {
			this.setPos_x(x);
			this.setPos_y(y);
			return true;
		}
		return false;
	}

	/**
	 * Retour à la case départ
	 * 
	 * @return boolean : true si tout s'est bien passé, false sinon.
	 */
	public boolean case_depart() {
		return deplacer(this.pos_init_x, this.pos_init_y);
	}
	
	/**
	 * Surcharge de la méthode toString
	 * @return un String.
	 */

	@Override
	public String toString() {
		return "Joueur [id=" + id + ", pos_x=" + pos_x + ", pos_y=" + pos_y + ", Pile = " + this.getNb_cartes()
				+ ", Sommet =" + this.cartes.peek().getNom() + "]";
	}

	/**Permet de savoir si le jeu est terminé ou non.
	 * @return true si c'est temriné, false sinon
	 */
	public boolean isTerminer() {
		return terminer;
	}

	/**Permet de changer la valeur de terminer.
	 * @param terminer : la valeur à changer.
	 */
	public void setTerminer(boolean terminer) {
		this.terminer = terminer;
	}

}