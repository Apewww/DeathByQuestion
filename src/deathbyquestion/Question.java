package deathbyquestion;

import java.util.List;

public class Question {

    private String question;
    private List<String> options;
    private int answer;

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options.toArray(new String[0]);
    }

    public int getCorrectIndex() {
        return answer;
    }
}