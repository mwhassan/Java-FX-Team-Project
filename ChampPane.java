package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class ChampPane<Score extends Comparable<Score>> 
			 extends MatchPane<Score> {

	/***********************************************************
     * Private Constants
     ***********************************************************/
	// GridPane as a whole
    private static final Integer MV_PANE_INSETS = 10; // Insets amount
    private static final Integer MV_PANE_VERT_GAP = 5; // Vertical gap
    private static final Integer MV_PANE_HORZ_GAP = 5; // Horizontal gap
    /*------
     * Pane layout
     *-----*/
    //Column Width details
    private static final Integer MV_COL_A_WIDTH = 15;
    private static final Integer MV_COL_B_WIDTH = 160;
    private static final Integer MV_COL_C_WIDTH = 60;
    private static final Integer MV_COL_D_WIDTH = 70;
    private static final Integer MV_COL_E_WIDTH = 60;
    private static final Integer MV_COL_F_WIDTH = 160;
    private static final Integer MV_COL_G_WIDTH = 15;
    //Row Height Details
    private static final Integer MV_ROW_0_HEIGHT = 15;
    private static final Integer MV_ROW_1_HEIGHT = 30;
    private static final Integer MV_ROW_2_HEIGHT = 15;
    private static final Integer MV_ROW_3_HEIGHT = 25;
    private static final Integer MV_ROW_4_HEIGHT = 25;
    private static final Integer MV_ROW_5_HEIGHT = 10;
    private static final Integer MV_ROW_6_HEIGHT = 25;
    private static final Integer MV_ROW_7_HEIGHT = 25;

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
    private static final int MV_ROW_5 = 4;
    private static final int MV_ROW_6 = 5;
    private static final int MV_ROW_7 = 6;
    private static final int MV_ROW_8 = 7;

    //COL Information (should not change)
    private static final int MV_COL_A = 0;
    private static final int MV_COL_B = 1;
    private static final int MV_COL_C = 2;
    private static final int MV_COL_D = 3;
    private static final int MV_COL_E = 4;
    private static final int MV_COL_F = 5;
    private static final int MV_COL_G = 6;
    
    //Takes up single x or double x (x = row or column)
    private static final Integer MV_SINGLE = 1;
    private static final Integer MV_DOUBLE = 2;
    private static final Integer MV_TRIPLE = 3;
    private static final Integer MV_QUAD = 4;
    
    
    
    
    
    private static final Integer MV_ROW_ALL = 8;
    private static final Integer MV_COL_ALL = 7;
    
    
    /*******************
     * Constructors
     *******************/
    
	public ChampPane(int matchNum, Match match, Object caller) {
		this(matchNum, match, caller, false);
	}
	
	public ChampPane(int matchNum, Match match, Object caller,Boolean isMirrored) {
		super(matchNum, match, caller, isMirrored);
		
		// Set pane specific fields
        this.setPadding(new Insets(MV_PANE_INSETS, MV_PANE_INSETS, MV_PANE_INSETS, MV_PANE_INSETS));
        this.setVgap(MV_PANE_VERT_GAP);
        this.setHgap(MV_PANE_HORZ_GAP);
	}
	
	
	/*******************
     * Protected Helper Classes
     *******************/
	
	 /**
     * Initialize the view variables but then calls paintView to build the view
     */
	@Override
	protected void initMatchView() {
        
    	 /*------
         * Team Name Information
         *-----*/       
        //mX = col, colSpan, row, rowSpan
        // Instantiate Teams
        lblTeam1 = new Label();
        mvTeamOne = new Integer[]{MV_COL_B, MV_SINGLE , MV_ROW_4, MV_DOUBLE};
        
        lblTeam2 = new Label();
        mvTeamTwo = new Integer[]{MV_COL_F, MV_SINGLE, MV_ROW_4, MV_DOUBLE};
        
        
        /*------
         * Internal match information 
         *-----*/

        // Add match info
        lblMatchHeader = new Label(); //Header
        mvMatchHeader = new Integer[]{MV_COL_D, MV_SINGLE, MV_ROW_1, MV_SINGLE};
        
        
        //add Match Number info
        lblMatchNumber = new Label(); //
        mvMatchNumber = new Integer[]{MV_COL_D, MV_SINGLE, MV_ROW_2, MV_SINGLE};
        
        
        /*------
         * Border information 
         *-----*/
        
        //Using match border to create border for entire pane
        lblMatchBorder = new Label();
        mvMatchBorder = new Integer[]{MV_COL_A, MV_COL_ALL, MV_ROW_3, MV_TRIPLE};
        
        /*------
         * Status information 
         *-----*/
        
        // Add Status Header
        lblStatusHeader = new Label();
        mvStatusHeader = new Integer[]{MV_COL_F, MV_SINGLE, MV_ROW_7, MV_SINGLE};
        
        
        //Add status itself
        lblMatchStatus = new Label();
        mvMatchStatus = new Integer[]{MV_COL_C, MV_TRIPLE , MV_ROW_7, MV_SINGLE};
        

        /*------
         * Score and Submit 
         *-----*/

        // Add team 1 score
        txtScore1 = new TextField();
        mvScore1 = new Integer[] {MV_COL_C, MV_SINGLE, MV_ROW_5, MV_SINGLE};
        

        // Add team 2 score
        txtScore2 = new TextField();
        mvScore2 = new Integer[] {MV_COL_E, MV_SINGLE, MV_ROW_5, MV_SINGLE};

        
        // Add 'Submit Scores' button
        btnSubmit = new Button();
        mvSubmit = new Integer[] {MV_COL_D, MV_SINGLE, MV_ROW_4, MV_DOUBLE};

        paintView(); //Actually adds items to matchPane

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
	@Override
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
    	setHeight += ((mySetup[MV_ROW_SPAN]-1)*MV_PANE_VERT_GAP);
    		
    	System.out.println("\nAdding " + ctrl);
    	System.out.println("Champ Col Width = " + setWidth);
    	System.out.println("Champ Row Height = " + setHeight);
    	
    	//Set control size based on col\row
    	ctrl.setMaxSize(setWidth, setHeight);
    	ctrl.setMinSize(setWidth, setHeight);
    		
    	
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
	@Override
    protected Integer getRowHeight(Integer myRow) {
    	switch(myRow) {

	    	case MV_ROW_1:
	    		return MV_ROW_0_HEIGHT;
	    	case MV_ROW_2:
	    		return MV_ROW_1_HEIGHT;
	    	case MV_ROW_3:
	    		return MV_ROW_2_HEIGHT;
	    	case MV_ROW_4:
	    		return MV_ROW_3_HEIGHT;
	    	case MV_ROW_5:
	    		return MV_ROW_4_HEIGHT;
	    	case MV_ROW_6:
	    		return MV_ROW_5_HEIGHT;
	    	case MV_ROW_7:
	    		return MV_ROW_6_HEIGHT;
	    	case MV_ROW_8:
	    		return MV_ROW_7_HEIGHT;
	    	default:
	    		return -1;
    	}
    }
    
    /**
     * Returns col width based on col sent in
     * @param myCol - col we want width for
     * @return - returns width of requested col
     */
	@Override
    protected Integer getColWidth(Integer myCol) {
    	switch(myCol) {
	    	case MV_COL_B:
	    		return MV_COL_B_WIDTH;
	    	case MV_COL_C:
	    		return MV_COL_C_WIDTH;
	    	case MV_COL_D:
	    		return MV_COL_D_WIDTH;
	    	case MV_COL_E:
	    		return MV_COL_E_WIDTH;
	    	case MV_COL_F:
	    		return MV_COL_F_WIDTH;
	    	default:
	    		return -1;
    	}
    }

}
