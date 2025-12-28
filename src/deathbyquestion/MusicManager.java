package deathbyquestion;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class MusicManager {

    public enum SceneType { MAIN_MENU, QUIZ, RESULT }

    private static MusicManager instance;
    private MediaPlayer mediaPlayer;
    private SceneType currentScene;

    private MusicManager() { }

    public static MusicManager getInstance() {
        if (instance == null) instance = new MusicManager();
        return instance;
    }

    public void playSceneMusic(SceneType sceneType) {
        if (currentScene == sceneType) return; // musik sama, tidak perlu ganti

        stop(); // hentikan musik lama

        String fileName = switch (sceneType) {
            case MAIN_MENU -> "/assets/sound/mainsound.mp3";
            case QUIZ -> "/assets/sound/quizsound.mp3";
            case RESULT -> "/assets/sound/resultsound.mp3";
        };

        try {
            URL resource = getClass().getResource(fileName);
            if (resource != null) {
                Media media = new Media(resource.toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setVolume(0.3);
                mediaPlayer.play();
                currentScene = sceneType;
            } else {
                System.out.println("Audio file not found: " + fileName);
            }
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
}
