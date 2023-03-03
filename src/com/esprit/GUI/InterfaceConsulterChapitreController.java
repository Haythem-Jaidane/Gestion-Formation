/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.esprit.entities.AffichageConsulterChapitre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceConsulterChapitreController implements Initializable {


    @FXML
    private Button retourButton;
    @FXML
    private TableView<AffichageConsulterChapitre> chapitreContainer;
    @FXML
    private TableColumn<AffichageConsulterChapitre, String> titre;
    @FXML
    private TableColumn<AffichageConsulterChapitre, Button> supp;
    @FXML
    private TableColumn<AffichageConsulterChapitre, Button> Contenu;
    @FXML
    private Button ajouterChapitre;
    
    private String idCours;
    

    public void setIdCours(String idCours) {
        this.idCours = idCours;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void RetournerP(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceCours.fxml"));
        Parent root = loader.load();
        retourButton.getScene().setRoot(root);  
        InterfaceCoursController ajouter = loader.getController();
    }

}
