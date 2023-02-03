
// Game Logics class
public class Game {

	private int score;
	private Questions questionStock;
	private Question currentQuestion;
	private static final int CORRECT_ANS = 10;
	private static final int WRONG_ANS = 5;

	// Constructor
	public Game() {
		questionStock = new Questions();
	}
	
	// Get current Score
	public int getScore() {
		return score;
	}

	
	// Returning next question
	public Question nextQuestion() {
		return currentQuestion = questionStock.presentNextQuestion();
	}
	
	// Checks if the answer is correct
	public boolean isCorrectAns(String userAnswer) {
		if (userAnswer.equals(currentQuestion.getCorrectAnswer())) {
			score += CORRECT_ANS;
			return true;
		}
		score -= WRONG_ANS;
		return false;
	}
	
	// checks if game is ENED
	public boolean isLastQuestion() {
		if ( questionStock.getCurrentQuestionIndex() == questionStock.getTotalQuestions() ) {
			return true;
		}
		return false;
	}

	// Restart game
	public void restartGame() {
		questionStock.restart();
		score = 0;
	}
	

	
	
}
	
		
		


