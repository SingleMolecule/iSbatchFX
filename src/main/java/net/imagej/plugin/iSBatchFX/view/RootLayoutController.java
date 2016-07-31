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
package net.imagej.plugin.iSBatchFX.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import net.imagej.plugin.iSBatchFX.MainApp;
import net.imagej.plugin.iSBatchFX.MainAppFrame;
import net.imagej.plugin.iSBatchFX.util.AboutPane;

public class RootLayoutController {

    // Reference to the main application
    private MainAppFrame mainAppFrame;
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp2
     */
    public void setMainApp(MainApp mainApp2) {
        this.mainApp = mainApp2;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainAppFrame.getPersonData().clear();
        mainAppFrame.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainAppFrame.getPrimaryStage());

        if (file != null) {
            mainAppFrame.loadPersonDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File personFile = mainAppFrame.getPersonFilePath();
        if (personFile != null) {
        	mainAppFrame.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }
    
    
    @FXML
    /**
     * Load data into MainAppFrame
     */

    private void handeLoadData(){
    	mainAppFrame.showPersonOverview();
    }
    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        //TODO: Fix this stage problem
        File file = fileChooser.showSaveDialog(mainAppFrame.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainAppFrame.savePersonDataToFile(file);
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {

    	AboutPane.display();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

	public void setMainAppFrame(MainAppFrame mainAppFrame) {
		this.mainAppFrame = mainAppFrame;
		
	}
}