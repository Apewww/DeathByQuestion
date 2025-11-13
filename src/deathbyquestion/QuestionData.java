package deathbyquestion;

import java.util.ArrayList;
import java.util.List;

public class QuestionData {
    public static List<Question> getQuestions() {
        List<Question> list = new ArrayList<>();

        list.add(new Question(
            "Apa ibu kota Indonesia?",
            new String[]{"Jakarta", "Bandung", "Surabaya", "Medan"},
            0
        ));

        list.add(new Question(
            "2 + 2 x 2 = ?",
            new String[]{"6", "8", "4", "2"},
            0
        ));

        list.add(new Question(
            "Bahasa pemrograman yang digunakan untuk Android?",
            new String[]{"Python", "Java", "C#", "PHP"},
            1
        ));

        return list;
    }
}
