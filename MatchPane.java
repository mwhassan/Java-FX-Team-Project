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
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * This class represents the data for a single match on the GUI.
 * Stores a single match and the index of that match in the bracket.
 * Also stores a reference to the main application that this class.
 */
public class MatchPane<Score extends Comparable<Score>> extends GridPane {


    /***********************************************************
     * Private Constants
     ***********************************************************/

    // GridPane specific
    private static final Integer MV_PANE_INSETS = 10; // Insets amount
    private static final Integer MV_PANE_VERT_GAP = 2; // Vertical gap
    private static final Integer MV_PANE_HORZ_GAP = 3; // Horizontal gap

    /*------
     * Team Name Information
     *-----*/

    // **Team Information**
    private static final Integer MC_TEAM_SPACING = 43;
    private static final Integer MV_TEAM_MAXWIDTH = 300;


    // lblTeam1 specific
    private static final Integer MV_TEAM1_COL_IND = 0;
    private static final Integer MV_TEAM1_COL_SPAN = 1;

    private static final Integer MV_TEAM1_ROW_IND = 0;
    private static final Integer MV_TEAM1_ROW_SPAN = 1;

    // lblTeam2 specific
    private static final Integer MV_TEAM2_COL_IND = 0;
    private static final Integer MV_TEAM2_COL_SPAN = 1;

    private static final Integer MV_TEAM2_ROW_IND = 4;
    private static final Integer MV_TEAM2_ROW_SPAN = 1;



    /*------
     * Internal match information 
     *-----*/
    private static final Integer MV_DISP_SPACING = MC_TEAM_SPACING; // number of spaces avail for

    // lblMatch specific
    private static final Integer MV_MATCH_COL_IND = 0;
    private static final Integer MV_MATCH_COL_SPAN = 2;

    private static final Integer MV_MATCH_ROW_IND = 1;
    private static final Integer MV_MATCH_ROW_SPAN = 1;

    // lblWinner (Status) specific
    private static final Integer MV_WINNER_COL_IND = 0;
    private static final Integer MV_WINNER_COL_SPAN = 2;

    private static final Integer MV_WINNER_ROW_IND = 2;
    private static final Integer MV_WINNER_ROW_SPAN = 1;

    /*------
     * Score and Submit 
     *-----*/
    private static final Double MV_SCORE_WIDTH = 73.0;
    private static final Double MV_SCORE_HEIGHT = 50.0;

    // txtScore1 specific
    private static final Integer MV_SCORE_T1_COL_IND = 2;
    private static final Integer MV_SCORE_T1_COL_SPAN = 1;

    private static final Integer MV_SCORE_T1_ROW_IND = MV_TEAM1_ROW_IND;
    private static final Integer MV_SCORE_T1_ROW_SPAN = 1;

    // txtScore2 score specific
    private static final Integer MV_SCORE_T2_COL_IND = 2;
    private static final Integer MV_SCORE_T2_COL_SPAN = 1;

    private static final Integer MV_SCORE_T2_ROW_IND = MV_TEAM2_ROW_IND;
    private static final Integer MV_SCORE_T2_ROW_SPAN = 1;

    // btnSubmit specific
    private static final Integer MV_SUBMIT_COL_IND = 2;
    private static final Integer MV_SUBMIT_COL_SPAN = 1;

    private static final Integer MV_SUBMIT_ROW_IND = 1;
    private static final Integer MV_SUBMIT_ROW_SPAN = 2;

    /*------
     * Default labels and values 
     *-----*/
    private static final Integer MV_INDENT = 3;
    private static final String MV_LBL_MATCH =
                                    String.format("%" + MV_INDENT + "s%s", "", "Match: ");
    private static final String MV_LBL_WINNER_STATUS = "   Status: ";
    private static final String MV_LBL_WINNER_RESULT = "Scores Not Submitted";
    private static final String MV_LBL_SUBMIT = "Set\nScore"; 
    
    private static final String TIE_ERROR_PROMPT = 
    	String.format("%" + MV_INDENT + "s%s", "", "No ties are allowed!");
    private static final String INVALID_INPUT_ERROR_PROMPT = 
        	String.format("%" + MV_INDENT + "s%s", "", "You must first enter two integer scores!");

    private static final Integer MV_TEAM1 = 0;
    private static final Integer MV_TEAM2 = 1;
    
