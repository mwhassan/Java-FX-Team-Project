////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: CS 400 Team GUI Tournament Bracket Project
//
// Program Files:
// Bracket.java
// BracketADT.java
// Main.java
// Match.java
// MatchADT.java
// MatchPane.java
// Team.java
// application.css
//
// Testing Files:
// BracketTest.java
// MatchTest.java
// ZeroTeam.txt
// OneTeam.txt
// TwoTeam.txt
// FourTeam.txt
// EightTeam.txt
// SixteenTeam.txt
// ThirtyTwoTeam.txt
//
// Course: CS 400, Spring, 2018
//
// Author - A-Team 9:
// Mostafa Wail Hassan
// Christopher Todd Hayes-Birchler - hayesbirchle@wisc.edu
// Emma He
// Bryan Jin
//
// Lecturer's Name: Deb Deppeler
// Due Date : 4/23/2018 by 10PM
//
///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import java.io.IOException;
import java.util.List;

import application.MatchADT.TeamSpot;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * The main application class for a tournament bracket program.
 * Initializes a Bracket based on an input file, given in args[0]
 * Splits the Bracket into two converging halves
 * Updates the status of each match as the user enters scores
 */
public class Main extends Application {

    /*******************
     * Private Constants
     *******************/
    //stage detail
    private static final Integer STAGE_WIDTH = 900;
    private static final Integer STAGE_HEIGHT = 500;
    //Scene
    private static final Integer HORIZONTAL_PADDING = 10;
    private static final Integer VERTICAL_PADDING = 5;
    //Display strings
    private static final String TOURNAMENT_TITLE = "Tournament Bracket";
    private static final String FILE_NOT_FOUND_TITLE = "File Not Found";
    private static final String NO_TEAMS_TITLE = "Empty Bracket";
    private static final String ONE_TEAM_TITLE = "One Team";
    private static final String FILE_NOT_FOUND_MESSAGE = "The provided file could not be loaded - exiting program.";
    private static final String NO_TEAMS_MESSAGE = "No teams and no tournament!";
    private static final String ONE_TEAM_MESSAGE = "Only one team! The winner is ";
    private static final String CHAMPION_MESSAGE = "\n\nAnd the champion is\n";
    private static final String LEADER_BOARD_INTRO = "Congratulations to our winners";
    private static final String LEADER_BOARD_FIRST = "       1st  place -  ";
    private static final String LEADER_BOARD_SECOND = "       2nd place -  ";
    private static final String LEADER_BOARD_THIRD = "       3rd place -  ";
    /*******************
     * Private View Variables
     *******************/
    private Stage stage;                    	// The stage
    private Scene scene;                    	// The scene
    //controls
    private BorderPane[] roundPanes;			// Displays teams for each round
    private VBox[] columnPanes;					// Displays each column (half-round)
    private MatchPane<Integer>[] matchPanes;	// Groups submit buttons with scores and team labels
    private Label championLabel_1;				// Label will display champion
    private Label championLabel_2;				// Label will display champion

    /*******************
     * Private Control Variables
     *******************/
    private Bracket<Integer> bracket;			// Bracket to build and display
    
    
    /*******************
     * Static methods
     *******************/
    public static void main(String[] args) {
        launch(args);
    }
    
    /*******************
     * Public Interface
     *******************/
    @Override
    public void start(Stage primaryStage) {
        try {
            initControlObjects();
        } catch (IOException exception) {
        	alert(FILE_NOT_FOUND_MESSAGE, FILE_NOT_FOUND_TITLE);
    		return;
        }
        initViewObjects(primaryStage);
    }

    private void initControlObjects() throws IOException {
        
    	// get command line arguments
        Parameters parameters = getParameters();
        List<String> params = parameters.getRaw();
        
        // set parameters
        bracket = new Bracket<Integer>(params.get(0));
    }
    
    /*******************
     * Private helper methods
     *******************/
    
