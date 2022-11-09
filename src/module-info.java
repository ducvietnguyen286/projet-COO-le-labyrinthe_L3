module fr.li3.coo.projet {

	// Le(s) paquetage(s) de votre projet.
	// Ecrivez une ligne de cette forme pour chacun de vos paquetages.

	exports fr.li3.coo.projet;

	// Le paquetage contenant vos contrôleurs doit s'ouvrir à JavaFX.
	// Cela permet à JavaFX d'accéder réflexivement à ces classes.

	opens fr.li3.coo.projet to javafx.fxml;

	// Les modules de JavaFX requis pour votre interface graphique.

	requires javafx.controls;

	requires javafx.fxml;

	requires transitive javafx.graphics;

}
