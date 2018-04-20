package application;

import java.io.IOException;
import java.util.ArrayList;
import application.MatchADT.teamSpot;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class Main extends Application{

    /*******************
     * Private Constants
     *******************/
    private static final Integer STAGE_WIDTH = 375;
    private static final Integer STAGE_HEIGHT = 300;
    
    
    /*******************
     * Private View Variables
     *******************/
    private Stage stage;                    // The stage
    private Scene scene;                    // The scene
    
    private VBox round;              // Sub-layouts for each round
    private ArrayList<MatchPane<Integer>> matchPanes; // Groups submit buttons with scores and team label
  
    
    /*******************
     * Private Control Variables
     *******************/
    private Bracket<Integer> bracket;
    private int columns;             // Number of rounds and number of columns (for convenience)
    
    
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
        // TODO start a bracket from command line argument and replace test values in init()
        
        try {
            initControlObjects();
        } catch (IOException e) {
            System.err.println("The provided file could not be loaded - exiting program");
            System.exit(0);
        }
        initViewObjects(STAGE_WIDTH,STAGE_HEIGHT);
    }
    
    private void initControlObjects() throws IOException {
        bracket = new Bracket<Integer>("src/application/TeamList.txt");
        //TODO: file should be first command line argument!!  not hard coded;
        
        
        
        System.out.println("Bracket size = " + bracket.size());
        System.out.println("Bracket rounds = " + bracket.rounds());
        System.out.println("Bracket match one team one = " + bracket.getMatchTeam(1, teamSpot.TeamOne));
        System.out.println("Bracket match one team two = " + bracket.getMatchTeam(1, teamSpot.TeamTwo));
        System.out.println("Bracket match team on direct = " + bracket.getMatch(1));
        System.out.println("Bracket match team on direct = " + bracket.getMatch(2));
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
    private void initViewObjects (int width, int height) {
        // Parameters from bracket
        matchPanes = new ArrayList<MatchPane<Integer>>();
                        
                        
        int numRounds = bracket.rounds();
        
                        
        this.columns = 2*numRounds-1;
        
        
        round = new VBox();
        matchPanes.add(new MatchPane<Integer>(1, bracket.getMatch(1), this));
        matchPanes.add(new MatchPane<Integer>(1, bracket.getMatch(2), this));
        matchPanes.add(new MatchPane<Integer>(1, bracket.getMatch(3), this));
        matchPanes.add(new MatchPane<Integer>(1, bracket.getMatch(4), this));
        
  
        
        //round.getChildren().add(new MatchPane<Integer,Match<Integer>>(1,bracket.getMatch(1)));
        round.getChildren().add(matchPanes.get(0));
        round.getChildren().add(matchPanes.get(1));
        round.getChildren().add(matchPanes.get(2));
        round.getChildren().add(matchPanes.get(3));
        
        
        

        //primaryLayout.getChildren().add(new MatchPane(1,"Team Biscuits","Team Crackers"));
        scene = new Scene(round,width,height);
        scene.getStylesheets().add("/application/application.css");
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
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





