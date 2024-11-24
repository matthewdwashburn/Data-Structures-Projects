package datafinal;

import java.util.EmptyStackException;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * COS212 Final Project
 * 
 * Suitcase_Panel holds and creates GUI elements for the Suitcase class
 * 
 * @author Ethan Freebersyser
 * @version 1
 *
 */

public class Suitcase_Panel extends Pane implements EventHandler<ActionEvent>{
	//------------------------Attributes-----------------------
	private Button addItemB;
	private Button deleteItemB;
	private TextArea topItemTA;
	private Label suitcaseL;
	private Label topItemL;
	
	private ScrollPane SP;
	private ImageView imageV;
	private Suitcase SC;
	//---------------------Add Popup Attributes-----------------
	private Stage popupAddS;
	private TextField userNameTF;
	private Label userNameL;
	private TextField userDescriptionTF;
	private Label userDescriptionL;
	private TextField userSizeTF;
	private Label userSizeL;
	private TextField userImageTF;
	private Label userImageL;
	private Button submitB;
	
	public void setSC(Suitcase SC) {
		this.SC = SC;
	}
	public Suitcase getSC() {
		return SC;
	}
	
	public Suitcase_Panel() {
		//--------------Initializing Pane Elements--------------
		suitcaseL = new Label("In Suitcase");
		
		addItemB = new Button("Add Item");
		addItemB.setOnAction(this);
		
		deleteItemB = new Button("Delete Item");
		deleteItemB.setOnAction(this);
		
		topItemL = new Label("Top Item: ");
		
		//-----------Setting Pane element Parameters------------
		suitcaseL.relocate(225, 0);
		topItemL.relocate(230, 20);
		deleteItemB.relocate(250, 350);
		addItemB.relocate(180, 350);
				
		//----------------Adding Elements To Pane---------------
		getChildren().add(suitcaseL);
		getChildren().add(topItemL);
		getChildren().add(addItemB);
		getChildren().add(deleteItemB);
		
		//-----------Initializing FlowPane Elements ------------
		//uncomment if you want elements in the BST on start
		
		SC = new Suitcase();
		setSC(SC);
		
		SC.getStack().push(new Item("Shirt", "Comfortable cotton shirt", 2.0, "TV_Stand.jpg"));
		/*
		SC.getStack().push(new Item("Pants", "Jeans", 2.0, "pad_compose.gif"));
		SC.getStack().push(new Item("Shoes", "Cool Shoes", 2.0, "pad_compose.gif"));
		*/
		
		if (!SC.getStack().isEmpty()) {
			imageV = new ImageView(SC.getStack().peek().getImage());
			topItemTA = new TextArea(SC.getStack().peek().toString());
		} else {
			imageV = new ImageView();
			topItemTA = new TextArea();
		}

		//---------Setting Pane Element Parameters----------
		imageV.setPreserveRatio(true);
		imageV.setFitWidth(200);
		imageV.setFitHeight(200);
		
		topItemTA.setWrapText(true);
		topItemTA.setEditable(false);
		topItemTA.relocate(100, 40);
		topItemTA.setPrefSize(300, 80);
		
		getChildren().add(topItemTA);
		
		SP = new ScrollPane(imageV);
		//-------------Setting ScrollPane Parameters-------------
		SP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		SP.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		SP.setMaxSize(300, 300);
		SP.relocate(150, 130);
		
		//---------------Adding ScrollPane To Pane---------------
		getChildren().add(SP);
		
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
			submitB = new Button("Submit");
			submitB.setOnAction(this);
			
			//-----------Setting Pane element Parameters------------
			userNameTF.relocate(90, 5);
			userNameL.relocate(10, 5);
				
			userDescriptionTF.relocate(115, 35);
			userDescriptionL.relocate(10, 35);
				
			userSizeTF.relocate(120, 65);
			userSizeL.relocate(10, 65);
				
			userImageTF.relocate(165, 95);
			userImageL.relocate(10, 95);
		
			submitB.relocate(150, 125);
				
			//-----------Setting Pane Parameters------------
			Pane popupP = new Pane(userNameTF, userNameL, userDescriptionTF, userDescriptionL,
									   userSizeTF, userSizeL,userImageTF, userImageL, submitB);  
			popupP.setPrefSize(350, 160);
				
			//-----------Setting Scene Parameters------------
			popupAddS.setScene(new Scene(popupP));
			popupAddS.setTitle("Add Item");
			popupAddS.centerOnScreen();
				
		
			
			popupAddS.show();
			
		} else if (e.getSource() == submitB) {
			try {
				Item userI = new Item(userNameTF.getText(), userDescriptionTF.getText(), Double.parseDouble(userSizeTF.getText()), userImageTF.getText());
				SC.getStack().add(userI);
				topItemTA.setText(SC.getStack().peek().toString());
				imageV.setImage(SC.getStack().peek().getImage());
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
    			alert.show();
   	      	}
		} else if (e.getSource() == deleteItemB) {
			try {
				SC.getStack().pop();
				if (SC.getStack().isEmpty() == false) {
					topItemTA.setText(SC.getStack().peek().toString());
					imageV.setImage(SC.getStack().peek().getImage());
				} else {
					topItemTA.setText("");
					imageV.setImage(null);
				}
			} catch (EmptyStackException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Empty Stack");
    			alert.setContentText("Cannot delete the top element since the stack is empty");
    			alert.show();
			}
		}
	}
}//Suitcase_Panel
