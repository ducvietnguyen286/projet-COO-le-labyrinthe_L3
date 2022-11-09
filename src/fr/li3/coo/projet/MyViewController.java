package fr.li3.coo.projet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


 /* Classe contrôleur de la partie vue du jeu principal.
 * @author Théo Gayant
 * 
 */

public class MyViewController {

	private Jeu jeu;

	/**
	 * Le label qui affiche le joueur actif
	 */
	@FXML
	private Label joueurActif;

	/**
	 * Ajoute un jeu ce qui permet de relier le modele
	 * @param j : le jeu à ajouter
	 */
	public void addJeu(Jeu j) {
		jeu = j;
	}

	/**
	 * Le bouton qui permet de passer au joueur suivant
	 */
	@FXML
	private Button myButton;

	/**
	 * Passe au tour suivant
	 * 
	 * @param event : Au clique sur le bouton suivant
	 */
	@FXML
	void clickButton(ActionEvent event) {
		this.jeu.joueurSuivant();
		majJoueurActif();
	}

	/**
	 * Met a jour le joueur actif sur le pad
	 */
	void majJoueurActif() {
		joueurActif.getStyleClass().clear();
		joueurActif.getStyleClass().add("label");
		joueurActif.getStyleClass().add("joueur" + this.jeu.getLeJoueur().getId());
	}

	/**
	 * Le label de la carte à placer
	 */
	@FXML
	private Label laCarte;
	/**
	 * Le label du trésor qui se situe sur la carte a placer
	 */
	@FXML
	private Label laCarteT;

	/**
	 * Le bouton qui permet la rotation de la carte a placer
	 */
	@FXML
	private Button btnRotation;

	/**
	 * Permet de changer le sens de la carte à placer
	 * 
	 * @param event Au clique du bouton changer de Sens de la carte à placer
	 */
	@FXML
	void rotation_carte(ActionEvent event) {
		this.jeu.getLaCarte().tourneSensDroite();
		laCarte.getStyleClass().clear();
		laCarte.getStyleClass().add("label");
		majForme(laCarte, this.jeu.getLaCarte().getForme());
		majSens(laCarte, this.jeu.getLaCarte().getSens());
	}

	/**
	 * Le gridpane contenant le plateau du jeu de taille, ainsi que les fleche
	 * d'insertion des cartes 9*9, composé en 3 couches ( 1 : les cartes labyrinthes
	 * / 2 : les trésors / 3 : les joueurs)
	 */
	@FXML
	private GridPane plateau;

	/**
	 * Décalage pour accéder à la couche des cartes labyrinthes du gridpane plateau
	 */
	static int DECALAGE_CARTES = 12;

	/**
	 * Décalage pour accéder à la couche des trésors du gridpane plateau
	 */
	static int DECALAGE_TRESOR = 12 + 49;

	/**
	 * Décalage pour accéder à la couches des pions des joueurs du gridpane plateau
	 */
	static int DECALAGE_PION = 12 + 49 + 49;

	/**
	 * Met à jour tout ce qui est necessaire pour la vue lors de l'initialisation
	 */
	void majAll() {
		this.majCartes();
		this.majJoueurActif();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.getJoueurs().forEach((jo) -> this.majTblObjectif(jo.getId()));
	}

	/**
	 * Permet de mettre à jour entièrement le plateau ainsi que la carte à placer
	 */
	void majCartes() {
		// le plateau des cartes labyrinthes
		Cartes_laby[][] plat = this.jeu.getPlateau();

		// la carte à placer
		Cartes_laby carte = this.jeu.getLaCarte();

		// CARTES_LABY
		// le label de la couche des cartes
		Node n;
		// le label de la couche des trésors
		Node n_tresor = null;
		Cartes_laby c;
		Forme f;
		Sens s;
		// pour les 50 cartes
		for (int i = 0; i < 50; i++) {
			if (i != 49) {// CARTE DU PLATEAU
				n = plateau.getChildren().get(DECALAGE_CARTES + i);
				n_tresor = plateau.getChildren().get(DECALAGE_TRESOR + i);
				int lig = i / 7;
				int col = i % 7;
				f = plat[lig][col].getForme();
				s = plat[lig][col].getSens();
				c = plat[lig][col];
			} else {// LA CARTE A PLACER
				n = laCarte;
				n_tresor = laCarteT;
				f = carte.getForme();
				s = carte.getSens();
				c = this.jeu.getLaCarte();
			}
			n.getStyleClass().clear();
			n.getStyleClass().add("label");

			// FORME
			majForme(n, f);
			// FORME

			// SENS
			majSens(n, s);
			// SENS

			// On met a jour le label trésor
			this.majTresor(n_tresor, c);
		}
		// CARTES_LABY
	}

