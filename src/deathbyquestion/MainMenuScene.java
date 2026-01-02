package deathbyquestion; 

// Import class JavaFX yang dibutuhkan
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

    private Stage stage; // Stage utama aplikasi
    private Scene scene; // Scene menu utama

    // Konstruktor MainMenuScene
    public MainMenuScene(Stage stage) {
        this.stage = stage;
        this.scene = createScene(); 

        // Memainkan musik khusus menu utama
        MusicManager.getInstance().playSceneMusic(
                MusicManager.SceneType.MAIN_MENU
        );
    }

    // Method untuk membuat tampilan menu utama
    private Scene createScene() {

        // VBox sebagai layout utama (susun vertikal)
        VBox mainLayout = new VBox(50); 
        mainLayout.setAlignment(Pos.CENTER); 
        mainLayout.setPadding(new Insets(40)); 

        // Membuat background image
        BackgroundImage bgImage = new BackgroundImage(
                new Image(
                        getClass()
                        .getResource("/assets/img/bg2.png")
                        .toExternalForm()
                ),
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

        // Menetapkan background ke layout
        mainLayout.setBackground(new Background(bgImage));

        mainLayout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #6EC1F3, #020617);"
        );

        // Membuat logo game
        ImageView logo = new ImageView(
                new Image(
                        getClass()
                        .getResource("/assets/img/logobaru.png")
                        .toExternalForm()
                )
        );
        logo.setFitWidth(500);       
        logo.setPreserveRatio(true); 

        Button startButton = createImageButton(
                "/assets/img/start2.png", 
                250, 
                60
        );

        Button achievementButton = createImageButton(
                "/assets/img/achiev.png", 
                150, 
                40
        );

        Button exitButton = createImageButton(
                "/assets/img/exit.png", 
                80, 
                20
        );

        // Aksi saat tombol Start 
        startButton.setOnAction(e -> {
            ThemeSelectionScene themeScene =
                    new ThemeSelectionScene(stage);
            stage.setScene(themeScene.getScene());
        });

        // Aksi saat tombol Exit 
        exitButton.setOnAction(e -> {
            MusicManager.getInstance().stop(); 
            stage.close(); 
        });

        // Menambahkan semua elemen ke layout
        mainLayout.getChildren().addAll(
                logo,
                startButton,
                achievementButton,
                exitButton
        );

        // Membungkus layout dengan StackPane
        return new Scene(
                new StackPane(mainLayout),
                Constants.SCENE_WIDTH,  
                Constants.SCENE_HEIGHT  
        );
    }

    // Method untuk membuat button berbasis gambar
    private Button createImageButton(String imgPath, double w, double h) {

        Button btn = new Button();
        btn.setPrefSize(w, h); 

        // Styling tombol menggunakan gambar
        btn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-image: url('" +
                getClass().getResource(imgPath).toExternalForm() +
                "');" +
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

        // Saat mouse keluar dari tombol
        btn.setOnMouseExited(e -> {
            btn.setEffect(null); 
            btn.setScaleX(1);    
            btn.setScaleY(1);
        });

        return btn;
    }

    // Getter untuk mengambil scene
    public Scene getScene() {
        return scene;
    }
}
