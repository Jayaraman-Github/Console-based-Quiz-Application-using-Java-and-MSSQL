package miniProject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
    private String questionText;
    private List<String> options;
    private String correctAnswer;

    public Question(String questionText, List<String> options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }
    public String getcorrectAnswer() {
    	
        return correctAnswer;
    }


    public boolean isCorrectAnswer(String answer) {
    	System.out.println(answer + " " + correctAnswer);
        return answer != null && answer.equalsIgnoreCase(correctAnswer);
    }

    // Displaying the question and options
    public Map<String, String> displayQuestion() {
        System.out.println(questionText);
        Map<String, String> questionDisplayMap = new HashMap<>();
        char optionLabel = 'A';
        for (String option : options) {
            System.out.println(optionLabel + ": " + option);
            questionDisplayMap.put("" + optionLabel, option);
            optionLabel++;
        }
        return questionDisplayMap;
    }
}