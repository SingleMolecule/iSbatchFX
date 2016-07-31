/*******************************************************************************
 * 	This file is part of iSBatchFX.
 *
 *     IiSBatchFX is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     iSBatchFX is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with iSBatchFX.  If not, see <http://www.gnu.org/licenses/>. 
 *     
 *      Copyright 2015,2016 Victor Caldaas
 *******************************************************************************/
package javaFXTutorial.creatingAlertBoxes;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultiLayoutExample extends Application {

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
        
        HBox topmenu = new HBox();
        Button buttonA = new Button("File");
        Button buttonB = new Button("Edit");
        Button buttonC = new Button("View");
        
        topmenu.getChildren().addAll(buttonA, buttonB,buttonC);
        
        
        
        VBox leftmenu = new VBox();
        Button buttonD = new Button("File");
        Button buttonE = new Button("Edit");
        Button buttonF = new Button("View");
        
        leftmenu.getChildren().addAll(buttonD, buttonE,buttonF);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topmenu);
        borderPane.setLeft(leftmenu);
        
       
        Scene scene = new Scene(borderPane, 300, 250);
        window.setScene(scene);
        window.show();
    }

	private void  closeProgramm() {
		Boolean closewindow = ConfirmBox.display("Close Box","Sure you want to close?");
		if (closewindow) window.close();

	}

}
