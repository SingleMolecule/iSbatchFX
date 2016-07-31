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
 *      Copyright 2015,2016 Victor Caldas
 *******************************************************************************/

package net.imagej.plugin.iSBatchFX;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.JFrame;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.imagej.ImageJ;
import net.imagej.plugin.iSBatchFX.model.Person;
import net.imagej.plugin.iSBatchFX.model.PersonListWrapper;
import net.imagej.plugin.iSBatchFX.view.PersonEditDialogController;
import net.imagej.plugin.iSBatchFX.view.PersonOverviewController;
import net.imagej.plugin.iSBatchFX.view.RootLayoutController;
import net.imagej.plugin.iSBatchFX.view.TreeViewController;

import org.scijava.log.LogService;
import org.scijava.plugin.Parameter;

public class MainAppFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	@Parameter
	//private MainApp mainApp;
    private LogService log;
	private ImageJ ij;
    private JFXPanel fxPanel;
    private BorderPane rootLayout;
    private Stage primaryStage;
    
    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }
    
   
    public MainAppFrame(ImageJ ij) {
    	//MainApp mainApp = new MainApp();
    	//this.mainApp = mainApp;
    	ij.context().inject(this);
    	//Start empty
    	this.getPersonData().clear();
           this.setPersonFilePath(null);
    }
       


	/**
     * Create the JFXPanel that make the link between Swing (IJ) and JavaFX plugin.
     */
    public void init() {
        this.fxPanel = new JFXPanel();
        this.add(this.fxPanel);
        this.setVisible(true);

        //this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
        // The call to runLater() avoid a mix between JavaFX thread and Swing thread.
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
                showPersonOverview();
                showTreeView();
           }
        });

    }

	@SuppressWarnings("deprecation")
	public void initFX(JFXPanel fxPanel) {
        // Init the root layout
		try {
       	 // Load root layout from fxml file.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
           rootLayout = (BorderPane) loader.load();
           
           // Give the controller access to the main app.
           RootLayoutController controller = loader.getController();
           controller.setMainAppFrame(this);
                 
           // Show the scene containing the root layout.
           Scene scene = new Scene(rootLayout);
           this.fxPanel.setScene(scene);
           this.fxPanel.show();

           // Resize the JFrame to the JavaFX scene
           this.setSize((int) scene.getWidth(), (int) scene.getHeight());

       } catch (IOException e) {
           e.printStackTrace();
}				
    }
    
    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

         // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
         // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Shows the person overview inside the root layout.
     */
    public void showTreeView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/TreeView.fxml"));
            AnchorPane treeView = (AnchorPane) loader.load();

         // Set person overview into the center of root layout.
            rootLayout.setLeft(treeView);
            
         // Give the controller access to the main app.
            TreeViewController treeController = loader.getController();
            treeController.setMainAppFrame(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppFrame.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainAppFrame.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainAppFrame.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            this.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            this.setTitle("AddressApp");
        }
    }
    
    /**
    * Loads person data from the specified file. The current person data will
    * be replaced.
    * 
    * @param file
    */
   public void loadPersonDataFromFile(File file) {
       try {
           JAXBContext context = JAXBContext
                   .newInstance(PersonListWrapper.class);
           Unmarshaller um = context.createUnmarshaller();

           // Reading XML from the file and unmarshalling.
           PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

           personData.clear();
           personData.addAll(wrapper.getPersons());

           // Save the file path to the registry.
           setPersonFilePath(file);

       } catch (Exception e) { // catches ANY exception
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Could not load data");
           alert.setContentText("Could not load data from file:\n" + file.getPath());

           alert.showAndWait();
       }
   }

   /**
    * Saves the current person data to the specified file.
    * 
    * @param file
    */
   public void savePersonDataToFile(File file) {
       try {
           JAXBContext context = JAXBContext
                   .newInstance(PersonListWrapper.class);
           Marshaller m = context.createMarshaller();
           m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

           // Wrapping our person data.
           PersonListWrapper wrapper = new PersonListWrapper();
           wrapper.setPersons(personData);

           // Marshalling and saving XML to the file.
           m.marshal(wrapper, file);

           // Save the file path to the registry.
           setPersonFilePath(file);
       } catch (Exception e) { // catches ANY exception
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Could not save data");
           alert.setContentText("Could not save data to file:\n" + file.getPath());

           alert.showAndWait();
       }
   }
}
