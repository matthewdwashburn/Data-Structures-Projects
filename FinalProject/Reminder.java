package datafinal;

import java.util.LinkedList;

/**
 * COS212 Final Project
 * 
 * Reminder holds a doubly linked list of type string and the current index in that list
 * 
 * @author Matthew Washburn
 * @version 1
 *
 */

public class Reminder {
	//--------------Attributes--------------
	private LinkedList<String> taskList;
	private int currIndex;
	
	//--------------Constructor--------------
	public Reminder() {
		taskList = new LinkedList<String>();
		currIndex = 0;
	}
	
	//--------------Setters and Getters--------------
	public void setTaskList(LinkedList<String> taskList) throws IllegalArgumentException {
		if (taskList.isEmpty()) {
			throw new IllegalArgumentException("List cannot be empty");
		} else {
			this.taskList = taskList;
		}	
	}
	
	public void setCurrIndex(int newIndex) {
		currIndex = newIndex;
	}
	
	public LinkedList<String> getTaskList() {
		return taskList;
	}
	
	public int getCurrIndex() {
		return currIndex;
	}
}//Reminder
