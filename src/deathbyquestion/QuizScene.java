package deathbyquestion;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class QuizScene {
    private Stage stage;
    private Scene scene;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private List<Question> questions;
    private LifeSystem lifeSystem;

    // ADDED → Label hati
    private HBox heartBox;

    // ADDED → Label Level
    private Label levelLabel;

    public QuizScene(Stage stage) {
        this.stage = stage;
        this.questions = QuestionData.getQuestions();

        lifeSystem = new LifeSystem(3);

        this.scene = createScene();
    }

    private Scene createScene() {

        // ----------------------------------------------------------
        // ADDED → TOP HUD (LEVEL – LOGO – HEARTS)
        // ----------------------------------------------------------
        // LEVEL Label
        levelLabel = new Label("LEVEL " + (currentQuestionIndex + 1));
        levelLabel.getStyleClass().add("level-label");

        // Logo Image
        ImageView logoView = new ImageView(new Image(
                getClass().getResource("/assets/img/logo.png").toExternalForm()
        ));
        logoView.setFitWidth(150);
        logoView.setPreserveRatio(true);

        // HEARTS
        heartBox = new HBox(5);
        heartBox.setAlignment(Pos.CENTER);
        updateHearts(); // generate hearts sesuai jumlah nyawa

        // gabungkan LEVEL – LOGO – HEARTS
        HBox topHUD = new HBox(50, levelLabel, logoView, heartBox);
        topHUD.setAlignment(Pos.CENTER);
        // ----------------------------------------------------------

        VBox layout = new VBox(25);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("quiz-background");

        // ----------------------------------------------------------
        // Asli → Question + RadioButton Options
        // ----------------------------------------------------------
        Label questionLabel = new Label();
        questionLabel.getStyleClass().add("question-text");

        ToggleGroup group = new ToggleGroup();
        RadioButton[] options = new RadioButton[4];
        for (int i = 0; i < 4; i++) {
            options[i] = new RadioButton();
            options[i].setToggleGroup(group);
            options[i].getStyleClass().add("answer-button");
        }

        Button nextButton = new Button("Next");
        nextButton.getStyleClass().add("next-button");

        nextButton.setOnAction(e -> {
            RadioButton selected = (RadioButton) group.getSelectedToggle();
            if (selected != null) {
                int selectedIndex = -1;
                for (int i = 0; i < 4; i++) {
                    if (options[i] == selected) selectedIndex = i;
                }

                if (selectedIndex == questions.get(currentQuestionIndex).getCorrectIndex()) {
                    score++;
                } else {
                    lifeSystem.loseLife(1);
                    updateHearts(); // ADDED → update hati UI

                    if (lifeSystem.isDead()) {
                        System.out.println("Died!!");
                        return;
                    }
                }

                currentQuestionIndex++;

                if (currentQuestionIndex < questions.size()) {
                    levelLabel.setText("LEVEL " + (currentQuestionIndex + 1)); // ADDED update level
                    showQuestion(questionLabel, options);
                    group.selectToggle(null);
                } else {
                    ResultScene resultScene = new ResultScene(stage, score, questions.size());
                    stage.setScene(resultScene.getScene());
                }
            }
        });

        // ----------------------------------------------------------
        // ADDED → EXIT BUTTON
        // ----------------------------------------------------------
        Button exitButton = new Button("exit");
        exitButton.getStyleClass().add("exit-button");
        exitButton.setOnAction(e -> stage.close()); 
        // ----------------------------------------------------------

        layout.getChildren().addAll(
                topHUD,
                questionLabel,
                options[0], options[1], options[2], options[3],
                nextButton,
                exitButton // ADDED
        );

        showQuestion(questionLabel, options);

        Scene scene = new Scene(layout, 900, 500);
        scene.getStylesheets().add(
                getClass().getResource("/assets/css/style.css").toExternalForm()
        );

        return scene;
    }

    // ----------------------------------------------------------
    // ADDED → Fungsi untuk update tampilan hati
    // ----------------------------------------------------------
    private void updateHearts() {
        heartBox.getChildren().clear();
        for (int i = 0; i < lifeSystem.getLife(); i++) {
            ImageView heart = new ImageView(new Image(
                    getClass().getResource("/assets/img/heart.png").toExternalForm()
            ));
            heart.setFitWidth(35);
            heart.setPreserveRatio(true);
            heartBox.getChildren().add(heart);
        }
    }

    private void showQuestion(Label label, RadioButton[] options) {
        Question q = questions.get(currentQuestionIndex);
        label.setText(q.getQuestion());
        String[] opts = q.getOptions();
        for (int i = 0; i < 4; i++) {
            options[i].setText(opts[i]);
        }
    }

    public Scene getScene() {
        return scene;
    }
}
