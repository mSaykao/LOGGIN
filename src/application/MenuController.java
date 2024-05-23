package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MenuController {
	@FXML
	private Menu menu;

	@FXML
	private MenuItem chooseImage;

	@FXML
	private MenuItem clearImage;

	@FXML
	private ImageView image;

	private Stage stage;

	@FXML
	public void initialize() {
		menu.setOnShowing(event -> {
			if (image.getImage() == null) {
				clearImage.setDisable(true);
			}
		}); 
		
		chooseImage.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			
			// Set extension filter
			ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.png", "*.jpg", "*.jpeg");

			fileChooser.getExtensionFilters().add(extFilter);

			// Show a file open dialog
			File selectedFile = fileChooser.showOpenDialog(stage);

			InputStream fileInputStream;
			
			try {
				fileInputStream = new FileInputStream(selectedFile);
				image.setImage(new Image(fileInputStream));
				clearImage.setDisable(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		clearImage.setOnAction(event -> {
			image.setImage(null);
		});
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
