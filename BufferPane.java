package application;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BufferPane extends GridPane {

	
	/***********************************************************
     * Private Constants
     ***********************************************************/
	// GridPane as a whole
    private static final Integer MV_PANE_INSETS = 10; // Insets amount
    private static final Integer MV_PANE_VERT_GAP = 2; // Vertical gap
    private static final Integer MV_PANE_HORZ_GAP = 5; // Horizontal gap
    
    private static final Integer BP_MV_WIDTH = 255;
    private static final Integer BP_MV_HEIGHT = 97;
    
	protected Label lblSpacer;
	
	
	private static final String BP_SPACER = "";
	
	public BufferPane() {
		
		 // Set pane specific fields
        this.setPadding(new Insets(MV_PANE_INSETS, MV_PANE_INSETS, MV_PANE_INSETS, MV_PANE_INSETS));
        this.setVgap(MV_PANE_VERT_GAP);
        this.setHgap(MV_PANE_HORZ_GAP);
        
		lblSpacer = new Label();
		
		lblSpacer.setMinSize(BP_MV_WIDTH, BP_MV_HEIGHT);
		lblSpacer.setMaxSize(BP_MV_WIDTH, BP_MV_HEIGHT);
		lblSpacer.setText("BP_SPACER");
		
		this.add(lblSpacer,  1,  1, 1, 1);
	}
}
