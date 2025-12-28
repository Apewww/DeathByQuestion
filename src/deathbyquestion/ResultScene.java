package deathbyquestion;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ResultScene {
    private Scene scene;

    public ResultScene(Stage stage, int score, int quizsize) {

        /* ================= BACKGROUND (gantinya .result-background) ================= */
        VBox layout = new VBox(25);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #020617, #000000);"
        );

        /* ================= 3 GAMBAR ================= */
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

        /* ================= LABEL SKOR (gantinya .score-result) ================= */
        Label result = new Label("Skor kamu: " + score + " dari " + quizsize);
        result.setFont(Font.font(22));
        result.setTextFill(Color.WHITE);
        result.setStyle("-fx-font-weight: bold;");

        /* ================= BUTTON MAIN MENU (gantinya .restart-button) ================= */
        Button restartButton = new Button("MAIN MENU");
        restartButton.setPrefSize(250, 90);
        restartButton.setFont(Font.font(16));
        restartButton.setTextFill(Color.WHITE);

        restartButton.setStyle(
                "-fx-background-color: rgba(255,255,255,0.08);" +
                "-fx-background-radius: 18;" +
                "-fx-border-radius: 18;" +
                "-fx-border-color: rgba(255,255,255,0.3);" +
                "-fx-cursor: hand;"
        );

        DropShadow hoverShadow = new DropShadow();
        hoverShadow.setRadius(12);
        hoverShadow.setColor(Color.web("#38BDF8"));

        restartButton.setOnMouseEntered(e -> {
            restartButton.setStyle(
                    "-fx-background-color: rgba(255,255,255,0.15);" +
                    "-fx-background-radius: 18;" +
                    "-fx-border-radius: 18;" +
                    "-fx-border-color: #38BDF8;" +
                    "-fx-cursor: hand;"
            );
            restartButton.setEffect(hoverShadow);
        });

        restartButton.setOnMouseExited(e -> {
            restartButton.setStyle(
                    "-fx-background-color: rgba(255,255,255,0.08);" +
                    "-fx-background-radius: 18;" +
                    "-fx-border-radius: 18;" +
                    "-fx-border-color: rgba(255,255,255,0.3);" +
                    "-fx-cursor: hand;"
            );
            restartButton.setEffect(null);
        });

        restartButton.setOnAction(e -> {
            MainMenuScene menu = new MainMenuScene(stage);
            stage.setScene(menu.getScene());
        });

        layout.getChildren().addAll(imageBox, result, restartButton);

        scene = new Scene(layout, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
    }

    public Scene getScene() {
        return scene;
    }
}