package deathbyquestion;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResultScene {
    private Scene scene;

    public ResultScene(Stage stage, int score, int quizsize) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("result-background");

        // ================================
        //   BAGIAN 3 GAMBAR TAMBAHAN
        // ================================
        HBox imageBox = new HBox(60);
        imageBox.setAlignment(Pos.CENTER);

        ImageView leftRIP = new ImageView(new Image(
                getClass().getResource("/assets/img/rip1.png").toExternalForm()
        ));
        leftRIP.setFitWidth(180);
        leftRIP.setPreserveRatio(true);

        ImageView skull = new ImageView(new Image(
                getClass().getResource("/assets/img/skul.png").toExternalForm()
        ));
        skull.setFitWidth(150);
        skull.setPreserveRatio(true);

        ImageView rightRIP = new ImageView(new Image(
                getClass().getResource("/assets/img/rip2.png").toExternalForm()
        ));
        rightRIP.setFitWidth(180);
        rightRIP.setPreserveRatio(true);

        imageBox.getChildren().addAll(leftRIP, skull, rightRIP);

        // ================================
        //          LABEL SKOR
        // ================================
        Label result = new Label("Skor kamu: " + score + " dari " + quizsize);
        result.getStyleClass().add("score-result");

        // ================================
        //         BUTTON MAIN MENU
        // ================================
        Button restartButton = new Button(" ");
        restartButton.setPrefSize(250, 90);
        restartButton.getStyleClass().add("restart-button");

        restartButton.setOnAction(e -> {
            MainMenuScene menu = new MainMenuScene(stage);
            stage.setScene(menu.getScene());
        });

        layout.getChildren().addAll(imageBox, result, restartButton);

        this.scene = new Scene(layout, 600, 400);
        this.scene.getStylesheets().add(
                getClass().getResource("/assets/css/style.css").toExternalForm()
        );
    }

    public Scene getScene() {
        return scene;
    }
}
