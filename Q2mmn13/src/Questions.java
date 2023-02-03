import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Questions class
public class Questions  {
	
	private static final int NUM_OF_ANSWERS = 4;
	private ArrayList<Question> questionsList;
	private int totalQuestions;
	private int currentQuestionIndex;
	

	// constructor 
	public Questions() {
		this.questionsList = readFile(); // read the file
	}
	
	// getters & setters
	public int getCurrentQuestionIndex() {
		return currentQuestionIndex;
	}


	public ArrayList<Question> getQuestionsList() {
		return questionsList;
	}


	public int getTotalQuestions() {
		return totalQuestions;
	}

	
	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	
	// read file & returns list of shuffled Questions
	public ArrayList<Question> readFile() {
		questionsList = new ArrayList<>();
		ArrayList<String> answersList = new ArrayList<String>();
		String currentQuestion = "";
		try {
			Scanner input = new Scanner(new File("TriviaFile.txt"));
			while (input.hasNext()) {
				currentQuestion = input.nextLine();
				for ( int i = 0; i < NUM_OF_ANSWERS; i++ ) {
					answersList.add(input.nextLine());
				}
				this.questionsList.add(new Question(currentQuestion, answersList));
				answersList = new ArrayList<String>();
			}
			input.close();
		} catch (IOException e) {
			System.out.println("Error: File is not readable / is MISSING");
		}
		setTotalQuestions(questionsList.size());
		Collections.shuffle(questionsList);
		return questionsList;
	}
	
	// gets a question from the list
	public String getQuestion() {
		return questionsList.get(currentQuestionIndex).getQuestion();
	}
	
	// gets the correct answer of a Question
	public String getAnswer() {
		return questionsList.get(currentQuestionIndex).getCorrectAnswer();
	}
	
	// Moves to next question in the list
	public void nextQuestion() {
		currentQuestionIndex++;
	}
	
	//Presents next questions to user
	public Question presentNextQuestion() {
		nextQuestion();
		return questionsList.get(currentQuestionIndex-1);
	}
	
	// Initialize new questions order
	public void restart() {
		currentQuestionIndex = 0;
		Collections.shuffle(questionsList);
	}
	
	
	
	
}