	/**
	 * Permet d'appliquer la forme f de la carte laby au label n
	 * 
	 * @param n le Label à modifier
	 * @param f la Forme de la carte laby
	 */
	void majForme(Node n, Forme f) {
		if (f.equals(Forme.ANGLE_D)) {
			n.getStyleClass().add("angle");
		} else if (f.equals(Forme.DROIT)) {
			n.getStyleClass().add("droit");
		} else if (f.equals(Forme.EN_T)) {
			n.getStyleClass().add("en_t");
		}
	}

	/**
	 * Permet d'appliquer le sens s de la carte laby au label n
	 * 
	 * @param n le Label à modifier
	 * @param s le sens de la carte laby
	 */
	void majSens(Node n, Sens s) {
		if (s.equals(Sens.DROITE)) {
			n.getStyleClass().add("fgauche");
		} else if (s.equals(Sens.BAS)) {
			n.getStyleClass().add("fbas");
		} else if (s.equals(Sens.GAUCHE)) {
			n.getStyleClass().add("fdroite");
		} else if (s.equals(Sens.HAUT)) {
			n.getStyleClass().add("fhaut");
		}
	}

	/**
	 * Met a jour le tresor en fonction de la carte c
	 * 
	 * @param n le noeud a mettre a jour
	 * @param c la carte laby qui lui correspond
	 */
	void majTresor(Node n, Cartes_laby c) {
		n.getStyleClass().clear();
		n.getStyleClass().add("label");
		if (c.getTresor() != null) {
			Tresor t = c.getTresor();
			String ch;
			int i = t.getNom().indexOf(" ");
			if (i != -1) {
				ch = t.getNom().substring(0, i);
			} else
				ch = t.getNom();
			n.getStyleClass().add(ch);
		}
	}

	/**
	 * Permet d'affficher le joueur selon sa position
	 * 
	 * @param id l'id du Joueur
	 * @param x  Position en x (ligne)
	 * @param y  Position en y (colonne)
	 */
	void majJoueur(int id, int x, int y) {
		// JOUEUR
		Node n = plateau.getChildren().get(DECALAGE_PION + x * 7 + y);
		n.getStyleClass().add("joueur" + id);
		// JOUEUR
	}

	/**
	 * Enleve l'image du joueur de la case
	 * 
	 * @param id id du joueur
	 * @param x  position x
	 * @param y  position y
	 */
	void enleveJoueur(int id, int x, int y) {
		Node n = plateau.getChildren().get(DECALAGE_PION + x * 7 + y);
		n.getStyleClass().remove("joueur" + id);
	}

	/**
	 * Le gridpane du tableau qui permet d'afficher a chaque joueur le trésor qu'il
	 * doit trouvé
	 */
	@FXML
	private GridPane tblObjectif;

	/**
	 * Pertmet d'afficher le trésor qui correspont à l'objectif du joueur id
	 * 
	 * @param id l'id du joueur au quel on veut mettre a jour le tresor
	 */
	void majTblObjectif(int id) {
		Node n = tblObjectif.getChildren().get(2 * (id - 1) + 1);
		n.getStyleClass().clear();
		n.getStyleClass().add("label");
		String ch = this.jeu.getJoueurs().get(id - 1).getCarteATrouver();
		int i = ch.indexOf(" ");
		if (i != -1) {
			ch = ch.substring(0, i);
		}
		n.getStyleClass().add(ch);
	}

	/**
	 * Déplace le joueur d'une case vers le bas
	 * 
	 * @param event Quand on appuie sur la fleche qui va vers le bas
	 */
	@FXML
	void deplacerBas(MouseEvent event) {
		Joueur joueur = this.jeu.getLeJoueur();
		int nb_tresor = joueur.getNb_cartes();
		if (this.jeu.deplacerJoueur("b")) {
			this.enleveJoueur(joueur.getId(), joueur.getPos_x() - 1, joueur.getPos_y());
			this.majJoueur(joueur.getId(), joueur.getPos_x(), joueur.getPos_y());
			if (nb_tresor != joueur.getNb_cartes()) {
				this.majTblObjectif(joueur.getId());
			}
			this.majJoueurActif();
		}
	}

