package deathbyquestion;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainMenuScene menu = new MainMenuScene(primaryStage);

        primaryStage.setTitle("Death By Question");
        primaryStage.setScene(menu.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
