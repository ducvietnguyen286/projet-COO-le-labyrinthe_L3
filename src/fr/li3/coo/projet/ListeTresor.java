package fr.li3.coo.projet;

/**
 * Enumeration listant tous les tresors du jeu ainsi que leurs attributs.
 * 
 * @author Duc Viet NGUYEN
 *
 */

public enum ListeTresor {
	ANNEAU("anneau d’or", false), ARAIGNEE("araignee", true), ARMURE("armure", false),
	CARTE_TRESOR("carte au tresor", false), CHANDELIER("chandelier", false), CHAUVE_SOURIS("chauve souris", true),
	CHOUETTE("chouette", true), CLEFS("clefs", false), COFFRE_TRESOR("coffre au tresor", false),
	COURONNE("couronne", false), CRANE("crane", false), DINOSAURE("dinosaure", true), EMERAUDE("emeraude", false),
	EPEE("epee", false), FANTOME("fantome", true), FEE("fee", true), GENIE("genie", false), GRIMOIRE("grimoire", false),
	RAT("rat", true), SAC_PIECES("sac de pieces", false), SALAMANDRE("salamandre", true), SCARABEE("scarabee", true),
	SPHYNX_TETE("sphynx a tete de mort", false), TROLL("troll", false);

	private String nom;
	private boolean vivant;

	/**
	 * Constructeur de l'enumération listant tous les tresors et leurs attributs.
	 * 
	 * @param nom    : le nom du tresor. Chaine de caractère.
	 * @param vivant : booleen indiquant si le tresor est un être vivant ou non.
	 */
	ListeTresor(String nom, boolean vivant) {
		this.nom = nom;
		this.vivant = vivant;
	}

	/**
	 * Accesseur en lecture du nom du trésor.
	 * 
	 * @return une chaine de caractères qui est le nom du trésor.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Accesseur en lecture permettant de savoir si le tresor est un être vivant.
	 * 
	 * @return un booleen qui permet de savoir si le tresor vit ou pas.
	 */
	public boolean isVivant() {
		return vivant;
	}
}