    /***********************************************************
     * Private View Fields
     ***********************************************************/
    
    private int index; // Number of match in tournament
    
    private Label lblMatch, 
                  lblWinnerStatus, 
                  lblTeam1, 
                  lblTeam2; // Match label, winner and team
                                                                 // names

    private Button btnSubmit; // Submit score button

    private TextField txtScore1, 
                      txtScore2; // Scores


    private String winnerStr = String.format("%-" + MV_DISP_SPACING + "s",
                    MV_LBL_WINNER_STATUS + MV_LBL_WINNER_RESULT);

    /*******************
     * Private Control Fields
     *******************/
    private Match<Integer> match;
    private int matchNum;
    private Object caller;
    
    /*******************
     * Constructors
     *******************/

    /**
     * Constructor: initializes all components
     */
    public MatchPane(int matchNum, Match<Integer> match, Object caller) {

        
        // Set pane specific fields
        this.setPadding(new Insets(MV_PANE_INSETS, MV_PANE_INSETS, MV_PANE_INSETS, MV_PANE_INSETS));
        this.setVgap(MV_PANE_VERT_GAP);
        this.setHgap(MV_PANE_HORZ_GAP);

        this.match = match;
        this.index = matchNum;
        
        //Setup control fields
        initMatchController(matchNum, match, caller);
        
        //Setup view fields
        initMatchView();
        
        //Should not be functional until populated
        this.setDisable(true);
    }
    
    
    /*******************
     * Public Interface
     *******************/
    

    /**
     * Enables MatchPane for editing.
     */
    public void clear() {
        txtScore1.setPromptText("<score>");
        txtScore2.setPromptText("<score>");
        
        txtScore1.setEditable(true);
        txtScore2.setEditable(true);
    }
    
    /**
     * Re-populates the match labels.
     */
    public void refresh() {

        String team1Name, team2Name;
        try {
            team1Name = " " + match.getTeams()[MV_TEAM1].toString();
        } catch (NullPointerException e) {
            team1Name = "";
        }
        
        try {
            team2Name = " " + match.getTeams()[MV_TEAM2].toString();
        } catch (NullPointerException e) {
            team2Name = "";
        }
        
        lblTeam1.setText(String.format("%-" + MC_TEAM_SPACING + "s", team1Name));
        lblTeam2.setText(String.format("%-" + MC_TEAM_SPACING + "s", team2Name));

    }
    
    /*******************
     * Private Helper Classes
     *******************/
    
    private void initMatchController(int matchNum, Match<Integer> match, Object caller) {
        this.matchNum = matchNum;
        this.match = match;
        this.caller = caller;
    }

