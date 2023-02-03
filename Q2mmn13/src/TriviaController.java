import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

// Graphics Class
public class TriviaController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane background;
	
    @FXML
    private Label scoreTxt;

	@FXML
	private Label questionsInput;

	@FXML
	private Button nextQBtn;

	@FXML
	private RadioButton radioBtn0;

	@FXML
	private RadioButton radioBtn1;

	@FXML
	private RadioButton radioBtn2;

	@FXML
	private RadioButton radioBtn3;

	@FXML
	private Button restartBtn;

	@FXML
	private Label scoreLB;

	@FXML
	private Text ansResult;

	@FXML
	private ToggleGroup toggleGroup;
	private Game game;
	private ArrayList<RadioButton> rBtns;
	ArrayList<String> answers;
	private Question current;
	private String userInput;
	private Alert information = new Alert(AlertType.INFORMATION);
	FunnyHeadlines headersForPopup = new FunnyHeadlines();
	private Image image;
	private ImageView imageView;

	@FXML
	// Event Handler for Next Question button variations
	void nextQuestionPressed(ActionEvent event) {
		showRadioButtons(true);
		// If the event is not an answer input from the user
		if (nextQBtn.getText() == "Next Question" || nextQBtn.getText() == "Start Trivia") {
			ansResult.setVisible(false);
			scoreLB.setVisible(true);
			restartBtn.setDisable(false);
			questionsInput.setVisible(true);
			for (int i = 0; i < rBtns.size(); i++) {
				rBtns.get(i).setDisable(false);
			}
			// Present 'END OF THE GAME' Pop-up and sum score
			if (game.isLastQuestion()) {
				setPopup("END OF THE ROAD","","Trivia Hero!  \n\nWow that was a greate game! you finished with the score of: "+ game.getScore()+"\n\n\n THANKS FOR PLAYING!");
				image = new Image("https://m.media-amazon.com/images/W/WEBP_402378-T1/images/I/71tfy7bDfQL._AC_SX679_.jpg");
				imageView = new ImageView(image);
				imageView.setFitWidth(600);
				imageView.setFitHeight(400);
				information.setGraphic(imageView);
				information.showAndWait();
				nextQBtn.setDisable(true);
			} else {
				// Game is still on
				nextQBtn.setText("Next Question");
				current = game.nextQuestion();
				loadQuestion();
			}
		} else {
			// checks the right answer & change button back to Next Question
			userAnswered(userInput);
			nextQBtn.setText("Next Question");
			scoreLB.setVisible(true);
		}
	}

	@FXML
	// Restart Trivia method
	void restartTrivia(ActionEvent event) {
		nextQBtn.setDisable(false);
		game.restartGame();
		setupGame();
		
	}

	@FXML
	// Initialize method
	void initialize() {
		game = new Game();
		setupGame();
		userInput = "";
		instructionForGame();
		information.showAndWait();
		
		
	}
	
	// Game setup method
	public void setupGame() {
		rBtns = new ArrayList<RadioButton>();
		rBtns = createToggleRadioBtns();
		showRadioButtons(false);
		scoreLB.setVisible(false);
		restartBtn.setDisable(true);
		questionsInput.setVisible(false);
		ansResult.setText("");
		nextQBtn.setText("Start Trivia");

	}
	// Game Entrance Pop-up maker
	private void instructionForGame() {
		String title = "Welcome";
		String header = "Dog Theme Trivia";
		String content = "On every question you have 4 answers to pick -> only one is correct \nAfter answering check if you are correct and move to the next one. \n \n GOOD LUCK";
		setPopup(title, header, content);
		image = new Image("https://cdn-icons-png.flaticon.com/512/616/616408.png");
		imageView = new ImageView(image);
		imageView.setFitWidth(75);
		imageView.setFitHeight(75);
		((Button)information.getDialogPane().lookupButton(ButtonType.OK)).setText("Let's Play");
		information.setGraphic(imageView);
		
	}
	
	// Creates RadioButtons list & handle selected events
	private ArrayList<RadioButton> createToggleRadioBtns() {
		toggleGroup = new ToggleGroup();
		radioBtn0.setToggleGroup(toggleGroup);
		radioBtn1.setToggleGroup(toggleGroup);
		radioBtn2.setToggleGroup(toggleGroup);
		radioBtn3.setToggleGroup(toggleGroup);
		ArrayList<RadioButton> rBtns = new ArrayList<RadioButton>();
		rBtns.add(radioBtn0);
		rBtns.add(radioBtn1);
		rBtns.add(radioBtn2);
		rBtns.add(radioBtn3);
		for (int i = 0; i < rBtns.size(); i++) {
			rBtns.get(i).setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					handlerAnswerClick(e);
				}
			});
		}
		return rBtns;
	}

	// Event handler for radio buttons
	private void handlerAnswerClick(Event event) {
		RadioButton rbn = (RadioButton) event.getSource();
		userInput = rbn.getText();
		if (rbn.isSelected()) {
			nextQBtn.setDisable(false);	
		}
		nextQBtn.setText("Confirm Answer");
		disableRadioButtons();
	}
	
	// Load new question method
	private void loadQuestion() {
		userInput = "";
		for (int i = 0; i < rBtns.size(); i++) {
			rBtns.get(i).setSelected(false);
		}
		nextQBtn.setDisable(true);
		answers = new ArrayList<String>();
		//System.out.println(answers);
		answers = current.getAnswersCollection();
		questionsInput.setText(current.getQuestion());
		radioBtn0.setText(current.getCorrectAnswer());
		//System.out.println(answers);
		answers.remove(current.getCorrectAnswer());
		//System.out.println(answers);
		radioBtn1.setText(answers.get(0));
		radioBtn2.setText(answers.get(1));
		radioBtn3.setText(answers.get(2));
		scoreLB.setText(game.getScore() + "");
	}
	
	// Method for checking & present user Answer & right Answer
	private void userAnswered(String ans) {
		if (game.isCorrectAns(ans)) {
			setPopup(headersForPopup.getRnadomFunnyHeader(headersForPopup.getPositiveHeadlines()),"Your are Correct!","You've just earned 10 points! Amazing!");
			image = new Image("https://styles.redditmedia.com/t5_5vivsz/styles/profileIcon_3qcnddkr9oi81.jpg?width=256&height=256&crop=256:256,smart&s=347ce97478351e8bd8a821e9c0dd3459ba1cfcbf");
			imageView = new ImageView(image);
			imageView.setFitWidth(150);
			imageView.setFitHeight(150);
			((Button)information.getDialogPane().lookupButton(ButtonType.OK)).setText("Continue");
			information.setGraphic(imageView);
			information.showAndWait();
		} else {
			setPopup(headersForPopup.getRnadomFunnyHeader(headersForPopup.getNegativeHeadlines()),"You are totaly wrong! ","Whoops!  The right answer is: "+current.getCorrectAnswer());
			image = new Image("https://pbs.twimg.com/profile_images/622403338470973440/qf1dGvKx_400x400.jpg");
			imageView = new ImageView(image);
			imageView.setFitWidth(150);
			imageView.setFitHeight(150);
			information.setGraphic(imageView);
			((Button)information.getDialogPane().lookupButton(ButtonType.OK)).setText("Continue");
			information.showAndWait();
		}
		scoreLB.setText(game.getScore()+"");
	}
	
	// Show / clear RadioButtons method
	private void showRadioButtons(boolean b) {
		radioBtn0.setVisible(b);
		radioBtn1.setVisible(b);
		radioBtn2.setVisible(b);
		radioBtn3.setVisible(b);
	}
	
	// Disable radioButtons method
	private void disableRadioButtons() {
		for (int i = 0; i < rBtns.size(); i++) {
			rBtns.get(i).setDisable(true);
		}
	}
	
	
	// Set Pop up content method
	private void setPopup(String title, String header, String content) {
		information.setTitle(title);
		information.setHeaderText(header);
		information.setContentText(content);
	}
}
