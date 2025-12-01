package deathbyquestion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ThemeSelectionScene {

    private Stage stage;
    private Scene scene;

    public ThemeSelectionScene(Stage stage) {
        this.stage = stage;
        this.scene = createScene();
    }

    private Scene createScene() {

        Label title = new Label("Pilih Mode Game");
        title.getStyleClass().add("selection-title");

        Label subtitle = new Label("Pilih kategori sebelum mulai bermain");
        subtitle.getStyleClass().add("selection-subtitle");

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
        layout.getStyleClass().add("selection-background");

        Scene scene = new Scene(layout, 900, 500);
        scene.getStylesheets().add(
                getClass().getResource("/assets/css/style.css").toExternalForm()
        );

        return scene;
    }

    private VBox createCard(String titleText, String descText, Runnable action) {

        Label title = new Label(titleText);
        title.getStyleClass().add("card-title");

        Label desc = new Label(descText);
        desc.setWrapText(true);
        desc.getStyleClass().add("card-desc");

        VBox card = new VBox(12, title, desc);
        card.setAlignment(Pos.TOP_LEFT);
        card.setPadding(new Insets(20));
        card.setPrefSize(240, 170);
        card.getStyleClass().add("mode-card");

        card.setOnMouseClicked(e -> action.run());

        return card;
    }

    private void startQuiz(List<Question> questions) {
        QuizScene quiz = new QuizScene(stage, questions);
        stage.setScene(quiz.getScene());
    }

    public Scene getScene() {
        return scene;
    }
}
