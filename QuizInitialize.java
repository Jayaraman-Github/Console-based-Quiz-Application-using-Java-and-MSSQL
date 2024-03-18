package miniProject;

import java.util.*;

public class QuizInitialize {
	public List<Question> questions;
	private Scanner scanner;

	public QuizInitialize() {
		this.questions = new ArrayList<>();
		this.scanner = new Scanner(System.in);
		questions = new Admin().loadQuestions();
	}

	public void startQuiz(String username) {
		int score = 0;
		List<Boolean> userResponses = new ArrayList<>();

		// Shuffle the questions
		Collections.shuffle(questions);
		String answer;
		List<String> answersListForSummary = new ArrayList<>();
		
		displaySummary dispSummary=new displaySummary(); 

		for (Question question : questions) {
			Map<String, String> questionDisplayMap = question.displayQuestion();
			System.out.print("Enter your answer (A, B, C, D): ");
			answer = scanner.nextLine();
			
			boolean isCorrect = question.isCorrectAnswer(questionDisplayMap.get(answer.toUpperCase()));
			userResponses.add(isCorrect); 
			answersListForSummary.add(questionDisplayMap.get(answer.toUpperCase()));
			
			if (isCorrect) {
				System.out.println("Correct!\n");
				score++;
			} else {
				System.out.println("Wrong answer.\n");
			}
		}

		System.out.println("Quiz finished! Your score: " + score + "/" + questions.size() + "\n");
//		System.out.println(userResponses);
		dispSummary.displayQuizSummary(questions, answersListForSummary);
//		displaySummary(currentUser, userResponses);
		
	}

//	private void displayQuizSummary(User currentUser, List<Boolean> userResponses) {
//		System.out.println("Quiz Summary:");
//		for (int i = 0; i < questions.size(); i++) {
//			Question question = questions.get(i);
//			System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
//			System.out.println("Your option: " );
//			System.out.println("Correct option: " + questions.get(i).getcorrectAnswer());
//			System.out.println();
//			
//		}
//	}
}
