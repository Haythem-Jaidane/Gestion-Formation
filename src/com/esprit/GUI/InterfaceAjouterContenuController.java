/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceAjouterContenuController implements Initializable {

    @FXML
    private Button filechose;
    @FXML
    private Button contnu;
    @FXML
    private Button chapitre;
    @FXML
    private Button terminer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fileChoseClick(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println(File);
        }
    }

    @FXML
    private void autreContenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAjouterContenu.fxml"));
        Parent root = loader.load();
        filechose.getScene().setRoot(root);  
        InterfaceAjouterContenuController ajouter = loader.getController();
    }

    @FXML
    private void NouveauCapitre(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAjouterChapitre.fxml"));
        Parent root = loader.load();
        filechose.getScene().setRoot(root);  
        InterfaceAjouterChapitreController ajouter = loader.getController();
    }

    @FXML
    private void terminerAjout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceCours.fxml"));
        Parent root = loader.load();
        filechose.getScene().setRoot(root);  
        InterfaceCoursController ajouter = loader.getController();
    }
    
}
