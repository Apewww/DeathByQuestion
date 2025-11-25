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
import javafx.geometry.Insets;

import java.util.List;

public class QuizScene {
    private Stage stage;
    private Scene scene;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private List<Question> questions;
    private LifeSystem lifeSystem;

    private HBox heartBox;
    private Label levelLabel;

    public QuizScene(Stage stage) {
        this.stage = stage;
        this.questions = QuestionData.getQuestions();
        lifeSystem = new LifeSystem(3);
        this.scene = createScene();
    }

    private Scene createScene() {
        /*TOPHUD*/
        levelLabel = new Label("LEVEL " + (currentQuestionIndex + 1));
        levelLabel.getStyleClass().add("level-label");

        ImageView logoView = new ImageView(new Image(
                getClass().getResource("/assets/img/logo.png").toExternalForm()
        ));
        logoView.setFitWidth(150); // ukuran kecil
        logoView.setPreserveRatio(true);

        heartBox = new HBox(5);
        heartBox.setAlignment(Pos.CENTER);
        updateHearts();

        HBox topHUD = new HBox(100, levelLabel, logoView, heartBox);
        topHUD.setAlignment(Pos.CENTER);

        /*Layout Utama*/
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(0));
        layout.getStyleClass().add("quiz-background");


        /*Question*/
        Label questionLabel = new Label();
        questionLabel.getStyleClass().add("question-text");

        /*Answer*/
        ToggleGroup group = new ToggleGroup();
        RadioButton[] options = new RadioButton[4];

        for (int i = 0; i < 4; i++) {
            options[i] = new RadioButton();
            options[i].setToggleGroup(group);
            options[i].getStyleClass().add("answer-button");
        }

        /*option grid 3x2*/
     VBox col1 = new VBox(20, options[0], options[2]);
     col1.setAlignment(Pos.CENTER_RIGHT);

     VBox col2 = new VBox();
     col2.setMinWidth(5);   
     col2.setAlignment(Pos.CENTER);

     VBox col3 = new VBox(20, options[1], options[3]);
     col3.setAlignment(Pos.CENTER_LEFT);

     // Grid 3 kolom
     HBox optionsGrid = new HBox(10, col1, col2, col3);
     optionsGrid.setAlignment(Pos.CENTER);

        /*next*/
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
                    updateHearts();

                    if (lifeSystem.isDead()) {
                        System.out.println("Game Over!");
                        return;
                    }
                }

                currentQuestionIndex++;

                if (currentQuestionIndex < questions.size()) {
                    levelLabel.setText("LEVEL " + (currentQuestionIndex + 1));
                    showQuestion(questionLabel, options);
                    group.selectToggle(null);
                } else {
                    ResultScene resultScene = new ResultScene(stage, score, questions.size());
                    stage.setScene(resultScene.getScene());
                }
            }
        });

        /*exit*/
        Button exitButton = new Button("Exit");
        exitButton.getStyleClass().add("exit-button");
        exitButton.setOnAction(e -> stage.close());

        /*space lines*/
        VBox.setMargin(topHUD, new Insets(0, 0, 60, 0));          // jarak HUD → pertanyaan
        VBox.setMargin(questionLabel, new Insets(0, 0, 35, 0));   // jarak pertanyaan → jawaban
        VBox.setMargin(optionsGrid, new Insets(0, 0, 30, 0));     // jarak jawaban → next
        VBox.setMargin(nextButton, new Insets(0, 0, 20, 0));      // jarak next → exit

        /*tambah ke layout*/
        layout.getChildren().addAll(
                topHUD,
                questionLabel,
                optionsGrid,
                nextButton,
                exitButton
        );

        showQuestion(questionLabel, options);

        Scene scene = new Scene(layout, 900, 500);
        scene.getStylesheets().add(
                getClass().getResource("/assets/css/style.css").toExternalForm()
        );

        return scene;
    }

    /*heart*/
    private void updateHearts() {
        heartBox.getChildren().clear();
        for (int i = 0; i < lifeSystem.getLife(); i++) {
            ImageView heart = new ImageView(new Image(
                    getClass().getResource("/assets/img/nyawa.png").toExternalForm()
            ));
            heart.setFitWidth(35);
            heart.setPreserveRatio(true);
            heartBox.getChildren().add(heart);
        }
    }

    /*menampilkan question*/
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
