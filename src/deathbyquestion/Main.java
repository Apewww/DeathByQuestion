package deathbyquestion;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().addAll(
                new javafx.scene.image.Image(
                    getClass().getResource("/assets/img/icon16.png").toExternalForm()
                ),
                new javafx.scene.image.Image(
                        getClass().getResource("/assets/img/icon32.png").toExternalForm()
                    ),
                new javafx.scene.image.Image(
                        getClass().getResource("/assets/img/icon64.png").toExternalForm()
                    ),
                new javafx.scene.image.Image(
                        getClass().getResource("/assets/img/icon128.png").toExternalForm()
                    ),
                new javafx.scene.image.Image(
                        getClass().getResource("/assets/img/icon256.png").toExternalForm()
                    )
            );
        
        MainMenuScene menu = new MainMenuScene(primaryStage);

        primaryStage.setTitle("Death By Question");
        primaryStage.setScene(menu.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
