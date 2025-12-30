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

        boolean perfectScore = score == quizsize;

        if (perfectScore) {
            buildPerfectScene(stage);
        } else {
            buildFailedScene(stage, score, quizsize);
        }
    }

    /*SCENE JIKA SEMUA BENAR*/
    private void buildPerfectScene(Stage stage) {

        VBox layout = new VBox(35);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #06B6D4, #0891B2);");

        ImageView goodJob = new ImageView(new Image(
                getClass().getResource("/assets/img/goodjob.png").toExternalForm()
        ));
        goodJob.setPreserveRatio(true);
        goodJob.fitWidthProperty().bind(stage.widthProperty().multiply(0.45));

        Label result = new Label("Skor kamu: 100% benar");
        result.setTextFill(Color.BLACK);
        result.setFont(Font.font(stage.getHeight() * 0.045));
        result.setStyle("-fx-font-weight: bold;");

        Button mainMenuButton = createMainMenuButton(stage);

        layout.getChildren().addAll(goodJob, result, mainMenuButton);
        scene = new Scene(layout, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
    }

    /* SCENE JIKA ADA SALAH*/
    private void buildFailedScene(Stage stage, int score, int quizsize) {

        VBox layout = new VBox(30);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #8B0000, #4B0000);");

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

        Label result = new Label("Skor kamu: " + score + " dari " + quizsize);
        result.setTextFill(Color.WHITE);
        result.setFont(Font.font(stage.getHeight() * 0.04));
        result.setStyle("-fx-font-weight: bold;");
        result.setWrapText(true);

        Button mainMenuButton = createMainMenuButton(stage);

        layout.getChildren().addAll(imageBox, result, mainMenuButton);
        scene = new Scene(layout, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
    }

    /* BUTTON MAIN MENU */
    private Button createMainMenuButton(Stage stage) {

        Image img = new Image(
                getClass().getResource("/assets/img/mainmenu.png").toExternalForm()
        );
        ImageView icon = new ImageView(img);
        icon.setPreserveRatio(true);
        icon.fitWidthProperty().bind(stage.widthProperty().multiply(0.25));

        Button button = new Button();
        button.setGraphic(icon);
        button.setStyle("-fx-background-color: transparent; -fx-padding: 5;");

        DropShadow hoverShadow = new DropShadow();
        hoverShadow.setRadius(12);
        hoverShadow.setColor(Color.web("#38BDF8"));

        button.setOnMouseEntered(e -> {
            button.setEffect(hoverShadow);
            button.setScaleX(1.05);
            button.setScaleY(1.05);
        });

        button.setOnMouseExited(e -> {
            button.setEffect(null);
            button.setScaleX(1);
            button.setScaleY(1);
        });

        button.setOnAction(e -> {
            MainMenuScene menu = new MainMenuScene(stage);
            stage.setScene(menu.getScene());
        });

        return button;
    }

    public Scene getScene() {
        return scene;
    }
}
