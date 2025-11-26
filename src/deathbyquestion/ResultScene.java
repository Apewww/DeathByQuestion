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

        Label result = new Label("Skor kamu: " + score + " dari " + quizsize);
        Button restart = new Button("Main lagi");
        restart.setOnAction(e -> {
            QuizScene quiz = new QuizScene(stage);
            stage.setScene(quiz.getScene());
        });

        layout.getChildren().addAll(result, restart);
        this.scene = new Scene(layout, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }
}
