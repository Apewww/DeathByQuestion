package deathbyquestion;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuScene {
    private Stage stage;
    private Scene scene;

    public MainMenuScene(Stage stage) {
        this.stage = stage;
        this.scene = createScene();
    }

    private Scene createScene() {
        VBox layout = new VBox(20);
        layout.getStyleClass().add("menu-background");
        layout.setAlignment(Pos.CENTER);
        
        Label title = new Label("DEATH BY QUESTION");
        title.getStyleClass().add("game-title");
        
        Button startButton = new Button("Start Game");
        startButton.getStyleClass().add("game-button");
        Button exitButton = new Button("Exit");
        exitButton.getStyleClass().add("game-button");

        // Tombol Start → masuk ke QuizScene
        startButton.setOnAction(e -> {
            QuizScene quizScene = new QuizScene(stage);
            stage.setScene(quizScene.getScene());
        });

        // Tombol Exit → keluar aplikasi
        exitButton.setOnAction(e -> {
            stage.close();
        });

        layout.getChildren().addAll(title, startButton, exitButton);

        return new Scene(layout, 400, 300);
    }

    public Scene getScene() {
    	scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        return scene;
    }
}
