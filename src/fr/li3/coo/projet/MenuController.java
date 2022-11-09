package fr.li3.coo.projet;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Contrôleur de la partie vue du menu principal.
 * @author Martin Springett
 */

public class MenuController {

    @FXML
    void onJoinGame(MouseEvent event) {
    	Platform.runLater(()->{
    		try {
				new MainGame().start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    }

    @FXML
    void onQuitGame(MouseEvent event) {
    	Platform.exit();

    }

}
