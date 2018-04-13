package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


public class Main extends Application {
	
	private Stage stage;					// The stage
	private Scene scene;					// The scene
	private int rounds,columns;				// Number of rounds and number of columns (for convenience)
	private HBox primaryLayout;				// Primary bracket layout
	private VBox[] sublayouts;				// Sub-layouts for each round
	private Subunit[] subunits;				// Groups submit buttons with scores and team label
	private Button[] submitButtons;			// Buttons to submit match scores
	private Label title;					// Title of bracket
	private Label[] teams;					// Team names
	private Label[] scores;					// Team scores
	private Scene display;					// Main scene of the program
	
	/**
	 * Initializes all GUI Objects
	 * 
	 * @param width
	 * @param height
	 * @param rounds
	 */
	private void init (int width, int height, int rounds) {
		// Parameters from bracket
		this.rounds = rounds;
		this.columns = 2*rounds-1;
		
		// Primary layout
		primaryLayout = new HBox(columns);
		sublayouts = new VBox[columns];
		subunits = new Subunit[(int)Math.pow(2, rounds)-1];
//		int counter = rounds;		// tracks which round we are adding
//		int add = -1;				// add to counter
//		for(VBox box : sublayouts) {
//			primaryLayout.getChildren().add(box);
//		}
		primaryLayout.getChildren().add(new Subunit(1,"Team Biscuits","Team Crackers"));
		scene = new Scene(primaryLayout,width,height);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		// TODO start a bracket from command line argument and replace test values in init()
		init(300,100,2);
	}
}