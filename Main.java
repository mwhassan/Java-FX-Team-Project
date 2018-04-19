package application;

import java.io.IOException;
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
     * Private View Variables
     *******************/
    private Stage stage;                    // The stage
    private Scene scene;                    // The scene
    
    private VBox round;              // Sub-layouts for each round
    private MatchPane[] matchPanes;               // Groups submit buttons with scores and team label
  
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
        initControlObjects();
        initViewObjects(375,115);
    }
    
    private void initControlObjects() {
        try {
            bracket = new Bracket<Integer>("TeamList.txt");
        } catch (IOException e) {
            System.err.println("The provided file could not be loaded");
        }
        
        
        System.out.println("Bracket size = " + bracket.size());
        System.out.println("Bracket rounds = " + bracket.rounds());
        System.out.println("Bracket match one team one = " + bracket.getMatchTeam(0, teamSpot.TeamOne));
        System.out.println("Bracket match one team two = " + bracket.getMatchTeam(0, teamSpot.TeamTwo));
        
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
        int numRounds = bracket.rounds();
        
        this.columns = 2*numRounds-1;
        
        
        round = new VBox;
        matchPanes = new MatchPane[(int)Math.pow(2, numRounds)-1];

        primaryLayout.getChildren().add(new MatchPane(1,"Team Biscuits","Team Crackers"));
        scene = new Scene(primaryLayout,width,height);
        scene.getStylesheets().add("/application/application.css");
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    
    /*******************
     * Getters and Setters
     *******************/

}





