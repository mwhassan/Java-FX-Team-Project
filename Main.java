package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.MatchADT.teamSpot;
import javafx.application.Application;
import javafx.application.Application.Parameters;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Main extends Application{

    /*******************
     * Private Constants
     *******************/
    private static final Integer STAGE_WIDTH = 900;
    private static final Integer STAGE_HEIGHT = 500;
    
    private static final Integer HORIZONTAL_PADDING = 10;
    private static final Integer VERTICAL_PADDING = 5;
    
    /*******************
     * Private View Variables
     *******************/
    private Stage stage;                    // The stage
    private Scene scene;                    // The scene
    
    private BorderPane[] roundPanes;		// Displays teams for each round
    private VBox[] columnPanes;				// Displays each column (half-round)
    private MatchPane[] matchPanes;			// Groups submit buttons with scores and team labels
  
    
    /*******************
     * Private Control Variables
     *******************/
    private Bracket<Integer> bracket;		// Bracket to build and display
    
    
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
        } catch (IOException e) {
            System.err.println("The provided file could not be loaded - exiting program");
            System.exit(0);
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
    private void initViewObjects (Stage stage) {
        // Parameters from bracket
    	int rounds = bracket.rounds();
    	
    	roundPanes = new BorderPane[rounds + 1];						// indexing starts at 1
    	columnPanes = new VBox[2*rounds];								// indexing starts at 1
        matchPanes = new MatchPane[bracket.matches()];					// indexing starts at 1
        
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
        int matchIndex = 1; // keeps track of next match to be added
        for(int i = 1; i < rounds; i++) {
        	roundPanes[i].setCenter(roundPanes[i+1]);
        	
        	for(int j = 1; j <= Math.pow(2, rounds-i-1); j++) {
        		matchPanes[matchIndex] = new MatchPane<Integer>
        			(matchIndex, bracket.getMatch(matchIndex), this);
        		columnPanes[2*i - 1].getChildren().add(matchPanes[matchIndex]);
        		matchIndex++;
        		
        		matchPanes[matchIndex] = new MatchPane<Integer>
    				(matchIndex, bracket.getMatch(matchIndex), this);
        		columnPanes[2*i].getChildren().add(matchPanes[matchIndex]);
        		matchIndex++;
        	}
        	
        	roundPanes[i].setRight(columnPanes[2*i - 1]);
        	roundPanes[i].setLeft(columnPanes[2*i]);
        }
        
        // add championship match
        columnPanes[2*rounds - 1].getChildren().add(new MatchPane<Integer>
        		(matchIndex, bracket.getMatch(matchIndex), this));
        roundPanes[rounds].setCenter(columnPanes[2*rounds - 1]);
        
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
        this.stage.setScene(scene);
        this.stage.show();
    }
    
    
    /*******************
     * Getters and Setters
     *******************/
    
    public void matchPaneCallBack() {
        System.out.println("--------------------------");
        System.out.println("-Called me back");
        System.out.println("--------------------------");
        System.out.println(bracket);
        
    }

}

