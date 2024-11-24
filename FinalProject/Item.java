package datafinal;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import javafx.scene.image.Image;

/**
 * COS212 Final Project
 * 
 * Item is the object that will be stored in our Tree and stack and doubly linked list
 * 
 * @author Ethan Freebersyser
 * @version 1
 *
 */

public class Item implements Comparable<Item>{

	//--------------Attributes--------------
	private String name;
	private String description;
	private Double size;
	private Image image;
	
	//---------------Constructor--------------
	public Item (String name, String description, Double size, String filePath) {
		setName(name);
		setDescription(description);
		setSize(size);
		setImage(filePath);
	}
	
	//--------------Setters and getters--------------
	public void setName(String name) throws IllegalArgumentException {
		if(name == null || name == "") {
			throw new IllegalArgumentException("Name Cannot Be Empty!");
		} else {
		this.name = name;
		}
	}

	
	public void setDescription(String description) throws IllegalArgumentException{
		if(description == null || description == "") {
			throw new IllegalArgumentException("Description Cannot Be Empty!");
		} else {
		this.description = description;
		}
	}
	
	public void setSize(Double size) {
		this.size = size;
	}
	
	
	public void setImage(String filePath) throws IllegalArgumentException {
		try {
	        Paths.get(filePath);
	    } catch (InvalidPathException | NullPointerException ex) {
	    	 throw new IllegalArgumentException("Image Not Found!");
	    }
		
		this.image =  new Image(filePath);
	}

	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Double getSize() {
		return size;
	}
	
	public Image getImage() {
		return image;
	}
	
	//--------------Other Methods--------------
	
	@Override
	public String toString() {
	      return "Name: " + name + "\nSize: " + size + "\nDescription: " + description + "\nImage: ";
	}
	
	@Override
	public int compareTo(Item o) {
		if (this.size > o.size) {
			return 1;
		} else if (this.size < o.size){
			return -1;
		} else {
			return 0;
		}
	}	
}//Item
