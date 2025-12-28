package deathbyquestion;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainMenuScene {

    private Stage stage;
    private Scene scene;

    public MainMenuScene(Stage stage) {
        this.stage = stage;
        this.scene = createScene();

        // Mainkan musik menu
        MusicManager.getInstance().playSceneMusic(MusicManager.SceneType.MAIN_MENU);
    }

    private Scene createScene() {
        VBox mainLayout = new VBox(30);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(40));

        BackgroundImage bgImage = new BackgroundImage(
                new Image(getClass().getResource("/assets/img/bg2.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        BackgroundSize.AUTO,
                        BackgroundSize.AUTO,
                        false,
                        false,
                        true,
                        true
                )
        );
        mainLayout.setBackground(new Background(bgImage));
        mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #6EC1F3, #020617);");

        ImageView logo = new ImageView(new Image(
                getClass().getResource("/assets/img/logobaru.png").toExternalForm()
        ));
        logo.setFitWidth(500);
        logo.setPreserveRatio(true);

        Button startButton = createImageButton("/assets/img/start2.png", 250, 60);
        Button achievementButton = createImageButton("/assets/img/achiev.png", 150, 40);
        Button exitButton = createImageButton("/assets/img/exit.png", 80, 20);
        Button resultButton = new Button("result");
        resultButton.setPrefSize(80, 80);

        startButton.setOnAction(e -> {
            ThemeSelectionScene themeScene = new ThemeSelectionScene(stage);
            stage.setScene(themeScene.getScene());
        });

        resultButton.setOnAction(e -> {
            MusicManager.getInstance().stop(); // stop sebelum masuk result
            ResultScene resultScene = new ResultScene(stage, 0, 0);
            stage.setScene(resultScene.getScene());
        });

        exitButton.setOnAction(e -> {
            MusicManager.getInstance().stop(); // stop saat keluar
            stage.close();
        });

        mainLayout.getChildren().addAll(logo, startButton, achievementButton, exitButton, resultButton);
        return new Scene(new StackPane(mainLayout), Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
    }

    private Button createImageButton(String imgPath, double w, double h) {
        Button btn = new Button();
        btn.setPrefSize(w, h);
        btn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-image: url('" + getClass().getResource(imgPath).toExternalForm() + "');" +
                "-fx-background-size: contain;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center;"
        );

        DropShadow hoverShadow = new DropShadow();
        hoverShadow.setColor(Color.rgb(0, 0, 0, 0.6));
        hoverShadow.setRadius(15);
        hoverShadow.setSpread(0.4);

        btn.setOnMouseEntered(e -> {
            btn.setEffect(hoverShadow);
            btn.setScaleX(1.05);
            btn.setScaleY(1.05);
        });
        btn.setOnMouseExited(e -> {
            btn.setEffect(null);
            btn.setScaleX(1);
            btn.setScaleY(1);
        });
        return btn;
    }

    public Scene getScene() {
        return scene;
    }
}
