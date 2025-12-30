package deathbyquestion;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class QuizScene {

    private Stage stage;
    private Scene scene;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private List<Question> questions;
    private LifeSystem lifeSystem;
    private final int MAX_LIFE = 3;

    private HBox heartBox;
    private Label levelLabel;

    public QuizScene(Stage stage, List<Question> questions) {
        this.stage = stage;
        this.questions = questions;
        this.lifeSystem = new LifeSystem(MAX_LIFE);

        this.scene = createScene();

        // Putar musik quiz setelah scene dibuat
        MusicManager.getInstance().playSceneMusic(MusicManager.SceneType.QUIZ);
    }

    private Scene createScene() {
        // === LEVEL LABEL ===
        levelLabel = new Label("LEVEL " + (currentQuestionIndex + 1));
        levelLabel.setTextFill(Color.BLACK);
        levelLabel.fontProperty().bind(Bindings.createObjectBinding(
                () -> Font.font(stage.getHeight() * 0.04), stage.heightProperty()));

        // === LOGO ===
        ImageView logoView = new ImageView(new Image(
                getClass().getResource("/assets/img/logo.png").toExternalForm()
        ));
        logoView.setPreserveRatio(true);
        logoView.fitWidthProperty().bind(stage.widthProperty().multiply(0.15));

        // === HEARTS ===
        heartBox = new HBox(5);
        heartBox.setAlignment(Pos.CENTER_RIGHT);
        heartBox.setMinWidth(120);
        updateHearts();

        // === SPACER ===
        Region spacerLeft = new Region();
        HBox.setHgrow(spacerLeft, Priority.ALWAYS);
        Region spacerRight = new Region();
        HBox.setHgrow(spacerRight, Priority.ALWAYS);

        // === TOP HUD ===
        HBox topHUD = new HBox(10, levelLabel, spacerLeft, logoView, spacerRight, heartBox);
        topHUD.setAlignment(Pos.CENTER_LEFT);
        topHUD.paddingProperty().bind(Bindings.createObjectBinding(
                () -> new Insets(stage.getHeight() * 0.03, stage.getWidth() * 0.04, 0, stage.getWidth() * 0.04),
                stage.widthProperty(), stage.heightProperty()
        ));

        // === LAYOUT UTAMA ===
        VBox layout = new VBox(50);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));
        layout.setStyle("-fx-background-color: #8bb0c9;");

        // === QUESTION LABEL ===
        Label questionLabel = new Label();
        questionLabel.setTextFill(Color.WHITE);
        questionLabel.setWrapText(true);
        questionLabel.setAlignment(Pos.CENTER);
        questionLabel.fontProperty().bind(Bindings.createObjectBinding(
                () -> Font.font(stage.getHeight() * 0.03), stage.heightProperty()));

        // === OPTIONS RADIOBUTTON ===
        ToggleGroup group = new ToggleGroup();
        RadioButton[] options = new RadioButton[4];
        for (int i = 0; i < 4; i++) {
            final int index = i;
            options[i] = new RadioButton();
            options[i].setToggleGroup(group);
            options[i].setTextFill(Color.BLACK);
            options[i].setStyle("-fx-background-color: white; -fx-padding: 8 15; -fx-border-color: black; -fx-border-width: 2; -fx-background-radius: 10; -fx-border-radius: 8;");
            options[i].fontProperty().bind(Bindings.createObjectBinding(
                    () -> Font.font(stage.getHeight() * 0.02), stage.heightProperty()));

            options[i].selectedProperty().addListener((obs, oldV, newV) -> {
                for (RadioButton rb : options)
                    rb.setStyle("-fx-background-color: white; -fx-padding: 8 15; -fx-border-color: black; -fx-border-width: 2; -fx-background-radius: 10; -fx-border-radius: 8;");
                if (newV) {
                    options[index].setStyle("-fx-background-color: #5cb85c; -fx-text-fill: white; -fx-padding: 8 15; -fx-border-color: black; -fx-border-width: 2; -fx-background-radius: 10; -fx-border-radius: 8;");
                }
            });
        }

        VBox col1 = new VBox(15, options[0], options[2]);
        col1.setAlignment(Pos.CENTER_RIGHT);
        VBox col2 = new VBox(15, options[1], options[3]);
        col2.setAlignment(Pos.CENTER_LEFT);

        HBox optionsGrid = new HBox(30, col1, col2);
        optionsGrid.setAlignment(Pos.CENTER);
        optionsGrid.setPadding(new Insets(0, 50, 0, 50));

        // === NEXT BUTTON DENGAN IMAGE ===
        Image nextImg = new Image(getClass().getResource("/assets/img/next.png").toExternalForm());
        ImageView nextIcon = new ImageView(nextImg);
        nextIcon.setPreserveRatio(true);
        nextIcon.fitWidthProperty().bind(stage.widthProperty().multiply(0.07)); // 10% lebar stage

        Button nextButton = new Button();
        nextButton.setGraphic(nextIcon);
        nextButton.setStyle("-fx-background-color: transparent; -fx-padding: 5;");

        // === Hover effect Next ===
        DropShadow hoverShadow = new DropShadow();
        hoverShadow.setColor(Color.rgb(0, 0, 0, 0.6));
        hoverShadow.setRadius(10);
        hoverShadow.setSpread(0.3);

        nextButton.setOnMouseEntered(e -> {
            nextButton.setEffect(hoverShadow);
            nextButton.setScaleX(1.05);
            nextButton.setScaleY(1.05);
        });
        nextButton.setOnMouseExited(e -> {
            nextButton.setEffect(null);
            nextButton.setScaleX(1);
            nextButton.setScaleY(1);
        });

        nextButton.setOnAction(e -> {
            RadioButton selected = (RadioButton) group.getSelectedToggle();
            if (selected != null) {
                int selectedIndex = -1;
                for (int i = 0; i < 4; i++)
                    if (options[i] == selected) selectedIndex = i;
                
                System.out.println(selectedIndex);
                System.out.println(questions.get(currentQuestionIndex).getCorrectIndex());
                if (selectedIndex == questions.get(currentQuestionIndex).getCorrectIndex()) {
                    score++;
                } else {
                    lifeSystem.loseLife(1);
                    updateHearts();
                    if (lifeSystem.isDead()) {
                        MusicManager.getInstance().playSceneMusic(MusicManager.SceneType.RESULT);
                        stage.setScene(new ResultScene(stage, score, questions.size()).getScene());
                        return;
                    }
                }

                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    levelLabel.setText("LEVEL " + (currentQuestionIndex + 1));
                    showQuestion(questionLabel, options);
                    group.selectToggle(null);
                } else {
                    MusicManager.getInstance().playSceneMusic(MusicManager.SceneType.RESULT);
                    stage.setScene(new ResultScene(stage, score, questions.size()).getScene());
                }
            }
        });

        // === EXIT BUTTON DENGAN IMAGE ===
        Image exitImg = new Image(getClass().getResource("/assets/img/exit.png").toExternalForm());
        ImageView exitIcon = new ImageView(exitImg);
        exitIcon.setPreserveRatio(true);
        exitIcon.fitWidthProperty().bind(stage.widthProperty().multiply(0.05));

        Button exitButton = new Button();
        exitButton.setGraphic(exitIcon);
        exitButton.setStyle("-fx-background-color: transparent; -fx-padding: 5;");

        // === Hover effect Exit ===
        DropShadow exitShadow = new DropShadow();
        exitShadow.setColor(Color.rgb(0, 0, 0, 0.6));
        exitShadow.setRadius(10);
        exitShadow.setSpread(0.3);

        exitButton.setOnMouseEntered(e -> {
            exitButton.setEffect(exitShadow);
            exitButton.setScaleX(1.05);
            exitButton.setScaleY(1.05);
        });
        exitButton.setOnMouseExited(e -> {
            exitButton.setEffect(null);
            exitButton.setScaleX(1);
            exitButton.setScaleY(1);
        });

        exitButton.setOnAction(e -> {
            MusicManager.getInstance().playSceneMusic(MusicManager.SceneType.MAIN_MENU);
            MainMenuScene menu = new MainMenuScene(stage);
            stage.setScene(menu.getScene());
        });

        // === TAMBAHKAN SEMUA KE LAYOUT ===
        layout.getChildren().addAll(topHUD, questionLabel, optionsGrid, nextButton, exitButton);

        // Tampilkan pertanyaan pertama
        showQuestion(questionLabel, options);

        return new Scene(layout, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
    }

    private void updateHearts() {
        heartBox.getChildren().clear();
        Image fullHeart = new Image(getClass().getResource("/assets/img/lifepoint.png").toExternalForm());
        Image emptyHeart = new Image(getClass().getResource("/assets/img/lifeheart.png").toExternalForm());

        int currentLife = lifeSystem.getLife();
        for (int i = 0; i < MAX_LIFE; i++) {
            ImageView heartView = new ImageView(i < currentLife ? fullHeart : emptyHeart);
            heartView.setPreserveRatio(true);
            heartView.fitWidthProperty().bind(stage.widthProperty().multiply(0.03));
            heartBox.getChildren().add(heartView);
        }
    }

    private void showQuestion(Label label, RadioButton[] options) {
        if (questions == null || questions.isEmpty() || currentQuestionIndex >= questions.size()) {
            label.setText("Tidak ada pertanyaan");
            for (RadioButton rb : options) rb.setDisable(true);
            return;
        }
        Question q = questions.get(currentQuestionIndex);
        label.setText(q.getQuestion());
        String[] opts = q.getOptions();
        for (int i = 0; i < 4; i++) options[i].setText(opts[i]);
    }

    public Scene getScene() {
        return scene;
    }
}
