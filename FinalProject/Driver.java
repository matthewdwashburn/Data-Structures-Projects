package datafinal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * COS212 Final Project
 * 
 * Driver calls all the Panel classes and runs the GUI
 * 
 * @author Ethan Freebersyser
 * @version 1
 *
 */

public class Driver extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		 try { 
			 GridPane root = new GridPane();
			 Scene scene = new Scene(root, 810, 500);
			 primaryStage.setScene(scene);
			 //primaryStage.setFullScreen(true);

			 //root.setGridLinesVisible(true);
			 root.setHgap(5.0);
			 root.setVgap(5.0);			 
			 
			 //root.setStyle("-fx-background-color:  #686665");
			 root.add(new InDorm_Panel(), 0, 0);
			 
			 root.add(new Task_Panel(), 0, 1);
			 
			 root.add(new Suitcase_Panel(), 1, 0);
			 
			 root.add(new Reminder_Panel(), 1, 1);
			 
			 primaryStage.show();
			 
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	}
	
	public static void 
	main(String[] args)
	{
	    launch(args);
	} // main

}
