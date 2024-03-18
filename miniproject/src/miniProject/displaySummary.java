package miniProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class displaySummary {

	public static void displayQuizSummary(List<Question> questions, List<String> options) {
		System.out.println("Quiz Summary:");
		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
			System.out.println("Your answer: " + options.get(i));
			System.out.println("Correct answer: " + questions.get(i).getcorrectAnswer());
			System.out.println();

		}
	}
}
