package deathbyquestion;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResultScene {
    private Scene scene;

    public ResultScene(Stage stage, int score, int quizsize) {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("result-background");

        Label result = new Label("Skor kamu: " + score + " dari " + quizsize);
        result.getStyleClass().add("score-result");
        Button restartButton = new Button(" ");
        restartButton.setPrefSize(100, 40);
        restartButton.getStyleClass().add("restart-button");
        restartButton.setOnAction(e -> {
        	MainMenuScene MainMenu = new MainMenuScene(stage);
            stage.setScene(MainMenu.getScene());
        });

        layout.getChildren().addAll(result, restartButton);
        this.scene = new Scene(layout, 400, 300);
        scene.getStylesheets().add(
                getClass().getResource("/assets/css/style.css").toExternalForm()
        );
    }

    public Scene getScene() {
        return scene;
    }
}