    /**
     * Initializes all GUI Objects
     * 
     * @param width
     * @param height
     * @param numRounds
     */
    @SuppressWarnings("unchecked")
	private void initViewObjects (Stage stage) {
        // Parameters from bracket
    	int rounds = bracket.rounds();
    	
    	// Handle no teams
    	if(bracket.size() < 1) {
    		alert(NO_TEAMS_MESSAGE, NO_TEAMS_TITLE);
    		return;
    	}
    	// Handle one team
    	if(bracket.size() == 1) {
    		alert(ONE_TEAM_MESSAGE + bracket.getMatch(1).getTeams()[0], ONE_TEAM_TITLE);
    		return;
    	}
    	// Bracket arrays
    	roundPanes = new BorderPane[rounds + 1];						// indexing starts at 1
    	columnPanes = new VBox[2*rounds];								// indexing starts at 1
        matchPanes = new MatchPane[bracket.matches()+1];				// indexing starts at 1
        // initialize roundPanes
        for(int i = 1; i <= rounds; i++) {
        	roundPanes[i] = new BorderPane();
        	roundPanes[i].setPadding(new Insets(0,HORIZONTAL_PADDING,0,HORIZONTAL_PADDING));
        }
        // initialize columnPanes
        for(int i = 1; i < 2*rounds; i++) {
        	columnPanes[i] = new VBox(VERTICAL_PADDING);
        	columnPanes[i].setAlignment(Pos.CENTER);
        }
        // initialize match panes and build scene
        for(int i = 1; i < rounds; i++) {
        	roundPanes[i].setCenter(roundPanes[i+1]);
        	for(int slot = 1; slot <= Math.pow(2, rounds-i); slot++) {
        		int index = bracket.getMatchIndex(i, slot);
    			System.out.println("slot = " + slot);
        		//add standard pane
        		if(slot <= Math.pow(2, rounds-i-1)) {
        			matchPanes[index] = new MatchPane<Integer>
										(index, bracket.getMatch(index), this);
        			columnPanes[2*i - 1].getChildren().add(matchPanes[index]);
        			//Add buffers
        			int bufferNumber = (int) (Math.pow(2, rounds-i));
        			int numBuffers = (int) Math.pow((i-1),2)-1;
        			
        			if(i%2 == 0) numBuffers -= 1;
        			if (numBuffers <1) numBuffers = 1;
        			if (i > 1 && slot < bufferNumber/2) {
        				for (int c = 0; c < numBuffers; c++) {
        					columnPanes[2*i-1].getChildren().add(new BufferPane());
        				}
        			} 
        		}
        		else {
        			//add mirror pane
        			matchPanes[index] = new MatchPane<Integer>
    									(index, bracket.getMatch(index), this, true);
        			columnPanes[2*i].getChildren().add(matchPanes[index]);
        			
        			//Add buffers
        			int bufferNumber = (int) (Math.pow(2, rounds-i));
        			int numBuffers = (int) Math.pow((i-1),2)-1;
        			
        			if(i%2 == 0) numBuffers -= 1;
        			if (numBuffers <1) numBuffers = 1;
        			if (i > 1 && slot < bufferNumber) {
        				for (int c = 0; c < numBuffers; c++) {
        					columnPanes[2*i].getChildren().add(new BufferPane());
        				}
        			} 
        		}
        		
    			if(i == 1) matchPanes[index].setDisable(false); // Enable first-round GUI functionality
        	}
        	
        	roundPanes[i].setLeft(columnPanes[2*i - 1]);
        	roundPanes[i].setRight(columnPanes[2*i]);
        }
        
        
        // add championship match     
        matchPanes[bracket.matches()] = new ChampPane<Integer>
        	(bracket.matches(), bracket.getMatch(bracket.matches()), this);
        
        //if only one round then need to set as champ round (since not handles in callback)
        if (rounds==1) matchPanes[bracket.matches()].refreshChamp(); 
        
        columnPanes[2*rounds - 1].getChildren().add(matchPanes[bracket.matches()]);
        if(bracket.matches() == 1) matchPanes[bracket.matches()].setDisable(false);
        roundPanes[rounds].setCenter(columnPanes[2*rounds - 1]);
        
        // add champion labels
        championLabel_1 = new Label();
        championLabel_2 = new Label();
        
        championLabel_1.getStyleClass().add("mainForm");
        championLabel_2.getStyleClass().add("mainChamps");
        
        roundPanes[rounds].setTop(championLabel_1);
        roundPanes[rounds].setBottom(championLabel_2);
        
        BorderPane.setAlignment(championLabel_1, Pos.CENTER);
        BorderPane.setAlignment(championLabel_2, Pos.CENTER);
        
        HBox box = new HBox();
        box.setStyle("-fx-background-color: #383838");
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(roundPanes[1]);
        
        ScrollPane sp = new ScrollPane();
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        sp.setContent(box);
        
        scene = new Scene(sp,STAGE_WIDTH,STAGE_HEIGHT);
        scene.getStylesheets().add("/application/application.css");
        this.stage = stage;
        this.stage.setMaximized(true);
        this.stage.setScene(scene);
        this.stage.setTitle(TOURNAMENT_TITLE);
        this.stage.show();
    }
    
