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


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.imagej.plugin.iSBatchFX.MainAppFrame;
import net.imagej.plugin.iSBatchFX.model.Person;


public class TreeViewController {
    @FXML
    private TreeView<String> treeView;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    // Reference to the main application.
    private MainAppFrame mainAppFrame;
    private Stage primaryStage;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TreeViewController() {

    

    }    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tree View Sample");        
        
        TreeItem<String> rootItem = new TreeItem<String> ("Inbox");

        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
            rootItem.getChildren().add(item);
        }        
        treeView = new TreeView<String> (rootItem);        
        StackPane root = new StackPane();
        root.getChildren().add(treeView);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
    @FXML
	public void setMainAppFrame(MainAppFrame mainAppFrame) {
		this.mainAppFrame = mainAppFrame;
        
        TreeItem<String> rootItem = new TreeItem<String> ("Inbox");

       
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
            rootItem.getChildren().add(item);
        }        
        rootItem.setExpanded(true);
        
        treeView.setRoot(rootItem);
        

      
        
    }
		
	}
  
    
  
