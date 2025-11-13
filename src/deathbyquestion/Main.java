package deathbyquestion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Label utama
        Label label = new Label("Selamat datang di Aplikasi Quiz!");

        // Tombol sederhana
        Button button = new Button("Mulai Quiz");
        button.setOnAction(e -> label.setText("Quiz dimulai! 🚀"));

        // Tata letak
        VBox layout = new VBox(15);
        layout.getChildren().addAll(label, button);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Scene dan Stage
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("DeathByQuestion");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