    private void alert(String text, String title) {
		Label msg = new Label(text);
    	msg.getStyleClass().add("mainForm");
		Button ok = new Button("OK");
		VBox display = new VBox(VERTICAL_PADDING);
		
		display.setAlignment(Pos.CENTER);
		display.getChildren().addAll(msg, ok);
		Scene scene = new Scene(display);
		scene.getStylesheets().add("/application/application.css");
		Stage alert = new Stage();
		
		alert.setWidth(STAGE_WIDTH);
		alert.setHeight(STAGE_HEIGHT);
		ok.setOnAction(e->alert.close());
		alert.setTitle(title);
		
		alert.setScene(scene);
		alert.setMaximized(true);
		alert.showAndWait();
    }
    
    /**
     * Is called from MatchPane, providing the match and match number to be processed.
     * Populates champion label at the conclusion of the tournament.
     * Adds teams to subsequent matches and enables them when both teams have been added.
     */
    public void matchPaneCallBack(Match<Integer> match, int index) {
    	//if we are at championship run this
    	if(index == bracket.matches()) {
    		
    		Team first = match.getWinner();
    		Team second = (match.getTeams()[0] == match.getWinner()?
					match.getTeams()[1] : match.getTeams()[0]);
    		
    		championLabel_1.setText(CHAMPION_MESSAGE + first);
    		
    		// if no third place
    		if(index <= 1) {
    			championLabel_2.setText(LEADER_BOARD_INTRO + "\n" +
						LEADER_BOARD_FIRST + first + "\n"+
						LEADER_BOARD_SECOND + second + "\n\n");
    			return;
    		}
    		
    		// Compute third place winner
    		Match<Integer> match1 = bracket.getMatch(index - 1);
    		Match<Integer> match2 = bracket.getMatch(index - 2);

    		Integer losingSpot1 = match1.getTeams()[0] == match1.getWinner() ? 1:0;
    		Integer losingSpot2 = match2.getTeams()[0] == match2.getWinner() ? 1:0;
    		
    		Integer score1 = match1.getFinalScores().get(losingSpot1);
    		Integer score2 = match2.getFinalScores().get(losingSpot2);

    		Team third = null;
    		if (score1 > score2) {
    		    third = match1.getTeams()[losingSpot1];
    		} else if (score1 == score2) {
    		    if (match1.getWinner().equals(match.getWinner())) {
    		        third = match1.getTeams()[losingSpot1];
    		    } else {
    		        third = match2.getTeams()[losingSpot2];
    		    }
    		} else {
    		    third = match2.getTeams()[losingSpot2];
    		}
    		
    		// print leaderboard
    		championLabel_2.setText(LEADER_BOARD_INTRO + "\n" +
    								LEADER_BOARD_FIRST + first + "\n"+
    								LEADER_BOARD_SECOND + second + "\n"+ 
    								LEADER_BOARD_THIRD + third + "\n\n");
    		return;
    	}
    	
    	//otherwise run this to enable next bracket
        int next = bracket.getNextMatch(index);
        
        //This is what needs to be updated.  We want to add the team to either spot 1
        //or spot 2 depending on where it is coming from
        TeamSpot setSpot;
        setSpot = TeamSpot.TeamOne;
        if (bracket.getMatchSlot(index)%2 == 0) {
        	setSpot = TeamSpot.TeamTwo;
        }
        
        bracket.getMatch(next).addTeam(match.getWinner(), setSpot);
        
        System.out.println("Bracket = " + bracket.matches());
        System.out.println("Index = " + index);
        
        if(index == bracket.matches() - 1) {
        	matchPanes[next].refreshChamp();
        } else {
        	matchPanes[next].refresh();
        }
        
        matchPanes[next].setDisable(false);
    }

}