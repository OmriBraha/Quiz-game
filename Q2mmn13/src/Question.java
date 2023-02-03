import java.util.ArrayList;
import java.util.Collections;

// Single Question class
public class Question {
	
	
	private String question;
	private String correctAnswer;
	private ArrayList <String> answersCollection;
	
	// Constructor
	public Question(String question, ArrayList <String> allAnswers) {
		this.question = question;
		this.correctAnswer = allAnswers.get(0); // Right answer in the first place of the list
		this.answersCollection = allAnswers;
	}

	// getters
	public String getQuestion() {
		return question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	// Shuffle Answers
	public ArrayList <String> getAnswersCollection() {
		Collections.shuffle(answersCollection);
		return answersCollection;	 
	}

}
