package deathbyquestion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        QuizScene quizScene = new QuizScene(primaryStage);
        primaryStage.setTitle("Death By Question");
        primaryStage.setScene(quizScene.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
