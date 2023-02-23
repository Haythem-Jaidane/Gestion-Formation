/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.esprit.entities.Cours;
import com.esprit.entities.Utilisateur;
import com.esprit.services.ServiceCours;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceAjouterCoursController implements Initializable {

    @FXML
    private Button ajouterboutton;
    @FXML
    private Button retour;
    
    @FXML
    private TextField nom;
    @FXML
    private ComboBox<String> categorie;

    ServiceCours spCours = new ServiceCours(); 
    Utilisateur Login = new Utilisateur("gtt");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> items = categorie.getItems();
        items.addAll("Web", "Cloud", "Dev","Reseau");
    }    

    @FXML
    private void ajouterNouveauChapitre(MouseEvent event) throws IOException {
        
        spCours.ajouter(new Cours("t", nom.getText(),Login.getId_utilisateur(),categorie.getValue()));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceAjouterChapitre.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);  
        InterfaceAjouterChapitreController Chapitre = loader.getController();
    }

    @FXML
    private void retourner(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceCours.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);  
        InterfaceCoursController Cour = loader.getController();
    }
    
}