	/**
	 * Déplace le joueur d'une case vers la droite
	 * 
	 * @param event Quand on appuie sur la fleche qui va vers la droite
	 */
	@FXML
	void deplacerDroit(MouseEvent event) {
		Joueur joueur = this.jeu.getLeJoueur();
		int nb_tresor = joueur.getNb_cartes();
		if (this.jeu.deplacerJoueur("d")) {
			this.enleveJoueur(joueur.getId(), joueur.getPos_x(), joueur.getPos_y() - 1);
			this.majJoueur(joueur.getId(), joueur.getPos_x(), joueur.getPos_y());
			if (nb_tresor != joueur.getNb_cartes()) {
				this.majTblObjectif(joueur.getId());
			}
			this.majJoueurActif();
		}
	}

	/**
	 * Déplace le joueur d'une case vers la gauche
	 * 
	 * @param event Quand on appuie sur la fleche qui va vers la gauche
	 */
	@FXML
	void deplacerGauche(MouseEvent event) {
		Joueur joueur = this.jeu.getLeJoueur();
		int nb_tresor = joueur.getNb_cartes();
		if (this.jeu.deplacerJoueur("g")) {
			this.enleveJoueur(joueur.getId(), joueur.getPos_x(), joueur.getPos_y() + 1);
			this.majJoueur(joueur.getId(), joueur.getPos_x(), joueur.getPos_y());
			if (nb_tresor != joueur.getNb_cartes()) {
				this.majTblObjectif(joueur.getId());
			}
			this.majJoueurActif();
		}
	}

	/**
	 * Déplace le joueur d'une case vers le haut
	 * 
	 * @param event Quand on appuie sur la fleche qui va vers le haut
	 */
	@FXML
	void deplacerHaut(MouseEvent event) {
		Joueur joueur = this.jeu.getLeJoueur();
		int nb_tresor = joueur.getNb_cartes();
		if (this.jeu.deplacerJoueur("h")) {
			this.enleveJoueur(joueur.getId(), joueur.getPos_x() + 1, joueur.getPos_y());
			this.majJoueur(joueur.getId(), joueur.getPos_x(), joueur.getPos_y());
			if (nb_tresor != joueur.getNb_cartes()) {
				this.majTblObjectif(joueur.getId());
			}
			this.majJoueurActif();
		}
	}

	/**
	 * Permet d'ajouter la carte a la position (0,1)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteH1(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(0, 1);
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.majCartes();
	}

	/**
	 * Permet d'ajouter la carte a la position (0,3)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteH2(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(0, 3);
		this.majCartes();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
	}

	/**
	 * Permet d'ajouter la carte a la position (0,5)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteH3(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(0, 5);
		this.majCartes();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
	}

	/**
	 * Permet d'ajouter la carte a la position (6,1)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteB1(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(6, 1);
		this.majCartes();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
	}

	/**
	 * Permet d'ajouter la carte a la position (6,3)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteB2(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(6, 3);
		this.majCartes();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
	}

	/**
	 * Permet d'ajouter la carte a la position (6,5)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteB3(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(6, 5);
		this.majCartes();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
	}

	/**
	 * Permet d'ajouter la carte a la position (1,6)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteD1(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(1, 6);
		this.majCartes();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
	}

	/**
	 * Permet d'ajouter la carte a la position (3,6)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteD2(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(3, 6);
		this.majCartes();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
	}

	/**
	 * Permet d'ajouter la carte a la position (5,6)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteD3(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(5, 6);
		this.majCartes();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
	}

	/**
	 * Permet d'ajouter la carte a la position (1,0)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteG1(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(1, 0);
		this.majCartes();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
	}

	/**
	 * Permet d'ajouter la carte a la position (3,0)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteG2(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(3, 0);
		this.majCartes();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
	}

	/**
	 * Permet d'ajouter la carte a la position (5,0)
	 * 
	 * @param event Au clique de la fleche d'insertion de la carte a placer
	 */
	@FXML
	void insererCarteG3(MouseEvent event) {
		this.jeu.getJoueurs().forEach((jo) -> this.enleveJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
		this.jeu.placerCarte(5, 0);
		this.majCartes();
		this.jeu.getJoueurs().forEach((jo) -> this.majJoueur(jo.getId(), jo.getPos_x(), jo.getPos_y()));
	}
}
