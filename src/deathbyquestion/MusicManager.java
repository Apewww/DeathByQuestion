package deathbyquestion;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class MusicManager {

    // Definisi tipe scene untuk menentukan musik mana yang harus diputar
    public enum SceneType { MAIN_MENU, QUIZ, RESULT }

    private static MusicManager instance;// Variabel statis untuk mnyimpan instance
    private MediaPlayer mediaPlayer;
    private SceneType currentScene;// Menyimpan status scene yang musiknya sedang diputar

    // Constructor 
    private MusicManager() { }

    // Method untuk mendapatkan instance tunggal dari MusicManager
    public static MusicManager getInstance() {
        if (instance == null) instance = new MusicManager();
        return instance;
    }

    // Method utama untuk mengganti dan memutar musik sesuai dengan scene yang aktif
    public void playSceneMusic(SceneType sceneType) {
        if (currentScene == sceneType) return; // musik sama, tidak perlu ganti
        stop(); 

        // Menentukan path file audio 
        String fileName = switch (sceneType) {
            case MAIN_MENU -> "/assets/sound/mainsound.mp3";
            case QUIZ -> "/assets/sound/quizsound.mp3";
            case RESULT -> "/assets/sound/resultsound.mp3";
        };

        try {
            // Mengambil resource file audio dari path yang ditentukan
            URL resource = getClass().getResource(fileName);
            if (resource != null) {
                // Inisialisasi media dan player
                Media media = new Media(resource.toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                // Mengatur agar musik diputar terus menerus (looping)
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setVolume(0.08);
                mediaPlayer.play();
                // Memperbarui status scene
                currentScene = sceneType;
            } else {
                System.out.println("Audio file not found: " + fileName);
            }
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
    }

    // Method untuk menghentikan pemutaran musik
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    // Method untuk menjeda (pause) musik yang sedang berjalan
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
}