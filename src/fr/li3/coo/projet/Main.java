package fr.li3.coo.projet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Initialise le menu principal du jeu.
 * @author Martin Springett
 */

public class Main extends Application {
	

	/*
	 * (non-Javadoc)
	 *
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Menu.fxml"));
		Parent root = fxmlLoader.load();

		MenuController controller = fxmlLoader.getController();
		
		// Do something with controller.

		Scene scene = new Scene(root);
		stage.setTitle("Le Labyrinthe");
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Executes the application.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		launch(args);

	}

}
