package fr.li3.coo.projet;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe implementant les fonctionnalités principales du jeu(plateau, Joueurs,
 * cartes,trésors,...)
 * 
 * @author Theo Gayant
 */
public class Jeu {

	private Cartes_laby[][] plateau;
	private ArrayList<Joueur> joueurs;
	private ArrayList<Cartes_laby> cartes;
	private ArrayList<Tresor> tresors;
	private Cartes_laby laCarte;
	private Joueur leJoueur;
	private boolean laCartePlacer = false;

	public ArrayList<Cartes_laby> getCartes() {
		return cartes;
	}

	/**
	 * Constructeur de la classe.
	 */
	public Jeu() {
		super();
		this.genererCartes();
		this.genererTresors();
		this.genererJoueur();
		this.distribuerTresorsJoueurs();
		this.genererPlateau();
		this.setLeJoueur(this.getJoueurs().get(0));
	}

	/**
	 * Permet de générer et intialiser le plateau de jeu et ses cartes immobiles.
	 */
	private void genererPlateau() {
		plateau = new Cartes_laby[7][7];
		// CARTES FIXE
		plateau[0][0] = new Cartes_laby(0, 0, Forme.ANGLE_D, Sens.DROITE, true, null);
		plateau[0][2] = new Cartes_laby(0, 2, Forme.EN_T, Sens.BAS, true, this.removeT("crane"));
		plateau[0][4] = new Cartes_laby(0, 4, Forme.EN_T, Sens.BAS, true, this.removeT("epee"));
		plateau[0][6] = new Cartes_laby(0, 6, Forme.ANGLE_D, Sens.BAS, true, null);

		plateau[2][0] = new Cartes_laby(2, 0, Forme.EN_T, Sens.DROITE, true, this.removeT("sac de pieces"));
		plateau[2][2] = new Cartes_laby(2, 2, Forme.EN_T, Sens.DROITE, true, this.removeT("clefs"));
		plateau[2][4] = new Cartes_laby(2, 4, Forme.EN_T, Sens.BAS, true, this.removeT("emeraude"));
		plateau[2][6] = new Cartes_laby(2, 6, Forme.EN_T, Sens.GAUCHE, true, this.removeT("armure"));

		plateau[4][0] = new Cartes_laby(4, 0, Forme.EN_T, Sens.DROITE, true, this.removeT("grimoire"));
		plateau[4][2] = new Cartes_laby(4, 2, Forme.EN_T, Sens.HAUT, true, this.removeT("couronne"));
		plateau[4][4] = new Cartes_laby(4, 4, Forme.EN_T, Sens.GAUCHE, true, this.removeT("coffre au tresor"));
		plateau[4][6] = new Cartes_laby(4, 6, Forme.EN_T, Sens.GAUCHE, true, this.removeT("chandelier"));

		plateau[6][0] = new Cartes_laby(6, 0, Forme.ANGLE_D, Sens.HAUT, true, null);
		plateau[6][2] = new Cartes_laby(6, 2, Forme.EN_T, Sens.HAUT, true, this.removeT("carte au tresor"));
		plateau[6][4] = new Cartes_laby(6, 4, Forme.EN_T, Sens.HAUT, true, this.removeT("anneau d’or"));
		plateau[6][6] = new Cartes_laby(6, 6, Forme.ANGLE_D, Sens.GAUCHE, true, null);

		// CARTES MOBILE
		this.distribuerTresorsCartes();
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= 6; j++) {
				// Si on est sur un emplacement vide
				if (plateau[i][j] == null) {
					plateau[i][j] = cartes.get(0);
					// On change la position de la carte
					plateau[i][j].deplacer(i, j);
					// On l'enlève de la liste
					cartes.remove(0);
				}
			}
		}
		// La carte que les joueurs pourront placer
		laCarte = cartes.get(0);

		// Met à jour les chemins ouvert de la carte en fontion du son sens/forme
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= 6; j++) {
				plateau[i][j].setOuvert();
			}
		}
		laCarte.setOuvert();
	}

	/**
	 * Création de la liste des joueurs ainsi que leur initialisation
	 * 
	 */
	private void genererJoueur() {
		joueurs = new ArrayList<Joueur>();
		// Ajout Joueur 1
		joueurs.add(new Joueur(1, 0, 0));
		joueurs.add(new Joueur(2, 0, 6));
		joueurs.add(new Joueur(3, 6, 0));
		joueurs.add(new Joueur(4, 6, 6));
	}

	/**
	 * Création de la liste des cartes laby ainsi que leur initialisation et
	 * randomisation (12 Droites,16 Angle Droit, 6 en T)
	 * 
	 */
	private void genererCartes() {
		cartes = new ArrayList<Cartes_laby>();
		int i;
		for (i = 1; i <= 12; i++)
			cartes.add(new Cartes_laby(Forme.DROIT));
		for (i = 1; i <= 16; i++)
			cartes.add(new Cartes_laby(Forme.ANGLE_D));
		for (i = 1; i <= 6; i++)
			cartes.add(new Cartes_laby(Forme.EN_T));
		// On mélange le sens des cartes de façon aléatoire
		cartes.forEach((c) -> c.randomSens());
		// On mélange toute les cartes
		Collections.shuffle(cartes);
	}

	/**
	 * Génère la liste de trésors pour jouer au jeu
	 * 
	 */
	private void genererTresors() {
		tresors = new ArrayList<Tresor>();
		// On récupère tous les tresors de l'enum
		ListeTresor[] lesTresors = ListeTresor.values();
		for (ListeTresor t : lesTresors) {
			tresors.add(new Tresor(t.getNom(), t.isVivant()));
		}
		Collections.shuffle(tresors);
	}

	/**
	 * Permet de retirer de la liste un tresors selon son nom
	 * 
	 * @param nom : Nom du trésor qu'on a besoin
	 * @return le tresor de la liste
	 */
	private Tresor removeT(String nom) {
		int i = 0;
		while (tresors.get(i).getNom() != nom) {
			i++;
		}
		return tresors.remove(i);
	}

	/**
	 * Distribue les 24 trésors aux joueurs (chacun 6)
	 */
	private void distribuerTresorsJoueurs() {
		int cpt = 0;
		for (int i = 0; i < joueurs.size(); i++) { // Pour chaque joueurs
			Joueur player = joueurs.get(i);
			// System.out.println("Joueur n°" + player.getId() + ":\n");
			for (int j = 0; j < 6; j++) { // On lui distribue 6 tresors
				player.ajout_carte(tresors.get(cpt).getNom());
				// System.out.println("- " + tresors.get(cpt).getNom() + "\n");
				cpt++;
			}
		}
	}

	/**
	 * Distribue les 12 trésors restant à 6 carte en T & 6 en ANGLE_D
	 */
	private void distribuerTresorsCartes() {
		// 16 en ANGLE_D dont 6 Tresor & 6 EN_T dont 6 Tresor
		ArrayList<Boolean> tab = new ArrayList<Boolean>();
		for (int i = 0; i < 10; i++)
			tab.add(false);
		for (int i = 0; i < 6; i++)
			tab.add(true);
		Collections.shuffle(tab);
		Collections.shuffle(tresors); // On remélange les tresors afin de les placers sur les cartes restantes
		int size = cartes.size();
		int cpt_tab = 0;
		for (int i = 0; i < size; i++) {
			Cartes_laby c = cartes.get(i);
			// Si la forme est en T on place un trésor
			if (c.getForme().equals(Forme.EN_T)) {
				c.setTresor(tresors.remove(0));
			} else if (c.getForme().equals(Forme.ANGLE_D)) {// Si la forme est en angle droit
				if (tab.get(cpt_tab)) {
					c.setTresor(tresors.remove(0));
				}
				cpt_tab++;
			}
		}
	}

	/**
	 * Placer la Carte disponible sur le plateau
	 * 
	 * @param posX : la position x a initialiser
	 * @param posY : la position y a initialiser
	 * @return Renvoie vrai si on a pu placer la carte, faux si il y a eu un
	 *         problème.
	 */
	public boolean placerCarte(int posX, int posY) {
		// Si le joueur a deja placer une carte
		if (this.laCartePlacer == true) {
			return false;
		}
		// Si on veut placer la carte enface du dernier endroit où elle a était inserer
		if (posX == this.laCarte.getPos_x() && posY == this.laCarte.getPos_y())
			return false;
		// Si on veut placer la carte par la haut ou le bas du plateau
		if (posX == 0 || posX == 6) {
			if (!plateau[posX][posY].isFixe()) {
				if (posX == 0)
					decalerColonne(1, posX, posY);
				else
					decalerColonne(-1, posX, posY);
				this.laCartePlacer = true;
				return true;
			} else
				return false;
		}
		// Si on veut placer la carte par la gauche ou la droite
		if (posY == 0 || posY == 6) {
			if (!plateau[posX][posY].isFixe()) {
				if (posY == 0)
					decalerLigne(1, posX, posY);
				else
					decalerLigne(-1, posX, posY);
				this.laCartePlacer = true;
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * Permet de decaler la colonne en fonction de la position donnee, du x et du y.
	 * 
	 * @param p : position donnée pour la colonne
	 * @param posX : position x donnée
	 * @param posY : position y donnée
	 */
	private void decalerColonne(int p, int posX, int posY) {
		// On vérifie si on doit décaler les joueurs
		joueurs.forEach((jo) -> this.decalerJoueurColonne(jo, posX, posY));
		// Si on decale de haut en bas
		if (p == 1) {
			Cartes_laby tmp = laCarte;
			laCarte = plateau[6][posY];
			for (int i = 5; i >= 0; i--) {
				plateau[i][posY].deplacer(i + 1, posY);
				plateau[i + 1][posY] = plateau[i][posY];
			}
			plateau[0][posY] = tmp;
			plateau[0][posY].deplacer(0, posY);
		} else {
			Cartes_laby tmp = laCarte;
			laCarte = plateau[0][posY];
			for (int i = 0; i <= 5; i++) {
				plateau[i][posY] = plateau[i + 1][posY];
				plateau[i][posY].deplacer(i, posY);
			}
			plateau[6][posY] = tmp;
			plateau[6][posY].deplacer(6, posY);
		}
	}

	/**
	 * Permet de decaler la ligne selon les coordonnees choisies.
	 * 
	 * @param p : position donnée pour la ligne
	 * @param posX : position x donnée
	 * @param posY : position y donnée
	 */
	private void decalerLigne(int p, int posX, int posY) {
		// On vérifie si on doit décaler les joueurs
		joueurs.forEach((jo) -> this.decalerJoueurLigne(jo, posX, posY));
		// Si on decale de gauche a droite
		if (p == 1) {
			Cartes_laby tmp = laCarte;
			laCarte = plateau[posX][6];
			for (int i = 5; i >= 0; i--) {
				plateau[posX][i].deplacer(posX, i + 1);
				plateau[posX][i + 1] = plateau[posX][i];
			}
			plateau[posX][0] = tmp;
			plateau[posX][0].deplacer(posX, 0);
		} else { // Si on decale de droite a gauche
			Cartes_laby tmp = laCarte;
			laCarte = plateau[posX][0];
			for (int i = 0; i <= 5; i++) {
				plateau[posX][i] = plateau[posX][i + 1];
				plateau[posX][i].deplacer(posX, i);
			}
			plateau[posX][6] = tmp;
			plateau[posX][6].deplacer(posX, 6);
		}
	}

	
	/** Décale le joueur de la colonne a la position x et y
	 * 
	 * @param jo : le joueur à décaler
	 * @param posX : la position x
	 * @param posY : la position y
	 */
	private void decalerJoueurColonne(Joueur jo, int posX, int posY) {
		int decalage = 1;
		if (posX == 6)
			decalage *= -1;
		// Si il est sur la meme colonne que celle qui ce déplace
		if (jo.getPos_y() == posY) {
			// Si le joueur sort du plateau
			if (jo.getPos_x() + decalage < 0 || jo.getPos_x() + decalage > 6) {
				// Si il a encore des cartes alors on le ramène a sa case de départ
				if (jo.getNb_cartes() != 0) {
					jo.deplacer(jo.getPos_init_x(), jo.getPos_init_y());
				} else {
					int x, y;
					if (jo.getId() == 1 || jo.getId() == 2)
						x = 6;
					else
						x = 0;
					if (jo.getId() == 1 || jo.getId() == 3)
						y = 6;
					else
						y = 0;
					jo.deplacer(x, y);
				}
			} else {
				jo.deplacer(jo.getPos_x() + decalage, jo.getPos_y());
			}
		}
	}

	/** Décale le joueur de la ligne a la position x et y
	 * 
	 * @param jo : le joueur à décaler
	 * @param posX : la position x
	 * @param posY : la position y
	 */
	private void decalerJoueurLigne(Joueur jo, int posX, int posY) {
		int decalage = 1;
		if (posY == 6)
			decalage *= -1;
		// Si il est sur la meme ligne que celle qui se déplace
		if (jo.getPos_x() == posX) {
			// Si le joueur sort du plateau
			if (jo.getPos_y() + decalage < 0 || jo.getPos_y() + decalage > 6) {
				// Si il a encore des cartes alors on le ramène a sa case de départ
				if (jo.getNb_cartes() != 0) {
					jo.deplacer(jo.getPos_init_x(), jo.getPos_init_y());
				} else {
					int x, y;
					if (jo.getId() == 1 || jo.getId() == 2)
						x = 6;
					else
						x = 0;
					if (jo.getId() == 1 || jo.getId() == 3)
						y = 6;
					else
						y = 0;
					jo.deplacer(x, y);
				}
			} else {
				jo.deplacer(jo.getPos_x(), jo.getPos_y() + decalage);
			}
		}
	}

	/** Permet de déplacer le joueur selon une direction choisie
	 * 
	 * @param ch : le chemin à choisir sous forme de chaine de caractères
	 * @return si le déplacement s'est bien déroulé (true) ou non (false)
	 */
	public boolean deplacerJoueur(String ch) {
		boolean res = false;
		if (!this.laCartePlacer)
			return false;
		if (leJoueur.isTerminer()) {
			return false;
		}
		if (ch == "d") {
			if (leJoueur.getPos_y() < 6) {
				// LA CARTE SITUE A DROITE
				Cartes_laby c1 = this.getPlateau()[leJoueur.getPos_x()][leJoueur.getPos_y() + 1];
				// LA CARTE OU LE JOUEUR SE SITUE
				Cartes_laby c2 = this.getPlateau()[leJoueur.getPos_x()][leJoueur.getPos_y()];
				// SI IL N'Y A PAS DE MUR ENTRE LES DEUX CARTES
				if (c1.getOuvert()[3] && c2.getOuvert()[1]) {
					leJoueur.deplacer(leJoueur.getPos_x(), leJoueur.getPos_y() + 1);
					res = true;
				}
			}
		} else if (ch == "g") {
			if (leJoueur.getPos_y() > 0) {
				// LA CARTE SITUE A GAUCHE
				Cartes_laby c1 = this.getPlateau()[leJoueur.getPos_x()][leJoueur.getPos_y() - 1];
				// LA CARTE OU LE JOUEUR SE SITUE
				Cartes_laby c2 = this.getPlateau()[leJoueur.getPos_x()][leJoueur.getPos_y()];
				// SI IL N'Y A PAS DE MUR ENTRE LES DEUX CARTES
				if (c1.getOuvert()[1] && c2.getOuvert()[3]) {
					leJoueur.deplacer(leJoueur.getPos_x(), leJoueur.getPos_y() - 1);
					res = true;
				}
			}
		} else if (ch == "h") {
			if (leJoueur.getPos_x() > 0) {
				// LA CARTE SITUE AU DESSUS
				Cartes_laby c1 = this.getPlateau()[leJoueur.getPos_x() - 1][leJoueur.getPos_y()];
				// LA CARTE OU LE JOUEUR SE SITUE
				Cartes_laby c2 = this.getPlateau()[leJoueur.getPos_x()][leJoueur.getPos_y()];
				// SI IL N'Y A PAS DE MUR ENTRE LES DEUX CARTES
				if (c1.getOuvert()[2] && c2.getOuvert()[0]) {
					leJoueur.deplacer(leJoueur.getPos_x() - 1, leJoueur.getPos_y());
					res = true;
				}
			}
		} else if (ch == "b") {
			if (leJoueur.getPos_x() < 6) {
				// LA CARTE SITUE EN DESSOUS
				Cartes_laby c1 = this.getPlateau()[leJoueur.getPos_x() + 1][leJoueur.getPos_y()];
				// LA CARTE OU LE JOUEUR SE SITUE
				Cartes_laby c2 = this.getPlateau()[leJoueur.getPos_x()][leJoueur.getPos_y()];
				// SI IL N'Y A PAS DE MUR ENTRE LES DEUX CARTES
				if (c1.getOuvert()[0] && c2.getOuvert()[2]) {
					leJoueur.deplacer(leJoueur.getPos_x() + 1, leJoueur.getPos_y());
					res = true;
				}
			}
		}
		// Si le joueur a pu se déplacer
		if (res) {
			// Si il est sur un trésor
			if (this.plateau[leJoueur.getPos_x()][leJoueur.getPos_y()].getTresor() != null) {
				// On vérifie qu'il correspond au trésor qu'il doit trouvé
				boolean r = leJoueur
						.tresor_trouve(this.plateau[leJoueur.getPos_x()][leJoueur.getPos_y()].getTresor().getNom());
				// Si il est sur son trésor on passe au suivant
				if (r)
					this.joueurSuivant();
			}
			// Sinon si il a trouvé toute ses cartes au trésor et qu'il se situe sur sa case
			// départ
			else if (this.leJoueur.getNb_cartes() == 0) {
				leJoueur.tresor_trouve("");
				if (leJoueur.isTerminer()) {
					this.joueurSuivant();
				}
			}
		}
		return res;
	}

	/**
	 * Surcharge de la fonction toString.
	 * @return un String.
	 */
	@Override
	public String toString() {
		String ch = "";
		String tmp;
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= 6; j++) {
				ch = ch + plateau[i][j].toString() + "\n";
				boolean[] o = plateau[i][j].getOuvert();
				tmp = "Haut : " + o[0] + " Droite : " + o[1] + " Bas : " + o[2] + " Gauche :" + o[3];
				ch = ch + tmp + "\n\n";
			}
		}
		ch = ch + "La Carte : " + laCarte.toString() + "\n==========\n";
		return ch;
	}

	/**
	 * Permet d'accéder en lecture a la liste des joueurs.
	 * 
	 * @return Une ArrayList de Joueurs.
	 */
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	/**
	 * Permet d'obtenir le plateau
	 * 
	 * @return the plateau
	 */
	public Cartes_laby[][] getPlateau() {
		return plateau;
	}

	/**
	 * Obtenir la carte a placer
	 * 
	 * @return the laCarte
	 */
	public Cartes_laby getLaCarte() {
		return laCarte;
	}

	/**
	 * Obtenir le joueur actif
	 * 
	 * @return the leJoueur
	 */
	public Joueur getLeJoueur() {
		return leJoueur;
	}

	/**
	 * Changer le joueur actif
	 * 
	 * @param leJoueur the leJoueur to set
	 */
	public void setLeJoueur(Joueur leJoueur) {
		this.leJoueur = leJoueur;
	}

	/**
	 * Permet de passer au tour du joueur suivant
	 */
	public void joueurSuivant() {
		// Si le joueur a placer la carte on peut passer au tour suivant
		if (this.laCartePlacer) {
			// On incrémente au joueur suivant
			this.leJoueur = joueurs.get(this.getLeJoueur().getId() % 4);
			// Si ce joueur a déjà terminer, on passe au suivant
			if (this.leJoueur.isTerminer()) {
				this.joueurSuivant();
			}
			this.laCartePlacer = false;
		}
	}

}
