import java.util.ArrayList;
import java.util.Collections;

/*
 * FunnyHeadLines class
 * I've added this class for the funny headlines text in the Pop-ups
 * Using shuffle, every time the user is Right/wrong the graphics class will take a random element from one of the lists here
 * The random element will be presented as the title of the Pop-up when a user confirm's the answer
 * For example -> if The use is right, the text 'Mann you got potential!' will be presented
 */
public class FunnyHeadlines {
	
	private ArrayList<String> positiveHeadlines = new ArrayList<String>();
	private ArrayList<String> negativeHeadlines = new ArrayList<String>();
	
	// Constructor
	public FunnyHeadlines() {
		this.positiveHeadlines = addPosLines();
		this.negativeHeadlines = addNegLines();

	}
	// getters
	public ArrayList<String> getPositiveHeadlines() {
		return positiveHeadlines;
	}

	public ArrayList<String> getNegativeHeadlines() {
		return negativeHeadlines;
	}
	// Add positive text for right answers
	private ArrayList<String>  addPosLines() {
		positiveHeadlines.add("Mann you got potential!");
		positiveHeadlines.add("Woof! that was insanely CORRECT!");
		positiveHeadlines.add("Impressive! Good job!");
		positiveHeadlines.add("I am just AMAZED");
		return positiveHeadlines;
	}
	
	// Add positive text for wrong answers
	private ArrayList<String> addNegLines() {
		negativeHeadlines.add("Somebody did not eat breakfast today");
		negativeHeadlines.add("SOOOOO.... disappointing");
		negativeHeadlines.add("I think somebody likes to LOSE");
		negativeHeadlines.add("-5 Points to you, Amigo");
		return negativeHeadlines;
	}
	
	// shuffle lists and generate a random element
	public String getRnadomFunnyHeader(ArrayList<String> s) {
		Collections.shuffle(s);
		String randomLine = "";
		for ( int i = 0; i < s.size(); i++ ) {
			randomLine = s.get(i); 
		}
		return randomLine;
	}
	
	
	


	
}
