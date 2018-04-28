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
    private static final String MV_STATUS_PENDING = "Pending";
    private static final String MV_STATUS_WINNER = " Win!";
    private static final String MV_STATUS_WAIT = "Waiting for teams";
    private static final String MV_STATUS_WAIT_SINGLE = "Waiting for team";
    private static final String MV_STATUS_CLEAR = "";
    private static final String MV_STATUS_NO_SCORES = "No Scores Entered";
    private static final String MV_STATUS_CHAMP = "Champions";
    
    //Status Errors
    private static final String MV_STATUS_ERR_NO_TIES = "Ties not allowed";
    private static final String MV_STATUS_ERR_NUMERIC_ONLY = "Integers Only";
    private static final String MV_STATUS_ERR_INVALID = "Enter 2nd Score";
    
        
    //Submit Button Options
    private static final String MV_BTN_SUBMIT_PENDING = "";
    private static final String MV_BTN_SUBMIT_SETSCORE = "Set Score";
    private static final String MV_BTN_SUBMIT_WINNER = "Winner";
    private static final String MV_BTN_SUBMIT_CHAMP = "Champ!";
    
    //Match Number nad header
    private static final String MV_MATCH_NUMBER_CHAMP = "Final";
    private static final String MV_MATCH_HEADER_CHAMP = "";
        
    
    private static final String MV_LBL_SCORES_PROMPT = "<score>";
    
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
    private int colA;
    private int colB;
    private int colC;
    
    /*******************
     * Private Control Fields
     *******************/
    private Match<Integer> match;
    private int matchNum;
    private Object caller;
    private boolean isMirrored = false; //Allows for re-arranging matchPane for symmetrical split bracket
    private boolean isChamp = false; //is this championshi]
    private boolean isMatchOver = false; //has the match been completed
    
    
    /*******************
     * Constructors
     *******************/
   
    /**
     * Constructor: initializes all components
     */
    public MatchPane(int matchNum, Match<Integer> match, Object caller) {
    	this(matchNum, match, caller, false);
    }
    
    /**
     * 
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
    

//    /**
//     * Enables MatchPane for editing.
//     */
//    public void clear() {
//        txtScore1.setPromptText(MV_LBL_SCORES_PROMPT);
//        txtScore2.setPromptText(MV_LBL_SCORES_PROMPT);
//        
//        txtScore1.setEditable(true);
//        txtScore2.setEditable(true);
//    }
    
    
    public void refresh() {
    	refresh(true);
    }
    
    public void refreshChamp() {
    	isChamp = true;
    	refresh(true);
    }
    
    
    /*******************
     * Private Helper Classes
     *******************/
    
    /**
     * Re-populates the match label text.
     * 
     * 
     * @param setCSS -  will refresh css state if true
     */
    private void refresh(boolean setCSS) {

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
    private void initMatchController(int matchNum, Match<Integer> match, Object caller) {
        this.matchNum = matchNum;
        this.match = match;
        this.caller = caller;
    }

    /**
     * Initialize the view variables but then calls paintView to build the view
     */
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
    
    
    /*
     * Builds the view itself by adding items to the pane (via setControl)
     */
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

        //Add team 1 listner for focus changes
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
     * sets default match CSS stylesheets and can enable and disable controls
     * as needed
     */
    private void setCSS_Start() {
    	
    	boolean team1Enabled = !(lblTeam1.getText().trim().equals(""));
    	boolean team2Enabled = !(lblTeam2.getText().trim().equals(""));
    	boolean otherEnabled = team1Enabled && team2Enabled;
    	
    	btnSubmit.setText(MV_BTN_SUBMIT_PENDING);
    	btnSubmit.setDisable(true);
    	
    	//If score one has a team
    	if (team1Enabled){
    		lblTeam1.setId("pendingFill");
    		txtScore1.setId("pendingFill");
    		lblMatchStatus.setText(MV_STATUS_WAIT_SINGLE + " 2");
    	}
    	
    	lblTeam1.setDisable(!team1Enabled);
    	txtScore1.setDisable(!team1Enabled);
    		
    	
    	
    	//If score two has a team
    	if (team2Enabled){
    		lblTeam2.setId("pendingFill");
    		txtScore2.setId("pendingFill");	
    		lblMatchStatus.setText(MV_STATUS_WAIT_SINGLE + " 1");
    	}
    	lblTeam2.setDisable(!team2Enabled);
    	txtScore2.setDisable(!team2Enabled);
    	
    	
    	
    	//if both are enabled
    	if (otherEnabled) {
    		System.out.println("Setting to pendingFill");
    		lblMatchStatus.setId("matchStatus_General");
    		lblMatchHeader.setId("matchHeader_General");
    		lblMatchNumber.setId("matchNumber_General");
    		lblMatchBorder.setId("matchBorder_General");
    		lblStatusHeader.setId("statusHeader_General");
    		lblMatchStatus.setText(MV_STATUS_PENDING);
    		
    		btnSubmit.setId("submitPendingFill");
    		
    		
    	} else {
    		if (!lblMatchStatus.getText().contains(MV_STATUS_WAIT_SINGLE)) 
    			lblMatchStatus.setText(MV_STATUS_WAIT);
    		
    	}
    	
    	
    	
    	
    		
    
    	
    	lblStatusHeader.setDisable(!otherEnabled);
    	lblMatchStatus.setDisable(!otherEnabled);
    	lblMatchHeader.setDisable(!otherEnabled);
    	lblMatchNumber.setDisable(!otherEnabled);
    	lblMatchBorder.setDisable(!otherEnabled);
    	
	
    }
    
    
    /**
     * Adds a control
     * Sets controls details including row and row span, column and column span, and then sets size.
     * We wanted size to stay set regardless of input so we need to set min and max size for 
     * each control in match pane.
     *   
     * @param ctrl - the control we are locking size on
     * @param mySetup - information about the control parameters set in initMatchView
     */
    private void setControl(Control ctrl, Integer[] mySetup) {
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
    
    
    /**
     * Returns row height based on row sent in
     * @param myRow - row we want height for
     * @return - returns height of requested row
     */
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
    
    /**
     * Returns col width based on col sent in
     * @param myCol - col we want width for
     * @return - returns width of requested col
     */
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
     * 
     * TODO
     * 1) disable button if scores final
     * 2) modify button and status text to match current state
     * 3) Lock down ability to put in non numerics and update status with error as necessary
     *    or use alert box and clear
     */
    private void btnAction_Handler() {
    	
    	if (btnSubmit.isDisabled()) {
    		System.out.println("BUTTON IS DISABLED YO");
    		return;
    	}
    	
    	try {
    		System.out.println("Button isDisable = " + btnSubmit.isDisable());
    		System.out.println("Button isDisabled = " + btnSubmit.isDisabled());
    		
    		//If either score is missing then change status with arg exception
    		if (txtScore1.getText().trim().equals("") || txtScore2.getText().trim().equals(""))
    			throw new IllegalArgumentException("Missing Score");
    		
    		
    		//will throw number format and exit if either score is empty or invalid
    		Integer s1 = Integer.parseInt(txtScore1.getText()); 
    		Integer s2 = Integer.parseInt(txtScore2.getText());

    		
    		//If scores are tied, throw state exception
    		if (s1 == s2)
    			throw new IllegalStateException("No ties allowed in bracket.");
    		
    		
    		

    		txtScore1.setEditable(false);
    		txtScore2.setEditable(false);
    		btnSubmit.setDisable(true);

    		//Change winner view
    		Label winner = ((s1 > s2) ? lblTeam1: lblTeam2);
    		TextField winnerScore = ((s1 > s2) ? txtScore1: txtScore2);
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
    			winner.setId("teamWonFill");
        		winnerScore.setId("scoreTeamWonFill");
        		btnSubmit.setId("submitTeamWonFill");
        		btnSubmit.setText(MV_BTN_SUBMIT_WINNER);
    		}
    		

    		//Change looser view
    		Label loser = ((s1 > s2) ? lblTeam2 : lblTeam1);
    		TextField loserScore = ((s1 > s2) ? txtScore2: txtScore1);
    		loser.setId("teamLostFill");
    		loserScore.setId("scoreTeamLostFill");

    		
    		match.setFinalScore(s1, s2);

    		if (!(this.isChamp)) {
    			String winStr = " " + match.getWinner().getName();    		
        		lblMatchStatus.setText(winStr + " " + MV_STATUS_WINNER);
    		}
    		
    		
    		isMatchOver = true;
    		((Main) caller).matchPaneCallBack(match, index);


    	} catch (NumberFormatException exception) {
    		exception.printStackTrace();
    		lblMatchStatus.setText(MV_STATUS_ERR_NUMERIC_ONLY);
    	} catch (IllegalArgumentException exception) {
    		lblMatchStatus.setText(MV_STATUS_ERR_INVALID);
    	} catch (IllegalStateException exception) {
    		lblMatchStatus.setText(MV_STATUS_ERR_NO_TIES);
    	}
    }
    
    private void txtLostFocus_Handler(ObservableValue<? extends Boolean> obs, Boolean oldVal, Boolean newVal, TextField ctrl) {
    	//don't care about getting focus
    	if (newVal) return; 
    	if (isMatchOver) return;
    	
    	System.out.println("obs : " + obs.getValue());
    	System.out.println("oldVal : " + oldVal);
    	System.out.println("newVal : " + newVal);
    	
    	//parses control text and will throw NF exception if not valid input
    	try {
    		
	    	//A value is entered in both controls
    		if (!(txtScore1.getText().trim().equals("")) &&
	    			!(txtScore2.getText().trim().equals(""))) {
    			
    			lblMatchStatus.setText(MV_STATUS_PENDING);
    			
    			//if there is a tie
	    		if (txtScore1.getText().trim().equals(
    					txtScore2.getText().trim())) {
	    			txtScore1.setText("");
	    			txtScore2.setText("");
	    			lblMatchStatus.setText(MV_STATUS_ERR_NO_TIES);
	    			//return;
	    		} else {
	    			btnSubmit.setDisable(false);
		    		btnSubmit.setText(MV_BTN_SUBMIT_SETSCORE);
		        	Integer s2 = Integer.parseInt(ctrl.getText());
	    		}
	    			
	    			
	    		
	    	} else {
	    		//at least one does not have a score.  Double checking
	    		//still disabled as you can enter and clear scores but have
	    		//button show up
	    		System.out.println("Is button disabled ? " + btnSubmit.isDisable());
	    		lblMatchStatus.setText(MV_STATUS_PENDING);
	    		if (!btnSubmit.isDisable()) {
		    		btnSubmit.setDisable(true);
		    		btnSubmit.setText(MV_BTN_SUBMIT_PENDING);
	    		}
	    	}
	    		
	    	
	    	//A value is entered in the control that lost focus 
	    	if (!(ctrl.getText().trim().equals(""))){
	        	Integer s2 = Integer.parseInt(ctrl.getText());
	    	}
	    		
    	} catch (NumberFormatException exception) {
			ctrl.setText("");
			lblMatchStatus.setText(MV_STATUS_ERR_NUMERIC_ONLY);
			if (!btnSubmit.isDisable()) {
	    		btnSubmit.setDisable(true);
	    		btnSubmit.setText(MV_BTN_SUBMIT_PENDING);
    		}
			ctrl.requestFocus();
		}
	
    	
    	
    }
    
    private void txtFieldAction_Handler(String ctrl) {
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