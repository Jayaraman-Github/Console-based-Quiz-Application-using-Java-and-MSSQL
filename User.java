package miniProject;

import java.util.HashMap;
import java.util.Map;

public class User {
    String username;
    String password;
    boolean isAdmin;
    // A map to hold quiz scores. The key is the quiz identifier, and the value is the score.
//    private Map<String, Integer> scores;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        isAdmin = false;
//        this.scores = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to add or update a quiz score for the user
//    public void addQuizScore(String quizId, int score) {
//        scores.put(quizId, score);
//    }
//
//    // Method to get a specific quiz score
//    public Integer getQuizScore(String quizId) {
//        return scores.get(quizId);
//    }
//
//    // Method to get all quiz scores
//    public Map<String, Integer> getAllQuizScores() {
//        return scores;
//    }
    
    // Method to display user scores
//    public void displayScores() {
//        if (scores.isEmpty()) {
//            System.out.println(username + " has no quiz scores yet.");
//            return;
//        }
//
//        System.out.println(username + "'s Quiz Scores:");
//        scores.forEach((quizId, score) -> System.out.println("Quiz ID: " + quizId + ", Score: " + score));
//    }
}

