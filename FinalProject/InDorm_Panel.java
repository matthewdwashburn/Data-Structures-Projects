package datafinal;

import java.util.ArrayList;
import java.util.List;
import datafinal.InDorm.BTNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * COS212 Final Project
 * 
 * InDorm_Panel holds and creates GUI elements for the InDorm class
 * 
 * @author Ethan Freebersyser
 * @version 1
 *
 */

public class InDorm_Panel extends Pane implements EventHandler<ActionEvent>{
	//--------------Attributes--------------
	private Label titleL;
	private ScrollPane SP;
	private FlowPane FP;
	private TextArea itemTA;
	private ImageView imageV;
	private InDorm BST;
	private Button addItemB;
	private Button deleteItemB;
	
	//--------------Popup Attributes--------------
	private Stage popupAddS;
	private TextField userNameTF;
	private Label userNameL;
	private TextField userDescriptionTF;
	private Label userDescriptionL;
	private TextField userSizeTF;
	private Label userSizeL;
	private TextField userImageTF;
	private Label userImageL;
	private Button submitAddB;
	private Button submitDeleteB;
	private Item userI;
	
	public InDorm_Panel() {
		//--------------Initializing FlowPane Elements--------------
		FP = new FlowPane();
		BST = new InDorm();
		//uncomment if you want elements in the BST on start
	    BST.insert(new Item("Item1", "Description1", 2.0, "TV_Stand.jpg"));
	    //BST.insert(new Item("Item2", "Description2", 3.0, "pad_compose.gif"));
		//BST.insert(new Item("Item3", "Description3", 1.0, "pad_compose.gif"));
		//BST.insert(new Item("Item4", "Description4", 5.0, "pad_compose.gif"));
		//BST.insert(new Item("Item5", "Description5", 4.0, "pad_compose.gif"));

		titleL = new Label("In Dorm");
		
		addItemB = new Button("Add Item");
		addItemB.setOnAction(this);
		
		deleteItemB = new Button("Delete Item");
		deleteItemB.setOnAction(this);
		
		//--------------Adding Elements To Pane--------------
		initializeFlowPaneElements(BST);
		
		getChildren().add(titleL);
		SP = new ScrollPane(FP);
		FP.setMaxSize(295,320);
		getChildren().add(SP);
		getChildren().add(addItemB);
		getChildren().add(deleteItemB);
		//--------------Setting ScrollPane Parameters--------------
		SP.setPrefSize(300, 320);
		SP.relocate(40, 20);
		//--------------Setting Pane element Parameters--------------
		titleL.relocate(170, 0);
		addItemB.relocate(130, 350);
		deleteItemB.relocate(200, 350);
	}
	
	 /**
     * Private method to insert new GUI elements for each item in the BST into the FlowPane
     * @param root The starting node
     */
	private void initializeFlowPaneElements(InDorm BST) {
		if (BST.getRoot() == null) {
			return;
	    }
	    List<BTNode> currentLevel = new ArrayList<>();
	    currentLevel.add(BST.getRoot());
	    while (!currentLevel.isEmpty()) {
	    	List<BTNode> nextLevel = new ArrayList<>();
	        for (BTNode currentNode : currentLevel) {
	        	//--------------Initializing FlowPane Elements--------------
	        	itemTA = new TextArea(currentNode.getData().toString());
	        	itemTA.setPrefColumnCount(23);
	        	itemTA.setWrapText(true);
	        	itemTA.setPrefRowCount(4);
	        	itemTA.setEditable(false);
	        	FP.getChildren().add(itemTA);
	        	
	        	imageV = new ImageView(currentNode.getData().getImage());
	        	imageV.setPreserveRatio(true);
	        	imageV.setFitWidth(100);
	        	imageV.setFitHeight(100);
	        	FP.getChildren().add(imageV);
	        	
	        	if (currentNode.getLeft() != null) {
	        		nextLevel.add(currentNode.getLeft());
	        	}
	        	if (currentNode.getRight() != null) {
	        		nextLevel.add(currentNode.getRight());
	        	}
	        }
	        currentLevel = nextLevel;
	    }
	}
	 
