package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * This class represents the data for a single match on the GUI.
 */
public class Subunit extends GridPane {

	private Label match, winner, team1, team2;		// Match label, winner and team names
	private Button submit;							// Submit score button
	private TextField score1, score2;				// Scores
	
	private int insets = 10;						// Insets amount
	private int vGap = 5;							// Vertical gap
	private int hGap = 5;							// Horizontal gap
	
	/**
	 * Constructor: initializes all components
	 */
	public Subunit(int number, String name1, String name2) {
		
		this.setPadding(new Insets(insets,insets,insets,insets));
		this.setVgap(vGap);
		this.setHgap(hGap);
		
		// Add match header
		match = new Label("Match: " + number);
		submit = new Button("Submit Scores");
		this.add(match, 0, 0, 2, 1);
		this.add(submit, 2, 0);
		
		// Add team 1
		team1 = new Label(name1);
		score1 = new TextField();
		score1.setPromptText("Score");
		this.add(team1, 0, 1);
		this.add(score1, 1, 1);
		
		// Add team 2
		team2 = new Label(name2);
		score2 = new TextField();
		score2.setPromptText("Score");
		this.add(team2, 0, 2);
		this.add(score2, 1, 2);
		
		// Add winner box
		winner = new Label("Pending");
		this.add(winner, 2, 1, 1, 2);
		
		/*
		 * Update score when submit button is pressed
		 * Throw an exception if an invalid integer or a tie is entered
		 */
		submit.setOnAction(event -> {
			try {
				int s1 = Integer.parseInt(score1.getText());
				int s2 = Integer.parseInt(score2.getText());
				if(s1 == s2) throw new IllegalStateException("No ties allowed in bracket.");
				winner.setText("Winner:\n" + (s1 > s2 ? team1.getText() : team2.getText()));
				
				score1.setEditable(false);
				score2.setEditable(false);
			}catch(NumberFormatException exception) {
				throw new NumberFormatException("Please enter an integer score!");
			} 
		});
	}
	
	/**
	 * Clears entered scores
	 * Assumes no winner has been declared or displayed, although this method will still function otherwise.
	 */
	public void clear() {
		score1.setPromptText("Score");
		score2.setPromptText("Score");
		
		score1.setEditable(true);
		score2.setEditable(true);
	}
	
}