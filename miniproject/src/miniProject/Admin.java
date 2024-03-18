package miniProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Admin {
    private Connection connection;
    Scanner scanner = new Scanner(System.in);
    // Establish database connection
    public Admin() {
        try {
            String databaseUrl = "jdbc:sqlserver://localhost:1433;databaseName=Quiz;user=Jayaraman;password=Jaya@987654321;encrypt=true;trustServerCertificate=true";
            connection = DriverManager.getConnection(databaseUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load questions from the database
    public List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT TOP 2 * FROM questions ORDER BY NEWID()");
            while (resultSet.next()) {
                String questionText = resultSet.getString("question_text");
                String optionsString = resultSet.getString("options");
                List<String> options = List.of(optionsString.split(","));
                String correctOption = resultSet.getString("correct_option");
                Question question = new Question(questionText, options, correctOption);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    // Add a question to the database
    public void addQuestion(Question question) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO questions (question_text, options, correct_option) VALUES (?, ?, ?)");
            statement.setString(1, question.getQuestionText());
            statement.setString(2, String.join(",", question.getOptions()));
            statement.setString(3, question.getcorrectAnswer());
            statement.executeUpdate();
            System.out.println("Added new question");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove a question from the database
    public void removeQuestion() {
        try {
        	Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery("SELECT question_text FROM questions");
            int i = 1;
            Map<Integer, String> questionNumberAnswer = new HashMap<>();
            while (resultSet.next()) {
            	String questionText = resultSet.getString("question_text");
                System.out.println(i + ". " + questionText);
                questionNumberAnswer.put(Integer.valueOf(i), questionText);
                i++;
            }
            System.out.println("Enter the question number to delete: ");
            int deletionChoice = scanner.nextInt();
            
            String questionText = questionNumberAnswer.get(deletionChoice);

            PreparedStatement statement = connection.prepareStatement("DELETE FROM questions WHERE question_text = ?");
            statement.setString(1, questionText);
            statement.executeUpdate();
            System.out.println("Removed question");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Close database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
