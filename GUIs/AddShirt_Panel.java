package javaFXTest;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class AddShirt_Panel extends AddApparel_Panel implements EventHandler<ActionEvent> {
	private TextField textTF;
	protected ComboBox<Shirt.Size> sizeCB;
	private Button addShirtB;
	private Wardrobe wdrb;

	public AddShirt_Panel(Wardrobe w) {
		super();
		this.wdrb = w;

		textTF = new TextField();
		textTF.setPrefColumnCount(2);

		addShirtB = new Button("Add Shirt");
		addShirtB.setOnAction(this);
		
		sizeCB = new ComboBox<Shirt.Size>();
		sizeCB.getItems().addAll(Shirt.Size.values());

		FlowPane temp = new FlowPane();
		temp.getChildren().add(new Label("Text: "));
		temp.getChildren().add(textTF);

		temp.getChildren().add(new Label("Size: "));
		temp.getChildren().add(sizeCB);
		
		getChildren().add(temp);
		getChildren().add(addShirtB);
		

	}

	/**
	 * clear - clear out text fields - though maybe it would be better to leave some
	 * values in place for easier entry of the next item.
	 */
	@Override
	protected void clear() {
		super.clear();
		textTF.setText("");
	}

	@Override
	public void handle(ActionEvent e) {
		try {
			if (e.getSource() == addShirtB) {
				// System.out.println("Button Pushed");
				Shirt a = new Shirt(colorTF.getText(), Double.parseDouble(priceTF.getText()), conditionCB.getValue(),
						sizeCB.getValue(), textTF.getText());
				wdrb.addApparel(a);
				clear();
			}
		} catch (NumberFormatException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Not a number");
			alert.setContentText("An expected number was: " + ex.getMessage());
			alert.show();
		} catch (IllegalArgumentException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Illegal Argument Exception");
			alert.show();
		}

	}

} // class AddPants_Panel