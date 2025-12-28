package deathbyquestion;

import javafx.beans.binding.Bindings;
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

        // === LAYOUT UTAMA ===
        VBox layout = new VBox(30);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #8B0000, #4B0000);");

        // === IMAGE RIP & SKULL ===
        HBox imageBox = new HBox(40);
        imageBox.setAlignment(Pos.CENTER);

        ImageView leftRIP = new ImageView(new Image(
                getClass().getResource("/assets/img/rip1.png").toExternalForm()
        ));
        leftRIP.setPreserveRatio(true);
        leftRIP.fitWidthProperty().bind(stage.widthProperty().multiply(0.15));

        ImageView skull = new ImageView(new Image(
                getClass().getResource("/assets/img/skul.png").toExternalForm()
        ));
        skull.setPreserveRatio(true);
        skull.fitWidthProperty().bind(stage.widthProperty().multiply(0.12));

        ImageView rightRIP = new ImageView(new Image(
                getClass().getResource("/assets/img/rip2.png").toExternalForm()
        ));
        rightRIP.setPreserveRatio(true);
        rightRIP.fitWidthProperty().bind(stage.widthProperty().multiply(0.15));

        imageBox.getChildren().addAll(leftRIP, skull, rightRIP);

        // === LABEL SKOR ===
        Label result = new Label("Skor kamu: " + score + " dari " + quizsize);
        result.setTextFill(Color.WHITE);
        result.setFont(Font.font(stage.getHeight() * 0.04));
        result.setStyle("-fx-font-weight: bold;");
        result.setWrapText(true);
        result.setAlignment(Pos.CENTER);

        // === BUTTON MAIN MENU DENGAN IMAGE ===
        Image mainMenuImg = new Image(getClass().getResource("/assets/img/mainmenu.png").toExternalForm());
        ImageView mainMenuIcon = new ImageView(mainMenuImg);
        mainMenuIcon.setPreserveRatio(true);
        mainMenuIcon.fitWidthProperty().bind(stage.widthProperty().multiply(0.25)); // 25% lebar stage

        Button restartButton = new Button();
        restartButton.setGraphic(mainMenuIcon);
        restartButton.setStyle("-fx-background-color: transparent; -fx-padding: 5;");

        // === Hover effect tombol ===
        DropShadow hoverShadow = new DropShadow();
        hoverShadow.setRadius(12);
        hoverShadow.setColor(Color.web("#38BDF8"));

        restartButton.setOnMouseEntered(e -> {
            restartButton.setEffect(hoverShadow);
            restartButton.setScaleX(1.05);
            restartButton.setScaleY(1.05);
        });

        restartButton.setOnMouseExited(e -> {
            restartButton.setEffect(null);
            restartButton.setScaleX(1);
            restartButton.setScaleY(1);
        });

        restartButton.setOnAction(e -> {
            MainMenuScene menu = new MainMenuScene(stage);
            stage.setScene(menu.getScene());
        });

        // === TAMBAHKAN SEMUA KE LAYOUT UTAMA ===
        layout.getChildren().addAll(imageBox, result, restartButton);

        scene = new Scene(layout, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
    }

    public Scene getScene() {
        return scene;
    }
}
