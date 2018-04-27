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
import javafx.scene.control.Control;
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
	// GridPane as a whole
    private static final Integer MV_PANE_INSETS = 10; // Insets amount
    private static final Integer MV_PANE_VERT_GAP = 2; // Vertical gap
    private static final Integer MV_PANE_HORZ_GAP = 5; // Horizontal gap
    /*------
     * Pane layout
     *-----*/
    //Column Width details
    private static final Integer MV_COL_A_WIDTH = 55;
    private static final Integer MV_COL_B_WIDTH = 135;
    private static final Integer MV_COL_C_WIDTH = 65;
    //Row Height Details
    private static final Integer MV_ROW_1_HEIGHT = 25;
    private static final Integer MV_ROW_2_HEIGHT = 17;
    private static final Integer MV_ROW_3_HEIGHT = 30;
    private static final Integer MV_ROW_4_HEIGHT = 25;
    //Indices for control setup array
    private static final Integer MV_COL_IND = 0;
    private static final Integer MV_COL_SPAN = 1;
    private static final Integer MV_ROW_IND = 2;
    private static final Integer MV_ROW_SPAN = 3;
    //Row information (should not change)
    private static final int MV_ROW_1 = 0;
    private static final int MV_ROW_2 = 1;
    private static final int MV_ROW_3 = 2;
    private static final int MV_ROW_4 = 3;
    //Takes up single x or double x (x = row or column)
    private static final Integer MV_SINGLE = 1;
    private static final Integer MV_DOUBLE = 2;
    /*------
     * Additional Constants
     *-----*/
    //Remaining constants
    private static final Integer MV_TEAM_INDENT = 3;
    private static final Integer MV_INDENT = 3;
    //Team one or team 2 (used in some bracket and match functions
    private static final Integer MV_TEAM1 = 0;
    private static final Integer MV_TEAM2 = 1;
    /*------
     * View Labels
     *-----*/
    //Header Options
    private static final String MV_LBL_MATCH_HEADER = "Match"; //String.format("%" + MV_INDENT + "s%s", "", "Match: ");
    private static final String MV_LBL_STATUS_HEADER = "Status";
    //Status Options
    //private static final String MV_LBL_WINNER_RESULT = "Pending";
    private static final String MV_LBL_WINNER_RESULT = "Scores not submitted";
    
    private static final String TIE_ERROR_PROMPT = 
        	String.format("%" + MV_INDENT + "s%s", "", "No ties are allowed!");
        private static final String INVALID_INPUT_ERROR_PROMPT = 
            	String.format("%" + MV_INDENT + "s%s", "", "You must first enter two integer scores!");
    //Submit Button Options
    //private static final String MV_LBL_SUBMIT = "Set\nScore"; 
        private static final String MV_LBL_SUBMIT = "Winner";
    
    
	/***********************************************************
     * Control arrays (hold information about layout for view)
     ***********************************************************/
    /*------
     * Team Name Information
     *-----*/
    // lblTeam1 specific
    private Integer[] mvTeamOne;
    // lblTeam2 specific
    private Integer[] mvTeamTwo;
    /*------
     * Internal match information 
     *-----*/
    // lblMatch specific
    private Integer[] mvMatchHeader;
    private Integer[] mvMatchNumber;
    private Integer[] mvMatchBorder;
    // lblStatusHeader (Status) specific
    private Integer[] mvStatusHeader;
    private Integer[] mvMatchStatus;
    /*------
     * Score and Submit 
     *-----*/
    // txtScore1 specific
    private Integer[] mvScore1;
    // txtScore2 score specific
    private Integer[] mvScore2;
    // btnSubmit specific
    private Integer[] mvSubmit;

    /***********************************************************
     * Private View Fields
     ***********************************************************/
    private int index; // Number of match in tournament
    private Label lblMatchHeader, 
    			  lblMatchNumber,
                  lblMatchStatus, 
                  lblMatchBorder,
                  lblStatusHeader,
                  lblTeam1, 
                  lblTeam2; // Match label, winner and team
    private Button btnSubmit; // Submit score button
    private TextField txtScore1, 
                      txtScore2; // Scores
    //private String winnerStr = String.format("%" + MV_INDENT + "s%s", "", MV_LBL_WINNER_RESULT);
    private String winnerStr = String.format(" " + MV_LBL_WINNER_RESULT);
    private int colA;
    private int colB;
    private int colC;
    
    /*******************
     * Private Control Fields
     *******************/
    private Match<Integer> match;
    private int matchNum;
    private Object caller;
    private boolean isMirrored = false;
    
    
    
    /*******************
     * Constructors
     *******************/
   
    /**
     * Constructor: initializes all components
     */
    public MatchPane(int matchNum, Match<Integer> match, Object caller) {
    	this(matchNum, match, caller, false);
    }
    
    public MatchPane(int matchNum, Match<Integer> match, Object caller, Boolean isMirrored) {
    	this.isMirrored = isMirrored;
    	
    	if (this.isMirrored == false) {
        	colA = 0;
        	colB = 1;
        	colC = 2;
        } else {
        	colA = 2;
        	colB = 1;
        	colC = 0;
        }
    	
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
     * Re-populates the match label text.
     */
    public void refresh() {

        String team1Name, team2Name;
        try {
            team1Name = match.getTeams()[MV_TEAM1].toString();
        } catch (NullPointerException e) {
            team1Name = "";
        }
        
        try {
            team2Name = match.getTeams()[MV_TEAM2].toString();
        } catch (NullPointerException e) {
            team2Name = "";
        }
        
       
        if (isMirrored) {
        	lblTeam1.setText(String.format("%s%" + MV_TEAM_INDENT + "s", team1Name, ""));
	        lblTeam2.setText(String.format("%s%" + MV_TEAM_INDENT + "s", team2Name, ""));
        } else {
	        lblTeam1.setText(String.format("%" + MV_TEAM_INDENT + "s%s", "",  team1Name));
	        lblTeam2.setText(String.format("%" + MV_TEAM_INDENT + "s%s", "", team2Name));
        }

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
        
        // Instantiate Teams
        lblTeam1 = new Label();
        int col = colA;
        if (this.isMirrored) col = colB;
        mvTeamOne = new Integer[]{col, MV_DOUBLE, MV_ROW_1, MV_SINGLE};
        
        lblTeam2 = new Label();
        mvTeamTwo = new Integer[]{col, MV_DOUBLE, MV_ROW_4, MV_SINGLE};
        
        
        /*------
         * Internal match information 
         *-----*/

        // Add match info
        lblMatchHeader = new Label(); //Header
        mvMatchHeader = new Integer[]{colA, MV_SINGLE, MV_ROW_2, MV_SINGLE};
        
        //add Match Number info
        lblMatchNumber = new Label(); //
        mvMatchNumber = new Integer[]{colA, MV_SINGLE, MV_ROW_3, MV_SINGLE};
        
        //Add match box
        lblMatchBorder = new Label();
        mvMatchBorder = new Integer[]{colA, MV_SINGLE, MV_ROW_2, MV_DOUBLE};
        
        // Add Status Header
        lblStatusHeader = new Label();
        mvStatusHeader = new Integer[]{colB, MV_SINGLE, MV_ROW_2, MV_SINGLE};
        
        
        //Add status itself
        lblMatchStatus = new Label();
        mvMatchStatus = new Integer[]{colB, MV_SINGLE, MV_ROW_3, MV_SINGLE};
        

        /*------
         * Score and Submit 
         *-----*/

        // Add team 1 score
        txtScore1 = new TextField();
        mvScore1 = new Integer[] {colC, MV_SINGLE, MV_ROW_1, MV_SINGLE};
        

        // Add team 2 score
        txtScore2 = new TextField();
        mvScore2 = new Integer[] {colC, MV_SINGLE, MV_ROW_4, MV_SINGLE};

        
        // Add 'Submit Scores' button
        btnSubmit = new Button();
        mvSubmit = new Integer[] {colC, MV_SINGLE, MV_ROW_2, MV_DOUBLE};

        paintView(); //Actually adds items to matchPane

    }
    
    
    
    private void paintView() {
    	 /*------
         * Team Name Information
         *-----*/       
        
        // Add team 1
    	if (isMirrored) lblTeam1.getStyleClass().add("teamLabelMirrored"); 
    	else lblTeam1.getStyleClass().add("teamLabel");
        setControl(lblTeam1, mvTeamOne);
        
        // Add team 2
        if (isMirrored) lblTeam2.getStyleClass().add("teamLabelMirrored");
        else lblTeam2.getStyleClass().add("teamLabel");
        setControl(lblTeam2, mvTeamTwo);
        

        this.refresh(); //refreshes team names only
        
                
        /*------
         * Internal match information 
         *-----*/

        // Add match header
        lblMatchHeader.setText(MV_LBL_MATCH_HEADER);
        lblMatchHeader.getStyleClass().add("matchHeader");
        setControl(lblMatchHeader, mvMatchHeader);

        // Add match Number
        lblMatchNumber.setText("#" + matchNum);
        lblMatchNumber.getStyleClass().add("matchNumber");
        setControl(lblMatchNumber, mvMatchNumber);
        
        // add match box
        lblMatchBorder.getStyleClass().add("matchBorder");
        setControl(lblMatchBorder, mvMatchBorder);
        
        
        
        // Add Status box
        lblStatusHeader.setText(MV_LBL_STATUS_HEADER);
        lblStatusHeader.getStyleClass().add("statusHeader");
        setControl(lblStatusHeader, mvStatusHeader);
        
        //Add status itself
        lblMatchStatus = new Label(winnerStr);
        lblMatchStatus.getStyleClass().add("matchStatus");
        setControl(lblMatchStatus, mvMatchStatus);

        // Add 'Submit Scores' button
        btnSubmit.setText(MV_LBL_SUBMIT);
        btnSubmit.getStyleClass().add("submitButton");
        setControl(btnSubmit, mvSubmit);
        btnSubmit.setOnAction(e->action_handler());

        /*------
         * Scores and Submit 
         *-----*/

        // Add team 1 score
        txtScore1.setPromptText("<score>");
        txtScore1.getStyleClass().add("scoreLabel");
        setControl(txtScore1, mvScore1);
        txtScore1.setOnAction(e->action_handler());

        // Add team 2 score
        txtScore2.setPromptText("<score>");
        txtScore2.getStyleClass().add("scoreLabel");
        setControl(txtScore2, mvScore2);
        txtScore2.setOnAction(e->action_handler());

        
        
        setCSS_Start();
    }
    
    /**
     * sets default match CSS based on what has been filled in
     */
    private void setCSS_Start() {
    	
    	System.out.println("setCSS_Start");
    	System.out.println("t1 =:" + lblTeam1.getText().trim() + ":");
    	System.out.println("t2 =:" + lblTeam2.getText().trim()+ ":");
    	if (!(lblTeam1.getText().trim().equals("") && lblTeam2.getText().trim().equals(""))) {
//    		System.out.println("Setting to noTeamFill");
//    		lblTeam1.setId("noTeamFill");
//    		lblTeam2.setId("noTeamFill");
//    		txtScore1.setId("noTeamFill");
//    		txtScore2.setId("noTeamFill");
//    		btnSubmit.setId("noTeamFill");
//    		lblStatusHeader.setId("StatusHeader_NoTeam");
//    		lblMatchStatus.setId("matchStatus_NoTeam");
//    		lblMatchBorder.setId("matchBorder_NoTeam");
//    		lblMatchHeader.setId("MatchHeader_NoTeam");
//    		lblMatchNumber.setId("matchNumber_NoTeam");
//    	} else {
    		System.out.println("Setting to pendingFill");
    		lblTeam1.setId("pendingFill");
    		lblTeam2.setId("pendingFill");
    		txtScore1.setId("pendingFill");
    		txtScore2.setId("pendingFill");
    		btnSubmit.setId("pendingFill");
    		lblStatusHeader.setId("statusHeader_General");
    		lblMatchStatus.setId("matchStatus_General");
    		lblMatchHeader.setId("matchHeader_General");
    		lblMatchNumber.setId("matchNumber_General");
    		lblMatchBorder.setId("matchBorder_General");
    	}
    }
    
    
    private void setControl(Control ctrl, Integer[] mySetup) {
    	Integer setHeight = 0, setWidth = 0;
    	
    	//Find item width based on col and col span
    	for (int i = 0; i < mySetup[MV_COL_SPAN]; i++) {
    		setWidth += getColWidth(mySetup[MV_COL_IND] + i);
    	}
    	setWidth += ((mySetup[MV_COL_SPAN]-1)*MV_PANE_HORZ_GAP);
    	
    	//Find row height based on row and row span
    	for (int i = 0; i < mySetup[MV_ROW_SPAN]; i++) {
    		setHeight += getRowHeight(mySetup[MV_ROW_IND] + i);
    	}
    	setWidth += ((mySetup[MV_ROW_SPAN]-1)*MV_PANE_VERT_GAP);
    		
    	//Set control size based on col\row
    	ctrl.setMaxSize(setWidth, setHeight);
    	ctrl.setMinSize(setWidth, setHeight);
    		
//    	System.out.println("\nAdding " + ctrl);
//    	System.out.println("Column " + mySetup[MV_COL_IND]);
//    	System.out.println("Column Span" + mySetup[MV_COL_SPAN]);
//    	System.out.println("Row " + mySetup[MV_ROW_IND]);
//    	System.out.println("Row Span" + mySetup[MV_ROW_SPAN] + "\n");
    	
    	this.add(ctrl, 
    			 mySetup[MV_COL_IND],
    			 mySetup[MV_ROW_IND],
    			 mySetup[MV_COL_SPAN],
    			 mySetup[MV_ROW_SPAN]);
    }
    
    private Integer getRowHeight(Integer myRow) {
    	switch(myRow) {
	    	case MV_ROW_1:
	    		return MV_ROW_1_HEIGHT;
	    	case MV_ROW_2:
	    		return MV_ROW_2_HEIGHT;
	    	case MV_ROW_3:
	    		return MV_ROW_3_HEIGHT;
	    	case MV_ROW_4:
	    		return MV_ROW_4_HEIGHT;
	    	default:
	    		return -1;
    	}
    }
    
    private Integer getColWidth(Integer myCol) {
    	switch(myCol) {
	    	case 0:
	    		return (isMirrored) ? MV_COL_C_WIDTH: MV_COL_A_WIDTH;
	    	case 1:
	    		return MV_COL_B_WIDTH;
	    	case 2:
	    		return (isMirrored) ? MV_COL_A_WIDTH: MV_COL_C_WIDTH;
	    	default:
	    		return -1;
    	}
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
    		winnerStr = String.format(" " + (s1 > s2 ? lblTeam1.getText() : lblTeam2.getText()).trim());

    		lblMatchStatus.setText(winnerStr);

    		txtScore1.setEditable(false);
    		txtScore2.setEditable(false);

    		//Change winner view
    		Label winner = ((s1 > s2) ? lblTeam1: lblTeam2);
    		TextField winnerScore = ((s1 > s2) ? txtScore1: txtScore2);
    		winner.setId("teamWonFill");
    		winnerScore.setId("teamWonFill");
    		btnSubmit.setId("teamWonFill");

    		//Change looser view
    		Label loser = ((s1 > s2) ? lblTeam2 : lblTeam1);
    		TextField loserScore = ((s1 > s2) ? txtScore2: txtScore1);
    		loser.setId("teamLostFill");
    		loserScore.setId("teamLostFill");

    		match.setFinalScore(s1, s2);

    		((Main) caller).matchPaneCallBack(match, index);


    	} catch (NumberFormatException exception) {
    		lblMatchStatus.setText(INVALID_INPUT_ERROR_PROMPT);
    	} catch (IllegalStateException exception) {
    		lblMatchStatus.setText(TIE_ERROR_PROMPT);
    	}
    }
}