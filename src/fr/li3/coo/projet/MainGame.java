package fr.li3.coo.projet;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class executes the JavaFX application.
 *
 * @version 0.1.0
 */
public final class MainGame extends Application {

	/*
	 * (non-Javadoc)
	 *
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MyView.fxml"));
		Parent root = fxmlLoader.load();

		Jeu j = new Jeu();

		MyViewController controller = fxmlLoader.getController();
		controller.addJeu(j);
		// Do something with controller.

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/plateau.css").toExternalForm());
		stage.setTitle("Le Labyrinthe");
		stage.setScene(scene);
		stage.show();

		ArrayList<Joueur> joueurs = j.getJoueurs();
		j.setLeJoueur(joueurs.get(0));

		controller.majAll();
	}
}
