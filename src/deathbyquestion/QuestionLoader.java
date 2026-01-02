package deathbyquestion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class QuestionLoader {

    // Inisialisasi library GSON
    private static final Gson gson = new Gson();

    // Method internal untuk membaca file JSO
    private static Map<String, List<Question>> loadAll() {
        try {
            // Membuka file questions.json
            InputStreamReader reader = new InputStreamReader(
                QuestionLoader.class.getResourceAsStream("/assets/questions/questions.json")
            );

            // Menentukan tipe data target
            Type type = new TypeToken<Map<String, List<Question>>>(){}.getType();
            
            return gson.fromJson(reader, type);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Question> getAlgoritmaQuestions() {
        return loadAll().get("algoritma");
    }
    
    public static List<Question> getStrukturDataQuestions() {
        return loadAll().get("struktur_data");
    }

    public static List<Question> getPemrogramanWebQuestions() {
        return loadAll().get("pemrograman_web");
    }
}