package javaFXTutorial.creatingAlertBoxes;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GridPaneExample extends Application {

    Stage window;
    Button button;
    Button closeButton;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("iSBatcFX");
        
       GridPane gridPane = new GridPane();
       gridPane.setPadding(new Insets(10,10,10,10));
       gridPane.setVgap(8);
       gridPane.setHgap(10);
       
       
       Label name = new Label("User name");
       GridPane.setConstraints(name,0,0);
       
       TextField input  = new TextField("Jon Doe");
       GridPane.setConstraints(input, 1,0);
       
       Label pass = new Label("password");
       GridPane.setConstraints(pass,0,1);
       
       TextField passInput  = new TextField();
       passInput.setPromptText("Type your password");
       GridPane.setConstraints(passInput, 1,1);
       
       Button login = new Button("Login");
       GridPane.setConstraints(login, 1,2);
       
       gridPane.getChildren().addAll(name, input, pass, passInput, login);
       
       Scene scene = new Scene(gridPane, 300,300);
       window.setScene(scene);
       window.show();
       
       
       
        window.show();
    }

	private void  closeProgramm() {
		Boolean closewindow = ConfirmBox.display("Close Box","Sure you want to close?");
		if (closewindow) window.close();

	}

}
