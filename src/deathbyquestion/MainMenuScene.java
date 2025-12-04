package deathbyquestion;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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

        VBox mainLayout = new VBox(30);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(40));
        mainLayout.getStyleClass().add("menu-background");

        // Logo
        ImageView logo = new ImageView(
                new Image(getClass().getResource("/assets/img/logobaru.png").toExternalForm())
        );
        logo.setFitWidth(500);
        logo.setPreserveRatio(true);

        // Buttons
        Button startButton = new Button("");
        Button achievementButton = new Button("	");
        Button exitButton = new Button("");
        Button resultButton = new Button("result");

        startButton.setPrefSize(250, 60);
        startButton.getStyleClass().add("game-button");
        achievementButton.setPrefSize(150, 40);
        achievementButton.getStyleClass().add("achievement-button");
        exitButton.setPrefSize(80, 20);
        exitButton.getStyleClass().add("exit-button");
        resultButton.setPrefSize(80, 80);
        resultButton.getStyleClass().add("cepat-result");

        startButton.setOnAction(e -> {
            ThemeSelectionScene themeScene = new ThemeSelectionScene(stage);
            stage.setScene(themeScene.getScene());
        });

        
        resultButton.setOnAction(e -> {
        	ResultScene resultScene = new ResultScene(stage, 0, 0);
        	stage.setScene(resultScene.getScene());
        });

        exitButton.setOnAction(e -> stage.close());

        mainLayout.getChildren().addAll(logo, startButton, achievementButton, exitButton, resultButton);

        StackPane root = new StackPane(mainLayout);

        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("/assets/css/style.css").toExternalForm());

        return scene;
    }

    public Scene getScene() {
        return this.scene;
    }
}
