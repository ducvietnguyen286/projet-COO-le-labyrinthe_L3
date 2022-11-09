package fr.li3.coo.projet;

import java.util.Random;

/**
 * Classe permettant une implementation des Cartes du Labyrinthe posees sur le
 * plateau
 * 
 * @author Theo Gayant
 */
public class Cartes_laby {

	private int pos_x;
	private int pos_y;
	private Forme forme;
	private Sens sens;
	private boolean fixe;
	private Tresor tresor;

	// Indique les chemin ouvert 0 = En haut / 1 = Droite / 2 = Bas / 3 = Gauche
	private boolean ouvert[] = new boolean[4];

	/**
	 * @param pos_x  : la position x a inserer
	 * @param pos_y  : la position y a inserer
	 * @param forme  : la forme a configurer (Cf Forme.java)
	 * @param sens   : le sens de la forme (un entier entre 0 et 3)
	 * @param fixe   : booleen qui dit si la carte est fixee ou non sur le plateau
	 * @param tresor : decrit le tresor affiche sur la carte. Prend la valeur null
	 *               si la carte ne possede pas de tresor.
	 */
	public Cartes_laby(int pos_x, int pos_y, Forme forme, Sens sens, boolean fixe, Tresor tresor) {
		super();
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.forme = forme;
		this.sens = sens;
		this.fixe = fixe;
		this.tresor = tresor;
	}

	
	/**
	 * Constructeur de la classe Cartes_laby
	 * @param forme : décrit la forme que la carte doit prendre
	 */
	public Cartes_laby(Forme forme) {
		this(0, 0, forme, null, false, null);
	}

	/**
	 * Permet d'acceder en lecture a la position x de la carte du labyrinthe dans la
	 * matrice plateau
	 * 
	 * @return un entier qui correspond au x de la position definie
	 */
	public int getPos_x() {
		return pos_x;
	}

	/**
	 * Permet d'acceder en ecriture a la position y de la carte du labyrinthe dans
	 * la matrice plateau
	 * 
	 * @param pos_x : la nouvelle coordonnee x a changer
	 */
	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	/**
	 * Permet d'acceder en lecture a la position y de la carte du labyrinthe dans la
	 * matrice plateau
	 * 
	 * @return un entier qui correspond au y de la position definie
	 */
	public int getPos_y() {
		return pos_y;
	}

	/**
	 * Permet d'acceder en ecriture a la position y de la carte du labyrinthe dans
	 * la matrice plateau
	 * 
	 * @param pos_y : la nouvelle coordonnee y a changer
	 */
	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	/**
	 * Permet d'acceder en lecture a la forme de la carte du labyrinthe.
	 * 
	 * @return la forme definie, qui est de type Forme qui elle meme est une enum.
	 */
	public Forme getForme() {
		return forme;
	}

	/**
	 * Permet d'acceder en lecture au Sens de la carte du labyrinthe.
	 * 
	 * @return le sens definie, qui est de type Sens qui lui-meme est une enum.
	 */
	public Sens getSens() {
		return sens;
	}

	/**
	 * Permet de changer la sens de la carte en main. se fait uniquement avec les
	 * cartes mobiles.
	 * 
	 * @param sens: le nouveau sens a etablir.
	 */
	public void setSens(Sens sens) {
		if (!this.isFixe()) {
			this.sens = sens;
			this.setOuvert();
		}
	}

	/**
	 * Permet de changer le sens d'une carte comme ci on l'a tourner vers la droite
	 */
	public void tourneSensDroite() {
		if (this.sens == Sens.HAUT)
			setSens(Sens.DROITE);
		else if (this.sens == Sens.DROITE)
			setSens(Sens.BAS);
		else if (this.sens == Sens.BAS)
			setSens(Sens.GAUCHE);
		else if (this.sens == Sens.GAUCHE)
			setSens(Sens.HAUT);
	}

	/**
	 * Permet de savoir si la position est fixe ou non.
	 * 
	 * @return un booleen: Vrai si elle est fixe, Faux sinon.
	 */
	public boolean isFixe() {
		return fixe;
	}

