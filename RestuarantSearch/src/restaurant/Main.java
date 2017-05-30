/*
 * Class Name : RestController
 * Author : Rohith Uppala 
 * Version : 1.1  
 * Description : This class used for initialize,set and show FXML file as  GUI
 * 
 * 
 * */
package restaurant;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	//private Stage primaryStage;
	//private BorderPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		//this.primaryStage=primaryStage;
		//this.primaryStage.setTitle("Restuarant Search");
		//showMainView();

		Parent parent = FXMLLoader.load(getClass().getResource("RestSearch.fxml"));
		Scene scene = new Scene(parent);
		// The window title
		primaryStage.setTitle("Restuarant Search");			
		// What scene will we start with?
		primaryStage.setScene(scene);
		// Show it!
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
