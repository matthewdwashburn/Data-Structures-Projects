package datafinal;

import datafinal.Task.BinaryMinHeap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * COS212 Final Project
 * 
 * Task_Paenl holds and creates GUI elements for the Task class
 * 
 * @author Ethan Freebersyser
 * @version 1
 *
 */

public class Task_Panel extends Pane implements EventHandler<ActionEvent>{
	//--------------Attributes--------------
	private Button addTaskB;
	private Button deleteTaskB;
	private TextArea taskDisplayTA;
	private Label curTaskL;
	private BinaryMinHeap<Task> BMH;
	
	//--------------Add Pop Up Attributes--------------
	private Stage popupS;
	private TextField userDescriptionTF;
	private Label userDescriptionL;
	private TextField userPriorityAddTF;
	private Label userPriorityAddL;
	private Button submitB;	
	
	//--------------Setters and Getters for Heap--------------
	public void setBMH(BinaryMinHeap<Task> heap) {
		this.BMH = heap;
	}
	public BinaryMinHeap<Task> getBMH() {
		return BMH;
	}
	
	public Task_Panel() {
		//--------------Initializing Pane Elements--------------
		//uncomment if you want elements in the BMH on start
		//Task[] tasks = new Task[10];
		//tasks[0] = new Task("Pig", 1);
		//tasks[1] = new Task("small", 2);
		//tasks[2] = new Task("Cow", 3);
		//tasks[3] = new Task("Sheep", 4);
		//setBMH(new BinaryMinHeap<Task>(tasks));
		
		setBMH(new BinaryMinHeap<Task>(100));

		if (!BMH.isEmpty()) {
			taskDisplayTA = new TextArea(BMH.findMin().getDescription());
		} else {
			taskDisplayTA = new TextArea();
		}

		addTaskB = new Button("Add Task");
		addTaskB.setOnAction(this);
		
		deleteTaskB = new Button("Delete Task");
		deleteTaskB.setOnAction(this);
		
		curTaskL = new Label("Current Task: ");
		
		//--------------Setting Pane element Parameters--------------
		curTaskL.relocate(170, 0);
	
		addTaskB.relocate(130, 70);
		
		deleteTaskB.relocate(200, 70);
		
		taskDisplayTA.relocate(5, 20);
		taskDisplayTA.setPrefColumnCount(30);
		taskDisplayTA.setWrapText(true);
		taskDisplayTA.setPrefRowCount(1);
		taskDisplayTA.setEditable(false);
		
		//--------------Adding Elements To Pane--------------
		getChildren().add(curTaskL);
		getChildren().add(taskDisplayTA);
		
		getChildren().add(addTaskB);
		getChildren().add(deleteTaskB);
		
	}//Task_Panel Method
	
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == addTaskB) {
			//--------------Initializing Elements--------------
			popupS = new Stage();
			userDescriptionTF = new TextField();
			userDescriptionL = new Label("New Descripition: ");
			userPriorityAddTF = new TextField();
			userPriorityAddL = new Label("New Priority: ");
	        submitB = new Button("Submit");
	        submitB.setOnAction(this);
	        
	        //--------------Setting Pane element Parameters--------------
			userDescriptionTF.relocate(110, 10);
			userDescriptionL.relocate(10, 10);
			
			userPriorityAddTF.relocate(80, 40);
			userPriorityAddL.relocate(10, 40);
			
			submitB.relocate(110, 80);
			
			//--------------Setting Pane Parameters--------------
	        Pane popupP = new Pane(userDescriptionTF, userDescriptionL,  userPriorityAddTF,userPriorityAddL, submitB);  
			popupP.setPrefSize(300, 120);
			
			//--------------Setting Scene Parameters--------------
			popupS.setScene(new Scene(popupP));
			popupS.setTitle("Add Task");
			popupS.centerOnScreen();
			
			popupS.show();
		} else if (e.getSource() == submitB) {
			try {
				//--------------Adding Task--------------
				//checks to see if the BMH has the Priority
				if (BMH.isPriortyPresent(Integer.parseInt(userPriorityAddTF.getText()))) {
					throw new IllegalArgumentException("Priority Cannot the same as a current task!");
				}
				//inserts a valid task and update the task display 
				BMH.insert(new Task(userDescriptionTF.getText(), Integer.parseInt(userPriorityAddTF.getText())));
				popupS.close();
				taskDisplayTA.setText(BMH.findMin().getDescription());
			} catch(NumberFormatException ex) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Not a Number");
    			alert.setContentText("A number was expected " + ex.getMessage());
    			alert.show();
    		} 
			catch (IllegalArgumentException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Illegal Argument Exception");
    			alert.setContentText(ex.getMessage());
    			alert.show();
			}			
		} else if (e.getSource() == deleteTaskB) {
			try {
				BMH.deleteMin();
				if (BMH.isEmpty() == false) {
					taskDisplayTA.setText(BMH.findMin().getDescription());
				} else {
					taskDisplayTA.setText(" ");
				}
			} catch (IllegalStateException ex){
					Alert alert = new Alert(Alert.AlertType.ERROR);
	    			alert.setTitle("Illegal State Exception");
	    			alert.setContentText(ex.getMessage());
	    			alert.show();
			}		
		} 
	}//Handle
	
}//Task_Panel Class
