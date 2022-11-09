package fr.li3.coo.projet;

import java.util.ArrayList;
import java.lang.Math;
/**
 * Classe permettant d'implémenter le principe d'une intelligence articifielle.
 * 
 * @author Olivier Buhendwa
 *
 */

public class IA extends Joueur {
	
	private boolean[][] dejaVu = new boolean[7][7];
	private Cartes_laby[][] plateau_jeu;	

	/**
	 * @param id : identifiant du joueur.
	 * @param x : coordonnée x de base
	 * @param y : coordonnée y de base
	 * @param plateau : le plateau sur lequel mettre le joueur.
	 */
	public IA(int id, int x, int y, Cartes_laby[][] plateau) {
		super(id, x, y);
		this.plateau_jeu = plateau;
	}

	/**Permet d'accéder en lecture à la variable plateau_jeu.
	 * @return the plateau_jeu
	 */
	public Cartes_laby[][] getPlateau_jeu() {
		return plateau_jeu;
	}

	/**Permet d'accéder en écriture à la variable plateau_jeu.
	 * @param plateau_jeu the plateau_jeu to set
	 */
	public void setPlateau_jeu(Cartes_laby[][] plateau_jeu) {
		this.plateau_jeu = plateau_jeu;
	}

	
	/**
	 * Cette méthode nous permettra à réintialiser à faux notre matrice 
	 */
	public void initDejaVu() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				dejaVu[i][j] = false;
			}
		}
	}
	
	/**
	 * Cette méthode nous permet de vérifier si la case aux coordonnées i et j 
	 * a été déjà visitée.
	 * @param i : la coordonnée i (semblable à x)
	 * @param j : la coordonnée j (semblable à y)
	 * @return boolean[][]
	 */
	public boolean estDejaVu(int i, int j) {
		return dejaVu[i][j];
	}
	
	/**
	 * Cette méthode nous permettra à récupérer la distance minimum
	 * entre la case du trésor (x,y) et toutes les cases visitées.
	 * @param x : la coordonnée x
	 * @param y : la coordonnée y
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> casePlusProche(int x, int y)
	{
		ArrayList<Integer> tab = new ArrayList<Integer>(2);
		int min = 10 ; // 10 car la distance entre les deux points les plus éloignés vaut 8
 		for(int i = 0; i < 7; i++) {
			for(int j = 0; i < 7; i++) {
				if(dejaVu[i][j])
				{
					if(this.getPos_x() != i && this.getPos_y() != j)
					{
						if(this.distance(x, y, i, j) < min)
						{
							min = this.distance(x, y, i, j);
							tab.set(0,i);
							tab.set(1,j);
						}
					}
				}
			}
		}
 		return tab;
	}
	
	/**
	 * Cette méthode nous permettra à récupérer la distance minimum
	 * entre la case départ et toutes les cases visitées.
	 * @return une ArrayList d'entiers indiquant une distance.
	 */
	public ArrayList<Integer> casePlusProche()
	{
		ArrayList<Integer> tab = new ArrayList<Integer>(2);
		int min = 10 ; // 10 car la distance entre les deux points les plus éloignés vaut 8
 		for(int i = 0; i < 7; i++) {
			for(int j = 0; i < 7; i++) {
				if(dejaVu[i][j])
				{
					if(this.getPos_x() != i && this.getPos_y() != j)
					{
						if(this.distance(this.getPos_init_x(), this.getPos_init_y(), i, j) < min)
						{
							min = this.distance(this.getPos_init_x(), this.getPos_init_y(), i, j);
							tab.set(0,i);
							tab.set(1,j);
						}
					}
				}
			}
		}
 		return tab;
	}
	
	
	/**
	 * Cette méthode nous permettra à récupérer les coordonnées du trésor recherché.
	 * @param nom_tresor
	 * @return une ArrayList<Integer>
	 */
	public ArrayList<Integer> coordTresor(String nom_tresor)
	{
		ArrayList<Integer> tab = new ArrayList<Integer>(2);
		for(int i = 0; i < 7; i++) {
			for(int j = 0; i < 7; i++) {
				if(plateau_jeu[i][j].getTresor().getNom() == nom_tresor)
				{
					tab.set(0,i);
					tab.set(1,j);
				}
			}
		}
		return tab;
	}
	
	
	/**
	 * Cette méthode nous permettra de calculer la distance entre deux points
	 * @param x1 : coordonnée x du premier point.
	 * @param y1 : coordonnée y du premier point.
	 * @param x2 : coordonnée x du deuxième point.
	 * @param y2 : coordonnée y du deuxième point.
	 * @return int
	 */
	public int distance(int x1, int y1, int x2, int y2)
	{
		return (int) Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
	}

	
	/**
	 * Cette méthode nous permettra à récupérer les cases voisines de la case IA
	 * si le chemin est ouvert
	 * @param x : coordonnée x d'une case
	 * @param y : coordonnée y d'une case
	 * @return un boolean[] ou la valeur à l'intérieur correspond à un passage ouvert ou non
	 */
	public boolean[] getCasesVoisines(int x, int y){
		boolean casesOuvertes[] = new boolean[4];
		casesOuvertes[0] = false; //case en-haut ouverte vers le bas donc l'IA va en haut
		casesOuvertes[1] = false; //case à droite ouverte vers la gauche donc l'IA va à droite 
		casesOuvertes[2] = false; //case en-bas ouverte vers le haut donc l'IA va en bas
		casesOuvertes[3] = false; //case à gauche ouverte vers la droite donc l'IA va à gauche
		
		if(x == 0){
			if(plateau_jeu[x][y].getOuvert()[2] && plateau_jeu[x+1][y].getOuvert()[0]){
				casesOuvertes[2] = true;
			}
		}
		else if (x == 6){
			if(plateau_jeu[x][y].getOuvert()[0] && plateau_jeu[x-1][y].getOuvert()[2]){
				casesOuvertes[0] = true;
			}
		}
		else{
			if(plateau_jeu[x][y].getOuvert()[2] && plateau_jeu[x+1][y].getOuvert()[0]){
				casesOuvertes[2] = true;
			}
			
			if(plateau_jeu[x][y].getOuvert()[0] && plateau_jeu[x-1][y].getOuvert()[2]){
				casesOuvertes[0] = true;
			}
		}
		
		if(y == 0){
			if(plateau_jeu[x][y].getOuvert()[1] && plateau_jeu[x][y+1].getOuvert()[3]){
				casesOuvertes[1] = true;
			}
		}
		else if (y == 6){
			if(plateau_jeu[x][y].getOuvert()[3] && plateau_jeu[x][y-1].getOuvert()[1]){
				casesOuvertes[3] = true;
			}
		}
		else{
			if(plateau_jeu[x][y].getOuvert()[1] && plateau_jeu[x][y+1].getOuvert()[3]){
				casesOuvertes[1] = true;
			}
			
			if(plateau_jeu[x][y].getOuvert()[3] && plateau_jeu[x][y-1].getOuvert()[1]){
				casesOuvertes[3] = true;
			}
		}
		return casesOuvertes;		
	}
	 
	/**
	 * Cette méthode nous permettra à marquer à true tous les chemins ouverts possibles
	 * pour le retour à la case départ
	 * @param i : valeur semblable à la coordonnée x
	 * @param j : valeur semblable à la coordonnée y
	 */
	public void cheminRetour(int i, int j) {
		if (!estDejaVu(i,j)) dejaVu[i][j] = true;
		
		boolean casesOuvertes[] = this.getCasesVoisines(i,j);
		if(casesOuvertes[0]) 
		{
			this.cheminRetour(i-1,j);
		}
		if(casesOuvertes[1]) 
		{
			this.cheminRetour(i,j+1);
		}
		if(casesOuvertes[2]) 
		{
			this.cheminRetour(i+1,j);
		}
		if(casesOuvertes[3]) 
		{
			this.cheminRetour(i,j-1);
		}
	} 
	
		
	/**
	 * Cette méthode nous permettra à marquer à true les cases non visitées
	 * puis vérifie si l'IA est sur le trésor sinon on continue à parcourir 
	 * les cases voisines avec chemins ouverts
	 * @param i : valeur semblable à la coordonnée x
	 * @param j : valeur semblable à la coordonnée y 
	 */
	public void prochaineDirection(int i, int j) {
		if(!estDejaVu(i,j)) {
			dejaVu[i][j] = true;
			
			if(plateau_jeu[i][j].getTresor().getNom() == this.getCartes().peek().getNom()) {
				this.enleveSommet();
				this.initDejaVu();
				this.deplacer(i,j);
			}
			else {
				boolean casesOuvertes[] = this.getCasesVoisines(i,j);
				if(casesOuvertes[0] && this.estCoordValide(i-1,j)) 
				{
					this.prochaineDirection(i-1,j);
				}
				if(casesOuvertes[1] && this.estCoordValide(i,j+1)) 
				{
					this.prochaineDirection(i,j+1);
				}
				if(casesOuvertes[2] && this.estCoordValide(i+1,j)) 
				{
					this.prochaineDirection(i+1,j);
				}
				if(casesOuvertes[3] && this.estCoordValide(i,j-1)) 
				{
					this.prochaineDirection(i,j-1);
				}
			}
		}
	}
	
	
	/**
	 * Cette méthode permettra à l'IA de vérifier s'il a encore des cartes trésor
	 * à chercher sinon il cherche l'itinéraire pour rentrer dans sa case départ.
	 * @return  boolean permettant de savoir si l'itinéraire est possible ou non.
	 */
	public boolean go() {
		
		boolean drapeau = false;
		ArrayList<Integer> tab = new ArrayList<Integer>(2);
		if(!this.getCartes().empty()) {
			this.prochaineDirection(this.getPos_x(), this.getPos_y());
			tab = this.coordTresor(this.getCartes().peek().getNom());
			tab = this.casePlusProche(tab.get(0),tab.get(1));
			if(!tab.isEmpty()) {
				this.deplacer(tab.get(0), tab.get(1));
				this.initDejaVu();
				drapeau = true;
			}
		}
		else {
			this.cheminRetour(this.getPos_x(), this.getPos_y());
			tab = this.casePlusProche();
			if(!tab.isEmpty()) {	 //soit il arrive à la case départ soit dans la case la plus proche de la case départ
				this.deplacer(tab.get(0), tab.get(1));
				this.initDejaVu();
				drapeau = true;
			} 
		}
		return drapeau;
	}
}