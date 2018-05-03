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

import javafx.beans.value.ObservableValue;
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
    private static final String MV_STATUS_PENDING = " Pending";
    private static final String MV_STATUS_WINNER = " Wins!";
    private static final String MV_STATUS_WAIT = " Waiting for teams";
    private static final String MV_STATUS_WAIT_SINGLE = " Waiting for team";
    private static final String MV_STATUS_CHAMP = " Champion";
    //Status Errors
    private static final String MV_STATUS_ERR_NO_TIES = " Ties not allowed";
    private static final String MV_STATUS_ERR_NUMERIC_ONLY = " Whole Numbers Only";
    private static final String MV_STATUS_ERR_SINGLE_SCORE = " Enter 2nd Score";
    //Submit Button Options
    private static final String MV_BTN_SUBMIT_PENDING = "";
    private static final String MV_BTN_SUBMIT_SETSCORE = "Set \nScore";
    private static final String MV_BTN_SUBMIT_WINNER = "Winner";
    private static final String MV_BTN_SUBMIT_CHAMP = "Champ!";
    //Match Number nad header
    private static final String MV_MATCH_NUMBER_CHAMP = "Final";
    private static final String MV_MATCH_HEADER_CHAMP = "";
    //Score text box prompt
    private static final String MV_LBL_SCORES_PROMPT = "<score>";
	
    /***********************************************************
     * Control arrays (hold information about layout for view)
     ***********************************************************/
    
    /*------
     * Team Name Information
     *-----*/
    
    // lblTeam1 specific
    protected Integer[] mvTeamOne;
    // lblTeam2 specific
    protected Integer[] mvTeamTwo;
    
    /*------
     * Internal match information 
     *-----*/
    
    // lblMatch specific
    protected Integer[] mvMatchHeader;
    protected Integer[] mvMatchNumber;
    protected Integer[] mvMatchBorder;
    // lblStatusHeader (Status) specific
    protected Integer[] mvStatusHeader;
    protected Integer[] mvMatchStatus;
    
    /*------
     * Score and Submit 
     *-----*/
    
    // txtScore1 specific
    protected Integer[] mvScore1;
    // txtScore2 score specific
    protected Integer[] mvScore2;
    // btnSubmit specific
    protected Integer[] mvSubmit;
    
    /***********************************************************
     * protected View Fields
     ***********************************************************/
    
    protected int index; // Number of match in tournament
    protected Label lblMatchHeader, 
    			  lblMatchNumber,
                  lblMatchStatus, 
                  lblMatchBorder,
                  lblStatusHeader,
                  lblTeam1, 
                  lblTeam2; // Match label, winner and team
    protected Button btnSubmit; // Submit score button
    protected TextField txtScore1, 
                      txtScore2; // Scores
    protected int colA;
    protected int colB;
    protected int colC;
    
    /*******************
     * Private Control Fields
     *******************/
    
    protected Match<Integer> match;
    protected int matchNum;
    protected Object caller;
    protected boolean isMirrored = false; //Allows for re-arranging matchPane for symmetrical split bracket
    protected boolean isChamp = false; //is this championship]
    protected boolean isMatchOver = false; //has the match been completed
    
    /*******************
     * Constructors
     *******************/
   
    /**
     * Constructor: initializes all components
     * 
     * @param matchNum - index of match we are adding
     * @param match - match itself
     * @param caller - class that is instantiating new matchPane
     */
    public MatchPane(int matchNum, Match<Integer> match, Object caller) {
    	this(matchNum, match, caller, false);
    }
    
    /**
     * Constructor 
     * 
     * @param matchNum - index of match we are adding
     * @param match - match itself
     * @param caller - class that is instantiating new matchPane
     * @param isMirrored
     */
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
        //set some controller information
        this.match = match;
        this.index = matchNum;
        
        //Setup and instantiate control fields
        initMatchController(matchNum, match, caller);
        
        //Setup view instantiate view fields
        initMatchView();
        
        //Should not be functional until populated
        this.setDisable(true);
    }
    
    
    /*******************
     * Public Interface
     *******************/
    
    /**
     * Re populates team name labels and updates CSS based on current state
     */
    public void refresh() {
    	refresh(true);
    }
    
    /**
     * Sets isChamp to true and then runs refresh
     * Re populates team name labels and updates CSS based on current state
     */
    public void refreshChamp() {
    	isChamp = true;
    	refresh(true);
    }
    
    
    /*******************
     * Protected Helper Classes
     *******************/
    
    /**
     * Re-populates the match label text. Also updates CSS based on current state if setCSS is true.  
     * 
     * @param setCSS -  will refresh css state if true
     */
    protected void refresh(boolean setCSS) {

        String team1Name, team2Name;
        
        //Find team name
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
       
        //Set labels
        if (isMirrored) {
        	lblTeam1.setText(String.format("%s%" + MV_TEAM_INDENT + "s", team1Name, ""));
	        lblTeam2.setText(String.format("%s%" + MV_TEAM_INDENT + "s", team2Name, ""));
        } else {
	        lblTeam1.setText(String.format("%" + MV_TEAM_INDENT + "s%s", "",  team1Name));
	        lblTeam2.setText(String.format("%" + MV_TEAM_INDENT + "s%s", "", team2Name));
        }
        
        if (setCSS) setCSS_Start();
    }
    
    /**
     * Initialize controller variables 
     * 
     * @param matchNum - what match we are on
     * @param match - the match itself
     * @param caller - the object that instantiated the class (used for callback when
     * 					the score is set
     */
    protected void initMatchController(int matchNum, Match<Integer> match, Object caller) {
        this.matchNum = matchNum;
        this.match = match;
        this.caller = caller;
    }

    /**
     * Initialize the view variables but then calls paintView to build the view
     */
    protected void initMatchView() {
        
    	 /*------
         * Team Name Information
         *-----*/       
        
    	//dif between normal and mirrored setup
        int col = colA;
        if (this.isMirrored) col = colB;
        // Instantiate Teams
        //team 1
        lblTeam1 = new Label();
        mvTeamOne = new Integer[]{col, MV_DOUBLE, MV_ROW_1, MV_SINGLE};
        //team 2
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
        
        /*------
         * Status information 
         *-----*/
        
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
        //Add items to the pane itself
        paintView(); 
    }
    
    
    /**
     * Builds the view itself by adding items to the pane (via setControl)
     */
    protected void paintView() {
    	
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
        //refreshes team names only and holds off on setting css
        this.refresh(false); 
        
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
        lblMatchStatus = new Label(MV_STATUS_PENDING);
        lblMatchStatus.getStyleClass().add("matchStatus");
        setControl(lblMatchStatus, mvMatchStatus);
        // Add 'Submit Scores' button
        btnSubmit.getStyleClass().add("submitButton");
        setControl(btnSubmit, mvSubmit);
        btnSubmit.setOnAction(e->btnAction_Handler());
        
        /*------
         * Scores and Submit 
         *-----*/
        
        // Add team 1 score
        txtScore1.setPromptText(MV_LBL_SCORES_PROMPT);
        txtScore1.getStyleClass().add("scoreLabel");
        setControl(txtScore1, mvScore1);
        txtScore1.setOnAction(e->txtFieldAction_Handler("score1"));
        //Add team 1 listener for focus changes
        txtScore1.focusedProperty().addListener((obs, oldVal, newVal) -> 
										txtLostFocus_Handler(obs, oldVal, newVal, txtScore1));
        // Add team 2 score
        txtScore2.setPromptText(MV_LBL_SCORES_PROMPT);
        txtScore2.getStyleClass().add("scoreLabel");
        setControl(txtScore2, mvScore2);
        txtScore2.setOnAction(e->txtFieldAction_Handler("score2"));
        //Add team2 listener for focus changes
        txtScore2.focusedProperty().addListener((obs, oldVal, newVal) -> 
        										txtLostFocus_Handler(obs, oldVal, newVal, txtScore2));
        //Set css and enabled status
        setCSS_Start();
    }
    

	/**
     * sets default match CSS stylesheets and can enable and disable controls as needed
     */
    protected void setCSS_Start() {
    	//Create states via boolean logic
    	boolean team1Ready = !(lblTeam1.getText().trim().equals(""));
    	boolean team2Ready = !(lblTeam2.getText().trim().equals(""));
    	boolean enable = team1Ready && team2Ready;
    	/*
    	 * Set button to disabled and pending colors.  I will later check state to determine if it needs to be
    	 * updated, but the logic was easier to just default to pending state
    	 */
    	btnSubmit.setText(MV_BTN_SUBMIT_PENDING);
    	btnSubmit.setDisable(true);
    	//If score one has a team
    	if (team1Ready){
    		lblTeam1.setId("pendingFill");
    		txtScore1.setId("pendingFill");
    		lblMatchStatus.setText(MV_STATUS_WAIT_SINGLE + " 2");
    	}
    	//If score two has a team
    	if (team2Ready){
    		lblTeam2.setId("pendingFill");
    		txtScore2.setId("pendingFill");	
    		lblMatchStatus.setText(MV_STATUS_WAIT_SINGLE + " 1");
    	}
    	//if both teams are set
    	if (enable) {
    		lblMatchStatus.setId("matchStatus_General");
    		lblMatchHeader.setId("matchHeader_General");
    		lblMatchNumber.setId("matchNumber_General");
    		lblMatchBorder.setId("matchBorder_General");
    		lblStatusHeader.setId("statusHeader_General");
    		lblMatchStatus.setText(MV_STATUS_PENDING);
    		btnSubmit.setId("submitPendingFill");
    	} else {
    		/*
    		 * if both teams are not set then 
    		 * if current text is not 'Waiting for team x' (which is set above if either team is
    		 * enabled) then we set it to 'waiting for teams'
    		 */
    		if (!lblMatchStatus.getText().contains(MV_STATUS_WAIT_SINGLE)) 
    			lblMatchStatus.setText(MV_STATUS_WAIT);
    	}
    	//set enabled\disabled state of controls
    	lblTeam1.setDisable(!enable);
    	txtScore1.setDisable(!enable);
    	lblTeam2.setDisable(!enable);
    	txtScore2.setDisable(!enable);
    	lblStatusHeader.setDisable(!enable);
    	lblMatchStatus.setDisable(!enable);
    	lblMatchHeader.setDisable(!enable);
    	lblMatchNumber.setDisable(!enable);
    	lblMatchBorder.setDisable(!enable);
    }
    
    
    /**
     * Generically Adds a control based on setup details sent in
     * Sets controls details including row and row span, column and column span, and then sets size.
     * We wanted size to stay set regardless of input so we need to set min and max size for 
     * each control in match pane.
     *   
     * @param ctrl - the control we are locking size on
     * @param mySetup - information about the control parameters set in initMatchView
     */
    protected void setControl(Control ctrl, Integer[] mySetup) {
    	Integer setHeight = 0, setWidth = 0;
    	//Find item width based on col and col span
    	for (int i = 0; i < mySetup[MV_COL_SPAN]; i++) {
    		setWidth += getColWidth(mySetup[MV_COL_IND] + i); //returns width for each col in span
    	}
    	setWidth += ((mySetup[MV_COL_SPAN]-1)*MV_PANE_HORZ_GAP);
    	//Find row height based on row and row span
    	for (int i = 0; i < mySetup[MV_ROW_SPAN]; i++) {
    		setHeight += getRowHeight(mySetup[MV_ROW_IND] + i); //return height for each row in span
    	}
    	setWidth += ((mySetup[MV_ROW_SPAN]-1)*MV_PANE_VERT_GAP);
    	//Set control size based on col\row
    	ctrl.setMaxSize(setWidth, setHeight);
    	ctrl.setMinSize(setWidth, setHeight);
    	//Add control
    	this.add(ctrl, 
    			 mySetup[MV_COL_IND],
    			 mySetup[MV_ROW_IND],
    			 mySetup[MV_COL_SPAN],
    			 mySetup[MV_ROW_SPAN]);
    }

    /**
     * Returns row height based on row sent in
     * @param myRow - row we want height for
     * @return - returns height of requested row
     */
    protected Integer getRowHeight(Integer myRow) {
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
    
    /**
     * Returns col width based on col sent in
     * @param myCol - col we want width for
     * @return - returns width of requested col
     */
    protected Integer getColWidth(Integer myCol) {
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
    
    /*******************
     * Protected Event Handlers
     *******************/
    /**
     * Update score when submit button is pressed. Displays prompt if invalid scores entered.
     * Progression is halted at different stages and css is updated in various error scenarios:
     * 1) If an invalid score is entered, displays an invalid argument message and clears the score.
     * 2) If only one score is passed in, prompts the user to enter a 2nd score.
     */
    protected void btnAction_Handler() {
    	
    	//Score variables
    	Integer s1 = 0;
    	Integer s2 = 0;
    	
    	//Invalid input flags
    	boolean invalid1 = false;	//score1 invalid
    	boolean invalid2 = false;	//score2 invalid
    	
    	//No score flags
    	boolean empty1 = false;		//no score entered for score1
    	boolean empty2 = false;		//no score entered for score2
    	
    	//Check that the first score is a valid non-negative integer
    	try {
    		if (txtScore1.getText().trim().equals("")) empty1 = true;
    		else {
    			s1 = Integer.parseInt(txtScore1.getText());
        		if(s1 < 0) throw new NumberFormatException();	
    		}
    	} catch(NumberFormatException exception) {
    		invalid1 = true;
    		txtScore1.setText("");
    	}
    	
    	//Check that the second score is a valid non-negative integer
    	try {
    		if (txtScore2.getText().trim().equals("")) empty2 = true;
    		else {
    			s2 = Integer.parseInt(txtScore2.getText());
        		if(s2 < 0) throw new NumberFormatException();	
    		}
    	} catch(NumberFormatException exception) {
    		invalid2 = true;
    		txtScore2.setText("");
    	}
    	
    	/*
    	 * If invalid scores entered, display numeric only message.
    	 * Otherwise, check if scores are empty and display single score prompt.
    	 */
    	if(invalid1 || invalid2) {
    		lblMatchStatus.setText(MV_STATUS_ERR_NUMERIC_ONLY);
    		if((invalid1 && invalid2) || (invalid1 && empty2) || (empty1 && invalid2)) {
    			btnSubmit.setDisable(true);
    			btnSubmit.setText(MV_BTN_SUBMIT_PENDING);
    			btnSubmit.setId("submitPendingFill");
    		}
    		return;
    	}else {
    		if(empty1 || empty2) {
    			lblMatchStatus.setText(MV_STATUS_ERR_SINGLE_SCORE);
    			return;
    		}
    	}
		
		//If scores are tied, throw state exception
		if (s1 == s2) {
			txtScore1.setText("");
			txtScore2.setText("");
			btnSubmit.setDisable(true);
			btnSubmit.setText(MV_BTN_SUBMIT_PENDING);
			btnSubmit.setId("submitPendingFill");
			lblMatchStatus.setText(MV_STATUS_ERR_NO_TIES);
			return;
		}
		
		/****************************
		 * If we made it this far we have a valid submission
		 ****************************/
		//Disable scores and submit button
		txtScore1.setEditable(false);
		txtScore2.setEditable(false);
		btnSubmit.setDisable(true);
		//Set final score for match
		match.setFinalScore(s1, s2);
		//Change winner view
		Label winner = ((s1 > s2) ? lblTeam1: lblTeam2);
		TextField winnerScore = ((s1 > s2) ? txtScore1: txtScore2);
		
		//Champ view to champ style is isChamp = true
		if (isChamp) {
			winner.setId("ChampionFill");
    		winnerScore.setId("scoreChampionFill");
    		btnSubmit.setId("submitChampionFill");
    		btnSubmit.setText(MV_BTN_SUBMIT_CHAMP);
    		//Match header (match)
    		lblMatchHeader.setText(MV_MATCH_HEADER_CHAMP);
    		//Match number
    		lblMatchNumber.setText(MV_MATCH_NUMBER_CHAMP);
    		lblMatchNumber.setId("matchNumber_Champ");
    		//Match Border
    		lblMatchBorder.setId("matchBorder_Champ");
    		//Status header (status)
    		lblStatusHeader.setId("statusHeader_Champ");
    		//Match Status
    		lblMatchStatus.setText(MV_STATUS_CHAMP);
    		lblMatchStatus.setId("matchStatus_Champ");
		}else {
			//Else just winner style
			winner.setId("teamWonFill");
    		winnerScore.setId("scoreTeamWonFill");
    		btnSubmit.setId("submitTeamWonFill");
    		btnSubmit.setText(MV_BTN_SUBMIT_WINNER);
    		lblMatchStatus.setId("matchStatus_Winner");
    		String winStr = " " + match.getWinner().getName();    		
    		lblMatchStatus.setText(winStr + " " + MV_STATUS_WINNER);
		}
		
		//Change looser view
		Label loser = ((s1 > s2) ? lblTeam2 : lblTeam1);
		TextField loserScore = ((s1 > s2) ? txtScore2: txtScore1);
		loser.setId("teamLostFill");
		loserScore.setId("scoreTeamLostFill");
		//set match as over (used for setting css style elsewhere
		isMatchOver = true;
		//callback to main to let it know to refresh next match
		((Main) caller).matchPaneCallBack(match, index);
    }
    
    /**
     * Checks score and prompt states after a score field looses focus to see if input was valid,
     * update status to match current state,  and updates prompts and stylesheets based on state
     * 
     * @param obs - was there a change in state of focus
     * @param oldVal - what was the old focus state
     * @param newVal - what is the new focus state
     * @param ctrl - control we are monitoring
     */
    protected void txtLostFocus_Handler(ObservableValue<? extends Boolean> obs, Boolean oldVal, Boolean newVal, TextField ctrl) {
    	//don't care about getting focus
    	if (newVal) return; //if newVal is true then we got focus, we only care if we lose focus
    	if (isMatchOver) return; //defensive don't update if match is over
    	
    	//parses control text and will throw NumberFormat exception if not valid input
    	//A value is entered in both controls then set status to pending
		if (!(txtScore1.getText().trim().equals("")) || !(txtScore2.getText().trim().equals(""))) {
			lblMatchStatus.setText(MV_STATUS_PENDING);
			btnSubmit.setDisable(false);
			btnSubmit.setText(MV_BTN_SUBMIT_SETSCORE);
			btnSubmit.setId("submitReadyFill");
		} else {
			/*
			 * at least one does not have a score.  Double checking
			 * still disabled as you can enter and clear scores but have
			 * button show up
			 */
			lblMatchStatus.setText(MV_STATUS_PENDING);
			if (!btnSubmit.isDisable()) {
				btnSubmit.setDisable(true);
				btnSubmit.setText(MV_BTN_SUBMIT_PENDING);
				btnSubmit.setId("submitPendingFill");
			}
		}
    }
    
    /**
     *moves to alt text control if user hits 'enter'.  This would be what we change if we want enter to
     *submit scores
     * 
     * @param ctrl - current control
     */
    protected void txtFieldAction_Handler(String ctrl) {
    	switch (ctrl) {
    	case "score1":
    		txtScore2.requestFocus();
    		break;
    	case "score2":
    		txtScore1.requestFocus();
    		break;
    	}
    }
}