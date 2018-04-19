package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * This class represents the data for a single match on the GUI.
 */
public class MatchPane<S extends Comparable<S>, M extends MatchADT<S>> extends GridPane {


    /*******************
     * Private Constants
     *******************/

    // GridPane specific
    private static final Integer MV_PANE_INSETS = 10; // Insets amount
    private static final Integer MV_PANE_VERT_GAP = 2; // Vertical gap
    private static final Integer MV_PANE_HORZ_GAP = 3; // Horizontal gap

    // this.add(child, columnIndex, rowIndex, colspan, rowspan);

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
                                                                    // status

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



    /*******************
     * Private View Fields
     *******************/
    
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
    private M match;


    /*******************
     * Constructors
     *******************/

    /**
     * Constructor: initializes all components
     */
    public MatchPane(int matchNum, M match) {

        // Set pane specific fields
        this.setPadding(new Insets(MV_PANE_INSETS, MV_PANE_INSETS, MV_PANE_INSETS, MV_PANE_INSETS));
        this.setVgap(MV_PANE_VERT_GAP);
        this.setHgap(MV_PANE_HORZ_GAP);


        //Setup control fields
        initMatchController(match);
        
        //Setup view fields
        initMatchView();
        

    }
    
    
    /*******************
     * Public Interface
     *******************/
    

    /**
     * Clears entered scores Assumes no winner has been declared or displayed, although this method
     * will still function otherwise.
     */
    public void clear() {
        txtScore1.setPromptText("Score");
        txtScore2.setPromptText("Score");

        txtScore1.setEditable(true);
        txtScore2.setEditable(true);
    }
    
    
    /*******************
     * Private Helper Classes
     *******************/
    
    private void initMatchController(M match) {
        this.match = match;
    }

    private void initMatchView() {

        /*------
         * Team Name Information
         *-----*/

        // Add team 1
        lblTeam1 = new Label(String.format("%-" + MC_TEAM_SPACING + "s", " " + name1));
        lblTeam1.getStyleClass().add("label-team");
        lblTeam1.setMaxWidth(MV_TEAM_MAXWIDTH);
        this.add(lblTeam1, MV_TEAM1_COL_IND, MV_TEAM1_ROW_IND, MV_TEAM1_COL_SPAN,
                        MV_TEAM1_ROW_SPAN);

        // Add team 2
        lblTeam2 = new Label(String.format("%-" + MC_TEAM_SPACING + "s", " " + name2));
        lblTeam2.getStyleClass().add("label-team");
        lblTeam1.setMaxWidth(MV_TEAM_MAXWIDTH);
        this.add(lblTeam2, MV_TEAM2_COL_IND, MV_TEAM2_ROW_IND, MV_TEAM2_COL_SPAN,
                        MV_TEAM2_ROW_SPAN);



        /*------
         * Internal match information 
         *-----*/

        // Add match header
        lblMatch = new Label(MV_LBL_MATCH + matchNum);
        this.add(lblMatch, MV_MATCH_COL_IND, MV_MATCH_ROW_IND, MV_MATCH_COL_SPAN,
                        MV_MATCH_ROW_SPAN);

        // Add 'Submit Scores' button
        btnSubmit = new Button(MV_LBL_SUBMIT);
        btnSubmit.setMinHeight(getHeight());
        btnSubmit.setMaxWidth(MV_SCORE_WIDTH);
        btnSubmit.setMinWidth(MV_SCORE_WIDTH);
        this.add(btnSubmit, MV_SUBMIT_COL_IND, MV_SUBMIT_ROW_IND, MV_SUBMIT_COL_SPAN,
                        MV_SUBMIT_ROW_SPAN);
        btnSubmit_setOnAction(); //Sets submit button on action

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


        // Add team 2 score
        txtScore2 = new TextField();
        txtScore2.setPromptText("<score>");
        txtScore2.setMaxWidth(MV_SCORE_WIDTH);
        txtScore2.setMinWidth(MV_SCORE_WIDTH);
        this.add(txtScore2, MV_SCORE_T2_COL_IND, MV_SCORE_T2_ROW_IND, MV_SCORE_T2_COL_SPAN,
                        MV_SCORE_T2_ROW_SPAN);

        // Add winner box
        lblWinnerStatus = new Label(winnerStr);
        this.add(lblWinnerStatus, MV_WINNER_COL_IND, MV_WINNER_ROW_IND, MV_WINNER_COL_SPAN,
                        MV_WINNER_ROW_SPAN);


        
        
        
    }
    
    /**
     * Update score when submit button is pressed Throw an exception if an invalid integer or a
     * tie is entered
     */
    private void btnSubmit_setOnAction() {
        btnSubmit.setOnAction(event -> {
            try {
                int s1 = Integer.parseInt(txtScore1.getText());
                int s2 = Integer.parseInt(txtScore2.getText());
                if (s1 == s2)
                    throw new IllegalStateException("No ties allowed in bracket.");
                winnerStr = String.format("%-" + MV_DISP_SPACING + "s", (MV_LBL_WINNER_STATUS
                                + " Winner = "
                                + (s1 > s2 ? lblTeam1.getText() : lblTeam2.getText()).trim()));

                lblWinnerStatus.setText(winnerStr);

                txtScore1.setEditable(false);
                txtScore2.setEditable(false);
            } catch (NumberFormatException exception) {
                lblWinnerStatus.setText("You must first enter a score!");
                throw new NumberFormatException("Please enter an integer score!");
            }
        });
    }

}

