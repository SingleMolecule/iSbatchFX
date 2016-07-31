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
package net.imagej.plugin.iSBatchFX.util;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutPane {
	
	public static void display(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("About iSBatchFX");
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText("Author: Victor Caldas \n"
				+ "iSBatch FX - 2016");
		
		Button buttom = new Button("Dismiss");
		
		buttom.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, buttom);
		layout.setAlignment(Pos.CENTER);
		
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

    	
	}
}