	/**
     * Private method to clear the FlowPane
     */
	private void clearFlowPaneElements() {
		FP.getChildren().clear();
	}
	 
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == addItemB) {
			//--------------Initializing Pane Elements--------------
			popupAddS = new Stage();;
			userNameTF = new TextField();
			userNameL = new Label("Enter a Name: ");
			userDescriptionTF  = new TextField();
			userDescriptionL = new Label("Enter a description: ");
			userSizeTF  = new TextField();
			userSizeL = new Label("Enter a size (inches): ");
			userImageTF  = new TextField();
			userImageL = new Label("Enter a filepath to an image: ");
			submitAddB = new Button("Submit");
			submitAddB.setOnAction(this);
			
			//-----------Setting Pane element Parameters------------
			userNameTF.relocate(90, 5);
			userNameL.relocate(10, 5);
				
			userDescriptionTF.relocate(115, 35);
			userDescriptionL.relocate(10, 35);
				
			userSizeTF.relocate(120, 65);
			userSizeL.relocate(10, 65);
				
			userImageTF.relocate(165, 95);
			userImageL.relocate(10, 95);
		
			submitAddB.relocate(150, 125);
				
			//-----------Setting Pane Parameters------------
				
			Pane popupP = new Pane(userNameTF, userNameL, userDescriptionTF, userDescriptionL,
									   userSizeTF, userSizeL,userImageTF, userImageL, submitAddB);  
			popupP.setPrefSize(350, 160);
				
			//-----------Setting Scene Parameters------------
			popupAddS.setScene(new Scene(popupP));
			popupAddS.setTitle("Add Item");
			popupAddS.centerOnScreen();
				
			popupAddS.show();
		} else if (e.getSource() == submitAddB) {
			try {
				//creates a new item and adds it to a TextArea and ImageViewand sets the TextAreas parameters
				userI = new Item(userNameTF.getText(), userDescriptionTF.getText(), Double.parseDouble(userSizeTF.getText()), userImageTF.getText());
				itemTA = new TextArea(userI.toString());
				imageV = new ImageView(userI.getImage());
				
				//-----------Setting element Parameters------------
				itemTA.setPrefColumnCount(25);
				itemTA.setWrapText(true);
				itemTA.setPrefRowCount(4);
				itemTA.setEditable(false);
				
				imageV.setPreserveRatio(true);
	            imageV.setFitWidth(100);
	            imageV.setFitHeight(100);
	            
	            //-----------Adding to FlowPane------------
                FP.getChildren().add(itemTA);
                FP.getChildren().add(imageV);
                
                BST.insert(userI);
                
				popupAddS.close();
			} catch(NumberFormatException ex) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Not a Number");
    			alert.setContentText("A number was expected " + ex.getMessage());
    			alert.show();
    		} catch(IllegalArgumentException ex) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Illegal argument");
    			alert.setContentText(ex.getMessage());
   	      	}
		} else if (e.getSource() == deleteItemB) {
			//-----------Initializing Pane Elements-----------
			popupAddS = new Stage();;
			userSizeTF  = new TextField();
			userSizeL = new Label("Enter a size (inches) that you want to delete: ");
			submitDeleteB = new Button("Submit");
			submitDeleteB.setOnAction(this);
			
			//-----------Setting Pane element Parameters-----------
			userSizeTF.relocate(100, 20);
			userSizeL.relocate(60, 0);

			submitDeleteB.relocate(150, 60);
				
			//-----------Setting Pane Parameters------------
				
			Pane popupP = new Pane(userSizeTF, userSizeL, submitDeleteB);  
			popupP.setPrefSize(350, 100);
				
			//-----------Setting Scene Parameters------------
			popupAddS.setScene(new Scene(popupP));
			popupAddS.setTitle("Add Item");
			popupAddS.centerOnScreen();
				
			popupAddS.show();
		} else if(e.getSource() == submitDeleteB) {
			try {
				if (!BST.isEmpty()) {
					BST.deleteSize(Double.parseDouble(userSizeTF.getText()));
					clearFlowPaneElements();
					initializeFlowPaneElements(BST);
				} else {
					throw new NullPointerException("There are no tasks to delete");
				}			
			} catch (NumberFormatException ex) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Not a Number");
    			alert.setContentText("A number was expected " + ex.getMessage());
    			alert.show();
    		} catch (NullPointerException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Null Task Tree");
    			alert.setContentText(ex.getMessage());
    			alert.show();			
			} 
		}	
	}
}//InDorm_Panel
