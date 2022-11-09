		---	PROJET LE LABYRINTHE	---

Auteurs :
	- Théo Gayant
	- Nguyen Viet

#############################################################################
Executer le programme :
 
	- Soit avec le .jar --> $ ./runfx build --> Sert a crée le .jar
						  --> $ ./runfx run --> Executer le programme
	
	- Soit via eclipse -> on va dans la classe main et on l'éxécute

Guide d'utilisation :

	- Le plateau du jeu sur la gauche, avec les flèches d'insertions de la carte en jaune. Quand on clique sur une flèche jaune cela insère la carte à placer, limité à un ajout par tour et par joueur
	- La carte à placer se situe en haut à droite, on peut la faire pivoter grâce au bouton situer juste en dessous
	- Le bouton suivant permet de passer au tour suivant
	- Le tableau des objectifs de chaque joueurs se situe à droite au milieu, il permet d'indiquer à chaque joueur le trésor qu'il doit trouvé
	- Le pad, se situe en bas à droite, on y voit le joueur actif, et lors du clique sur les flèches cela permet de déplacer le joueur dans la direction choisit si cela est possible

	+ Le joueur doit obligatoirement poser la carte avant de pouvoir se déplacer ou bien passer son tour
	+ Si le joueur passe sur son trésor, il est alors stopé et on passe au tour suivant
	+ Si le joueur sort du terrain il est téléporté à son spawn ou bien à l'opposé

############################################################################

Fonctionnalité implémenté :

	- Toute la classe Cartes_laby.java --> Gestion d'une carte labyrinthes / Sens / Forme / Tourner la carte / Changer sa position / Gerer et obtenir les chemins ouvert selon le sens et la forme de la carte
	- Toute la classe Jeu.java --> Gestion du jeu / Initialisation du plateau de jeu / Mélange des cartes à placer, des trésors / Distribution des trésors aux joueurs et sur les cartes / Gérer l'insertion d'une carte par le joueur sur le plateau, décalage de la ligne/colonne de carte / Retour a la case départ ou opposé si le joueur sort du plateau / Gestion du tour des joueurs / Vérification avant de pouvoir se déplacer, il faut que le joueur ai placé la carte avant de pouvoir se déplacer / Vérification si le joueur est sur son trésor...
	- Toute la classe MyViewController.java (= au controller du jeu) --> Gestion du controller du jeu via la vue, si le joueur clique sur un certain bouton on fait telle action
	- La classe Sens.java et Forme.java --> Enum qui permet de différencier le sens et a la forme des cartes.
	- Quelques méthode de la classe Joueur.java
	- la classe MainGame
	- L'intégralité de la partie Vue (sauf le menu) --> création de la vue grâce à scene builder, plateau de jeu, les boutons, labels etc..
	- Un peu de documentation (juste sur les classes et méthodes que j'ai crée)





############################################################################

 ![Alt text](./diagramme_classe.png?raw=true "Title")
