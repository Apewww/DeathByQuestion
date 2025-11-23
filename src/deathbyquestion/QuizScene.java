package deathbyquestion;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
    private Label lifeLabel;
    
    
    public QuizScene(Stage stage) {
        this.stage = stage;
        this.questions = QuestionData.getQuestions();
        
        lifeSystem = new LifeSystem(3);
        lifeLabel = new Label("Life : " + lifeSystem.getLife());
        lifeLabel.getStyleClass().add("life-label");
        
        this.scene = createScene();
    }
    



    private Scene createScene() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        VBox topHUD = new VBox(lifeLabel);
        topHUD.setAlignment(Pos.TOP_CENTER);

        layout.getChildren().add(topHUD);
        
        Label questionLabel = new Label();
        ToggleGroup group = new ToggleGroup();
        RadioButton[] options = new RadioButton[4];
        for (int i = 0; i < 4; i++) {
            options[i] = new RadioButton();
            options[i].setToggleGroup(group);
        }

        Button nextButton = new Button("Next");
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
                    lifeLabel.setText("Life : " + lifeSystem.getLife());

                    if (lifeSystem.isDead()) {
                        // pindah ke Result / Game Over Scene
                        //ResultScene result = new ResultScene(stage, score);
                        //stage.setScene(result.getScene());
                    	System.out.println("Died!!");
                        return;
                    }
                }

                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    showQuestion(questionLabel, options);
                    group.selectToggle(null);
                } else {
                    ResultScene resultScene = new ResultScene(stage, score, questions.size());
                    stage.setScene(resultScene.getScene());
                }
            }
        });

        layout.getChildren().addAll(questionLabel, options[0], options[1], options[2], options[3], nextButton);
        showQuestion(questionLabel, options);

        return new Scene(layout, 400, 300);
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
