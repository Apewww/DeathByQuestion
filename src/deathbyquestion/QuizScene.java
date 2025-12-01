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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region; // Diperlukan untuk space lines
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.net.URL; // Diperlukan untuk pengecekan resource
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
        this.questions = questions; // hanya 10 soal dari tema yang dipilih!
        this.lifeSystem = new LifeSystem(3);
        this.scene = createScene();
    }


    private Scene createScene() {
        /*TOPHUD*/
        levelLabel = new Label("LEVEL " + (currentQuestionIndex + 1));
        levelLabel.getStyleClass().add("level-label");

        ImageView logoView = new ImageView(new Image(
                getClass().getResource("/assets/img/logo.png").toExternalForm()
        ));
        logoView.setFitWidth(150);
        logoView.setPreserveRatio(true);

        // **PERUBAHAN 1: HBox untuk heartBox dibuat lebih lebar**
        heartBox = new HBox(5);
        heartBox.setAlignment(Pos.CENTER_RIGHT);
        heartBox.setMinWidth(120); // Beri lebar minimal agar layout tidak bergeser
        updateHearts();

        // Gunakan Region sebagai 'Spacer' untuk responsif
        Region spacerLeft = new Region();
        HBox.setHgrow(spacerLeft, Priority.ALWAYS);
        Region spacerRight = new Region();
        HBox.setHgrow(spacerRight, Priority.ALWAYS);

        // HBox dengan spacer agar levelLabel di kiri dan heartBox di kanan
        HBox topHUD = new HBox(10, levelLabel, spacerLeft, logoView, spacerRight, heartBox);
        topHUD.setAlignment(Pos.CENTER_LEFT);
        topHUD.setPadding(new Insets(20, 30, 0, 30)); // Tambahkan padding untuk estetika

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
            final int index = i; // Variabel 'index' adalah effectively final
            options[i] = new RadioButton();
            options[i].setToggleGroup(group);
            options[i].getStyleClass().add("answer-button");
            
            // **PERUBAHAN 2: Tambahkan listener untuk perubahan warna opsi yang dipilih**
            options[i].selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                
                for (RadioButton rb : options) {
                    rb.getStyleClass().remove("answer-selected");
                }
                
                // Tambahkan kelas CSS ke opsi yang baru dipilih
                if (isSelected) {
                    options[index].getStyleClass().add("answer-selected"); // <<< GANTI i menjadi index
                }
            });
        }

        /*option grid 3x2 - Dibuat lebih responsif*/
        VBox col1 = new VBox(20, options[0], options[2]);
        HBox.setHgrow(col1, Priority.ALWAYS);
        col1.setAlignment(Pos.CENTER_RIGHT);

        VBox col2 = new VBox(20, options[1], options[3]);
        HBox.setHgrow(col2, Priority.ALWAYS);
        col2.setAlignment(Pos.CENTER_LEFT);

        // Grid 2 kolom utama
        HBox optionsGrid = new HBox(50, col1, col2);
        HBox.setHgrow(optionsGrid, Priority.ALWAYS);
        optionsGrid.setAlignment(Pos.CENTER);
        optionsGrid.setPadding(new Insets(0, 50, 0, 50));

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
                
                selected.getStyleClass().remove("answer-selected");

                if (selectedIndex == questions.get(currentQuestionIndex).getCorrectIndex()) {
                    score++;
                } else {
                    lifeSystem.loseLife(1);
                    updateHearts();

                    if (lifeSystem.isDead()) {
                        int finalScore = this.score;
                    	int totalQuestion = questions.size();
                        ResultScene resultScene = new ResultScene(stage, finalScore, totalQuestion);
                        stage.setScene(resultScene.getScene());
                        return;
                    }
                }

                currentQuestionIndex++;

                if (currentQuestionIndex < questions.size()) {
                    levelLabel.setText("LEVEL " + (currentQuestionIndex + 1));
                    showQuestion(questionLabel, options);
                    group.selectToggle(null);
                } else {
                	int finalScore = this.score;
                	int totalQuestion = questions.size();
                    ResultScene resultScene = new ResultScene(stage, finalScore, totalQuestion);
                    stage.setScene(resultScene.getScene());
                }
            }
        });

        /*exit*/
        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(80, 30);
        exitButton.getStyleClass().add("exit-button");
        exitButton.setOnAction(e -> stage.close());

        /*space lines*/
        VBox.setMargin(topHUD, new Insets(0)); // Hapus margin di sini karena padding sudah ada di topHUD
        VBox.setMargin(questionLabel, new Insets(0, 0, 50, 0)); // Tambahkan margin atas untuk pertanyaan
        VBox.setMargin(optionsGrid, new Insets(0, 0, 20, 0));
        VBox.setMargin(nextButton, new Insets(0, 0, 20, 0));

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

        // Gambar hati penuh
        Image fullHeart = new Image(getClass().getResource("/assets/img/lifepoint.png").toExternalForm());
        
        // Gambar hati kosong / hitam
        Image emptyHeart = new Image(getClass().getResource("/assets/img/lifeheart.png").toExternalForm());

        int currentLife = lifeSystem.getLife(); // nyawa saat ini, misal 2 dari 3

        for (int i = 0; i < MAX_LIFE; i++) {
            ImageView heartView;

            if (i < currentLife) {
                // Hati masih penuh
                heartView = new ImageView(fullHeart);
            } else {
                // Hati hilang → pakai gambar kosong/hitam
                heartView = new ImageView(emptyHeart);
            }

            heartView.setFitWidth(35);
            heartView.setPreserveRatio(true);
            heartBox.getChildren().add(heartView);
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