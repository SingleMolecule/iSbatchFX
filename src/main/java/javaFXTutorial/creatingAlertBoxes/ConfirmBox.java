package javaFXTutorial.creatingAlertBoxes;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

	static boolean answer;
	
    public static boolean display(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
       
        Button yesbt = new Button("Yes");
        Button nobt = new Button("No");
        
        yesbt.setOnAction(e -> {
            answer = true;
            window.close();                 
        }  	
        );
        
        
       nobt.setOnAction(e -> {
    	   answer = false;
    	   window.close();
    	   
       });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesbt, nobt);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return answer;
    }

}