	/**
	 * Permet de recuperer le tresor sur une carte du labyrinthe donnee.
	 * 
	 * @return un objet de type tresor qui est le tresor sur la carte. Retourne null
	 *         si il n'y a pas de tresor.
	 */
	public Tresor getTresor() {
		return tresor;
	}

	/**
	 * Permet de fixer un tresor a la carte du labyrinthe donnee.
	 * 
	 * @param tresor : le tresor a fixer a la carte.
	 */
	public void setTresor(Tresor tresor) {
		this.tresor = tresor;
	}

	/**
	 * Permet de deplacer la carte du labyrinthe de la position initiale a la
	 * nouvelle position donnee
	 * 
	 * @param x : la position x de destination. De type entier.
	 * @param y : la position y de destination. De type entier.
	 */
	public void deplacer(int x, int y) {
		setPos_x(x);
		setPos_y(y);
	}

	/**
	 * Permet de modifier les chemins ouverts en fonction du sens et du type de la
	 * carte. Cree un tableau, qui sera compose de booleen qui precise s'il y a ou
	 * connexion entre deux cartes ou non. Vrai si il y a une connexion, faux si la
	 * carte est face a un mur.
	 */
	public void setOuvert() {

		if (this.forme.equals(Forme.DROIT)) {

			if (this.sens.equals(Sens.HAUT) || this.sens.equals(Sens.BAS)) {
				ouvert[0] = true;
				ouvert[1] = false;
				ouvert[2] = true;
				ouvert[3] = false;
			}
			if (this.sens.equals(Sens.DROITE) || this.sens.equals(Sens.GAUCHE)) {
				ouvert[0] = false;
				ouvert[1] = true;
				ouvert[2] = false;
				ouvert[3] = true;
			}
		}

		if (this.forme.equals(Forme.ANGLE_D)) {

			if (this.sens.equals(Sens.HAUT)) {
				ouvert[0] = true;
				ouvert[1] = true;
				ouvert[2] = false;
				ouvert[3] = false;
			} else if (this.sens.equals(Sens.DROITE)) {
				ouvert[0] = false;
				ouvert[1] = true;
				ouvert[2] = true;
				ouvert[3] = false;
			} else if (this.sens.equals(Sens.BAS)) {
				ouvert[0] = false;
				ouvert[1] = false;
				ouvert[2] = true;
				ouvert[3] = true;
			} else if (this.sens.equals(Sens.GAUCHE)) {
				ouvert[0] = true;
				ouvert[1] = false;
				ouvert[2] = false;
				ouvert[3] = true;
			}
		}

		if (this.forme.equals(Forme.EN_T)) {

			if (this.sens.equals(Sens.HAUT)) {
				ouvert[0] = true;
				ouvert[1] = true;
				ouvert[2] = false;
				ouvert[3] = true;
			} else if (this.sens.equals(Sens.DROITE)) {
				ouvert[0] = true;
				ouvert[1] = true;
				ouvert[2] = true;
				ouvert[3] = false;
			} else if (this.sens.equals(Sens.BAS)) {
				ouvert[0] = false;
				ouvert[1] = true;
				ouvert[2] = true;
				ouvert[3] = true;
			} else if (this.sens.equals(Sens.GAUCHE)) {
				ouvert[0] = true;
				ouvert[1] = false;
				ouvert[2] = true;
				ouvert[3] = true;
			}
		}

	}

	/**
	 * Permet de recuperer en lecture un tableau de type booleen qui permet de
	 * savoir le type de connexion entre deux cartes.
	 * 
	 * @return un tableau a une dimension de type booleen.
	 */
	public boolean[] getOuvert() {
		return ouvert;
	}

	/**
	 * Génère aléatoirement un sens de Carte
	 */
	public void randomSens() {
		Sens[] lesSens = Sens.values();
		int x = new Random().nextInt(4);
		this.setSens(lesSens[x]);
	}

	/**
	 * Surcharge de la fonction toString. Permet d'afficher les informations d'une
	 * carte definie.
	 */
	@Override
	public String toString() {
		return "Cartes_laby [pos_x=" + pos_x + ", pos_y=" + pos_y + ", forme=" + forme + ", sens=" + sens + ", fixe="
				+ fixe + ", tresor=" + tresor + "]";
	}

}