    private void initMatchView() {

        /*------
         * Team Name Information
         *-----*/

        //Get teams names

        
        String team1Name, team2Name;
        try {
            team1Name = " " + match.getTeams()[MV_TEAM1].toString();
        } catch (NullPointerException e) {
            team1Name = "";
        }
        
        try {
            team2Name = " " + match.getTeams()[MV_TEAM2].toString();
        } catch (NullPointerException e) {
            team2Name = "";
        }
        
        
        // Add team 1
        
        lblTeam1 = new Label(String.format("%-" + MC_TEAM_SPACING + "s", team1Name));
        lblTeam1.getStyleClass().add("label-team");
        lblTeam1.setMaxWidth(MV_TEAM_MAXWIDTH);
        lblTeam1.setMinWidth(MV_TEAM_MAXWIDTH);
        this.add(lblTeam1, MV_TEAM1_COL_IND, MV_TEAM1_ROW_IND, MV_TEAM1_COL_SPAN,
                        MV_TEAM1_ROW_SPAN);

        // Add team 2
        lblTeam2 = new Label(String.format("%-" + MC_TEAM_SPACING + "s", team2Name));
        lblTeam2.getStyleClass().add("label-team");
        lblTeam2.setMaxWidth(MV_TEAM_MAXWIDTH);
        lblTeam2.setMinWidth(MV_TEAM_MAXWIDTH);
        this.add(lblTeam2, MV_TEAM2_COL_IND, MV_TEAM2_ROW_IND, MV_TEAM2_COL_SPAN,
                        MV_TEAM2_ROW_SPAN);



        /*------
         * Internal match information 
         *-----*/

        // Add match header
        lblMatch = new Label(MV_LBL_MATCH + matchNum);
        lblMatch.getStyleClass().add("label-no-border");
        this.add(lblMatch, MV_MATCH_COL_IND, MV_MATCH_ROW_IND, MV_MATCH_COL_SPAN,
                        MV_MATCH_ROW_SPAN);

        // Add 'Submit Scores' button
        btnSubmit = new Button(MV_LBL_SUBMIT);
        btnSubmit.setMaxHeight(MV_SCORE_HEIGHT);
        btnSubmit.setMinHeight(MV_SCORE_HEIGHT);
        btnSubmit.setMaxWidth(MV_SCORE_WIDTH);
        btnSubmit.setMinWidth(MV_SCORE_WIDTH);
        this.add(btnSubmit, MV_SUBMIT_COL_IND, MV_SUBMIT_ROW_IND, MV_SUBMIT_COL_SPAN,
                        MV_SUBMIT_ROW_SPAN);
        btnSubmit.setOnAction(e->action_handler());

        /*------
         * Score and Submit 
         *-----*/

        // Add team 1 score
        txtScore1 = new TextField();
        txtScore1.setPromptText("<score>");
        txtScore1.setMaxWidth(MV_SCORE_WIDTH);
        txtScore1.setMinWidth(MV_SCORE_WIDTH);
        this.add(txtScore1, MV_SCORE_T1_COL_IND, MV_SCORE_T1_ROW_IND, MV_SCORE_T1_COL_SPAN,
                        MV_SCORE_T1_ROW_SPAN);
        txtScore1.setOnAction(e->action_handler());


        // Add team 2 score
        txtScore2 = new TextField();
        txtScore2.setPromptText("<score>");
        txtScore2.setMaxWidth(MV_SCORE_WIDTH);
        txtScore2.setMinWidth(MV_SCORE_WIDTH);
        this.add(txtScore2, MV_SCORE_T2_COL_IND, MV_SCORE_T2_ROW_IND, MV_SCORE_T2_COL_SPAN,
                        MV_SCORE_T2_ROW_SPAN);
        txtScore2.setOnAction(e->action_handler());

        // Add winner box
        lblWinnerStatus = new Label(winnerStr);
        lblWinnerStatus.getStyleClass().add("label-no-border");
        this.add(lblWinnerStatus, MV_WINNER_COL_IND, MV_WINNER_ROW_IND, MV_WINNER_COL_SPAN,
                        MV_WINNER_ROW_SPAN);

    }
    
    /**
     * Update score when submit button is pressed. Displays prompt if invalid scores entered.
     */
    private void action_handler() {
    	try {
    		Integer s1 = Integer.parseInt(txtScore1.getText());
    		Integer s2 = Integer.parseInt(txtScore2.getText());
    		if (s1 == s2)
    			throw new IllegalStateException("No ties allowed in bracket.");
    		winnerStr = String.format("%-" + MV_DISP_SPACING + "s", (MV_LBL_WINNER_STATUS
    				+ " Winner = "
    				+ (s1 > s2 ? lblTeam1.getText() : lblTeam2.getText()).trim()));

    		lblWinnerStatus.setText(winnerStr);

    		txtScore1.setEditable(false);
    		txtScore2.setEditable(false);

    		//Change winner view
    		Label winner = ((s1 > s2) ? lblTeam1: lblTeam2);
    		TextField winnerScore = ((s1 > s2) ? txtScore1: txtScore2);
    		winner.setId("teamWon");
    		winnerScore.setId("teamWon");
    		btnSubmit.setId("teamWon");

    		//Change looser view
    		Label loser = ((s1 > s2) ? lblTeam2 : lblTeam1);
    		TextField loserScore = ((s1 > s2) ? txtScore2: txtScore1);
    		loser.setId("teamLost");
    		loserScore.setId("teamLost");

    		match.setFinalScore(s1, s2);

    		((Main) caller).matchPaneCallBack(match, index);


    	} catch (NumberFormatException exception) {
    		lblWinnerStatus.setText(INVALID_INPUT_ERROR_PROMPT);
    	} catch (IllegalStateException exception) {
    		lblWinnerStatus.setText(TIE_ERROR_PROMPT);
    	}
    }
}