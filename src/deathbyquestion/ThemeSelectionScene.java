package deathbyquestion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class ThemeSelectionScene {

    private Stage stage;
    private Scene scene;

    public ThemeSelectionScene(Stage stage) {
        this.stage = stage;
        this.scene = createScene();

        // Mainkan musik sama dengan main menu
        MusicManager.getInstance().playSceneMusic(MusicManager.SceneType.MAIN_MENU);
    }

    private Scene createScene() {
        Label title = new Label("Pilih Mode Game");
        title.setFont(Font.font(28));
        title.setTextFill(Color.WHITE);

        Label subtitle = new Label("Pilih kategori sebelum mulai bermain");
        subtitle.setFont(Font.font(14));
        subtitle.setTextFill(Color.LIGHTGRAY);
        VBox.setMargin(subtitle, new Insets(0, 0, 30, 0));

        HBox cardContainer = new HBox(30);
        cardContainer.setAlignment(Pos.CENTER);

        VBox algoCard = createCard(
                "Algoritma",
                "Fokus ke logika pemecahan masalah dan langkah terstruktur",
                () -> startQuiz(QuestionData.getAlgoritmaQuestions())
        );

        VBox sdCard = createCard(
                "Struktur Data",
                "Pelajari stack, queue, tree, graph lewat kuis",
                () -> startQuiz(QuestionData.getStrukturDataQuestions())
        );

        VBox webCard = createCard(
                "Pemrograman Web",
                "Front-end dan Back-end dasar",
                () -> startQuiz(QuestionData.getPemrogramanWebQuestions())
        );

        cardContainer.getChildren().addAll(algoCard, sdCard, webCard);

        VBox layout = new VBox(15, title, subtitle, cardContainer);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #6EC1F3, #020617);");

        scene = new Scene(layout, 900, 500);
        return scene;
    }

    private VBox createCard(String titleText, String descText, Runnable action) {
        Label title = new Label(titleText);
        title.setFont(Font.font(18));
        title.setTextFill(Color.WHITE);

        Label desc = new Label(descText);
        desc.setWrapText(true);
        desc.setFont(Font.font(13));
        desc.setTextFill(Color.LIGHTGRAY);

        VBox card = new VBox(12, title, desc);
        card.setAlignment(Pos.TOP_LEFT);
        card.setPadding(new Insets(20));
        card.setPrefSize(240, 170);
        card.setStyle("-fx-background-color: rgba(255,255,255,0.05);" +
                      "-fx-background-radius: 16;" +
                      "-fx-border-radius: 16;" +
                      "-fx-border-color: rgba(255,255,255,0.15);");

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.AQUA);

        card.setOnMouseEntered(e -> {
            card.setStyle("-fx-background-color: rgba(255,255,255,0.1);" +
                          "-fx-background-radius: 16;" +
                          "-fx-border-radius: 16;" +
                          "-fx-border-color: #38BDF8;");
            card.setTranslateY(-6);
            card.setEffect(shadow);
        });

        card.setOnMouseExited(e -> {
            card.setStyle("-fx-background-color: rgba(255,255,255,0.05);" +
                          "-fx-background-radius: 16;" +
                          "-fx-border-radius: 16;" +
                          "-fx-border-color: rgba(255,255,255,0.15);");
            card.setTranslateY(0);
            card.setEffect(null);
        });

        card.setOnMouseClicked(e -> action.run());
        return card;
    }

    private void startQuiz(List<Question> questions) {
        if (questions == null || questions.isEmpty()) {
            System.out.println("Error: Questions kosong!");
            return;
        }
        MusicManager.getInstance().stop(); // hentikan musik menu sebelum masuk Quiz
        QuizScene quiz = new QuizScene(stage, questions);
        stage.setScene(quiz.getScene());
    }

    public Scene getScene() {
        return scene;
    }
}
