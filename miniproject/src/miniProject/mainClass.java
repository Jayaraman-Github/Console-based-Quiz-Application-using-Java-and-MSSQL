package miniProject;

import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class mainClass {
	// private static Map<String, String> users = new HashMap<>();
	private static Scanner scanner = new Scanner(System.in);

	private static String databaseUrl = "jdbc:sqlserver://localhost:1433;databaseName=Quiz;user=Jayaraman;password=Jaya@987654321;encrypt=true;trustServerCertificate=true";

	public static void main(String[] args) {

		while (true) {
			System.out.println("Welcome to the Online Quiz Application");
			System.out.println("1. Signup");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			try {

				switch (choice) {
				case 1:
					signUp();
					break;
				case 2:
					login();
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("Invalid choice. Please enter 1, 2, or 3.");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	private static void signUp() throws SQLException {
		System.out.println("Signup:");
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
//		User newUser = new User(username, password);

		Connection conn = DriverManager.getConnection(databaseUrl);
		String signupSelectQuery = "select username from attendee where username = '" + username + "'";
		Statement statement = conn.createStatement();

		ResultSet resultSet = statement.executeQuery(signupSelectQuery);
//		System.out.println(rs.next());

		if (resultSet.next()) {
			System.out.println("This username is already taken. Please try another.");
		} else {
			String signupInsertQuery = "insert into attendee(username, password) values ('" + username + "', '"
					+ password + "')";
			Statement istatement = conn.createStatement();

			istatement.executeUpdate(signupInsertQuery);

			System.out.println("Signup successful. Please login to continue.");
		}
	}

	private static void login() throws SQLException {
		System.out.println("Login:");
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		Connection conn = DriverManager.getConnection(databaseUrl);
		String storedUsername = null;
		String storedPassword = null;
		// String query = "select username, password, isAdmin from attendee where
		// username = '" + username + "'";
		String loginSelectQuery = "SELECT username,password FROM attendee WHERE username = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(loginSelectQuery);
		preparedStatement.setString(1, username);
		//preparedStatement.setString(2, password);

		ResultSet resultSet2 = preparedStatement.executeQuery();
//		System.out.println(rs2.next());
		try {
			resultSet2.next();
			storedUsername = resultSet2.getString("username");
			System.out.println(storedUsername);
			storedPassword = resultSet2.getString("password");
			System.out.println(storedPassword);
		} catch (Exception e) {
			System.out.println("User not found");
			return;
		}
//		if() {
//			System.out.println("User not found");
//			return;
//		}
		
			if (storedUsername.equals("admin")) {
				if (storedPassword != null && storedPassword.equals(password)) {

					Admin admin = new Admin();

					while (true) {
						System.out.println("Welcome Admin\n");
						System.out.println("1. Add question");
						System.out.println("2. Remove question");
						System.out.println("3. Exit");
						System.out.print("Enter your choice: ");

						int choice = scanner.nextInt();
						scanner.nextLine();

						switch (choice) {
						case 1:
							System.out.println("Enter the question.\n");
							String question = scanner.nextLine();

							System.out.println("Enter the options separated by comma.\n");
							String options = scanner.nextLine();
							List<String> optionsList = Arrays.asList(options.split(","));

							System.out.println("Enter the correct option.\n");
							String correctOption = scanner.nextLine();

							Question questionObject = new Question(question, optionsList, correctOption);

							admin.addQuestion(questionObject);
							break;
						case 2:
							admin.removeQuestion();
							break;
						case 3:
							return;
						default:
							System.out.println("Invalid choice. Please enter 1, 2, or 3.");
						}
					}
				} else {
					System.out.println("Invalid Password");
					return;
				}
			}

			if (storedUsername.equals(username) && (storedPassword != null && storedPassword.equals(password))) {
				System.out.println("Login successful. Starting the quiz..");
				QuizInitialize quizInitialize = new QuizInitialize();
				quizInitialize.startQuiz(username);
			} else if(storedUsername.equals(username) && (storedPassword != null && !storedPassword.equals(password))){
				System.out.println("Login failed. Invalid password.");
			}
			else {
				System.out.println("User not found");
			}
			
		}
	
	}

//		String storedPassword = rs2.getString("password");
//		if (storedPassword != null && storedPassword.equals(password)) {
//			System.out.println("Login successful. Starting the quiz..");
//			QuizInitialize quizInitialize = new QuizInitialize();
//			quizInitialize.startQuiz(username);
//		} else {
//			System.out.println("Login failed. Invalid password.");
//		}
