package javaFXTutorial.creatingAlertBoxes;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Button button;
    Button closeButton;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("thenewboston");
        
        window.setOnCloseRequest(e -> {
        	 e.consume();
        	 closeProgramm();}
        );
        button = new Button("Click Me");
        closeButton = new Button("Close");
        
        button.setOnAction(e -> {
        	boolean result = ConfirmBox.display("Title", "message");
        	System.out.println(result);
        });
        
        closeButton.setOnAction(e-> {
       
        closeProgramm();
        });
    
        VBox layout = new VBox(10);
      
        
        
       
        layout.getChildren().addAll(button,closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }

	private void  closeProgramm() {
		Boolean closewindow = ConfirmBox.display("Close Box","Sure you want to close?");
		if (closewindow) window.close();

	}

}
