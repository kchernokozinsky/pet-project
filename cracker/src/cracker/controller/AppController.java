package cracker.controller;

import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public class AppController {
	@FXML
	LevelController levelController;
	@FXML
	WelcomeController welcomeController;
	@FXML
	SettingsController settingsController;

	public SettingsController getSettingsController() {
		return settingsController;
	}

	public LevelController getLevelController() {
		return levelController;
	}

	public WelcomeController getWelcomeController() {
		return welcomeController;
	}

	public void init() {
		settingsController.init();
		welcomeController.setLevelController(levelController);
		welcomeController.init();
		levelController.setWelcomeController(welcomeController);
		Image image = new Image("/image/window/cursor.png");
		levelController.getPane().setCursor(new ImageCursor(image));
		welcomeController.getWelcomePane().setCursor(new ImageCursor(image));

	}
}
