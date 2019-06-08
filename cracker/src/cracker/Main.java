package cracker;

import cracker.controller.AppController;
import cracker.controller.GameController;
import cracker.logic.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/app.fxml"));
        AnchorPane root = fxmlLoader.load();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 1024, 868));
        AppController appController = fxmlLoader.getController();
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        Game game = new Game();
        game.init();
        appController.getGameController().setStage(primaryStage);
        appController.getGameController().init(game);
        appController.getGameController().setBinding();
        game.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
