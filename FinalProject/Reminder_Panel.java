package datafinal;

import java.util.Timer;
import java.util.TimerTask;
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
 * Reminder_Panel holds and creates GUI elements for the reminder class
 * 
 * @author Ethan Freebersyser
 * @version 1
 *
 */

public class Reminder_Panel extends Pane implements EventHandler<ActionEvent>{
	//--------------Attributes--------------
	private Timer timer;
	private Label doNotForgetL;
	private TextArea messageTA;
	private Button addMessageB;
	private Button deleteMessageB;
	private Reminder reminder;
	//--------------Pop Up Attributes--------------
	private Stage popupAddS;
	private TextField userMessageTF;
	private Label userMessageL;
	private Button submitB;
	
	//--------------Setters and Getters for Reminder--------------
	public void setReminder(Reminder list) {
		reminder = list;
	}
	public Reminder getReminder() {
		return reminder;
	}

		
	public Reminder_Panel(){
		//--------------Initializing Pane Elements--------------
		//uncomment if you want elements in the linked list on start
		//Reminder reminder = new Reminder();
		//reminder.getTaskList().add("Task 1");
		//reminder.getTaskList().add("Task 2");
		//reminder.getTaskList().add("Task 3");
		//setReminder(reminder);
		
		setReminder(new Reminder());
		
		messageTA = new TextArea();
		startTimer(messageTA, reminder);
		
		doNotForgetL = new Label("!!DO NOT FORGET!!");
		
		addMessageB = new Button("Add Messsage");
		addMessageB.setOnAction(this);
		
		deleteMessageB = new Button("Delete Message");
		deleteMessageB.setOnAction(this);
		
		//-----------Setting Pane element Parameters------------
		doNotForgetL.relocate(200, 0);
		addMessageB.relocate(155, 70);
		deleteMessageB.relocate(255, 70);
				
		messageTA.relocate(60, 20);
		messageTA.setPrefColumnCount(30);
		messageTA.setWrapText(true);				
		messageTA.setPrefRowCount(1);
		messageTA.setEditable(false);
		
		//----------------Adding Elements To Pane---------------
		getChildren().add(doNotForgetL);
		getChildren().add(messageTA);
		
		getChildren().add(addMessageB);
		getChildren().add(deleteMessageB);
	}
	

	public void startTimer(TextArea TA, Reminder reminder) {
        timer = new Timer();
        timer.schedule(new MoveThroughListTask(TA, reminder), 0, 3000); // 3000 milliseconds = 3 seconds
    }
	
	public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private class MoveThroughListTask extends TimerTask {
    	private TextArea TA;
    	private Reminder reminder;
    	
    	public MoveThroughListTask(TextArea TA, Reminder reminder) {
    		this.TA = TA;
    		this.reminder = reminder;
    	}
    	@Override
        public void run() {
            if (!reminder.getTaskList().isEmpty()) {
                // Do whatever you want with the current element
            	TA.setText(reminder.getTaskList().get(reminder.getCurrIndex()));
                // Move to the next element
                reminder.setCurrIndex(( reminder.getCurrIndex() + 1) % reminder.getTaskList().size());
            }
        }
    }

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == addMessageB) {
			//--------------Initializing  Elements--------------
			popupAddS = new Stage();
			userMessageTF = new TextField();
			userMessageL = new Label("New Message: ");
	        submitB = new Button("Submit");
	        submitB.setOnAction(this);
	        
	        //-----------Setting Pane element Parameters------------
	        userMessageTF.relocate(90, 10);
	        userMessageL.relocate(10, 10);
			submitB.relocate(120, 40);
			
			//-----------Setting Pane Parameters------------
	        Pane popupP = new Pane(userMessageTF, userMessageL, submitB);  
			popupP.setPrefSize(280, 80);
			
			//-----------Setting Scene Parameters------------
			popupAddS.setScene(new Scene(popupP));
			popupAddS.setTitle("Add Task");
			popupAddS.centerOnScreen();
			
			popupAddS.show();
		} else if (e.getSource() == submitB) {
			try {
				//--------------Adding Message--------------
				//checks to see if the user entered a valid String
				if (!userMessageTF.getText().isEmpty()) {
					reminder.getTaskList().add(userMessageTF.getText());
					messageTA.setText(reminder.getTaskList().get(reminder.getCurrIndex()));
					popupAddS.close();
				} else {
					throw new IllegalArgumentException("The message cannot be empty");
				}			
			} catch (IllegalArgumentException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Illegal Argument Exception");
    			alert.setContentText(ex.getMessage());
    			alert.show();
			}			
		} else if (e.getSource() == deleteMessageB) {
			try {
				//checks if the list is empty before deleting
				if (!reminder.getTaskList().isEmpty()) {
					reminder.getTaskList().remove(messageTA.getText());
					messageTA.setText("");
				} else {
					throw new IllegalArgumentException("There are no Messages to delete");
				}
				
			} catch (IllegalArgumentException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Illegal Argument Exception");
    			alert.setContentText(ex.getMessage());
    			alert.show();
			}			
		}	
	}//handle
}//Reminder_Panel
