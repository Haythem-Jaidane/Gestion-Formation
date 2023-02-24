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
import java.util.UUID;
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
    private String id_Login ;

    public void setId_Login(String id_Login) {
        System.out.println(id_Login);
        this.id_Login = id_Login;
    }
    
    
    
    
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
        
        Cours C = new Cours(nom.getText(),id_Login,categorie.getValue(),0);
        C.setId(UUID.randomUUID().toString());
        
        spCours.ajouter(C);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceAjouterChapitre.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);  
        InterfaceAjouterChapitreController Chapitre = loader.getController();
        Chapitre.setId_Cours(C.getId());
    }

    @FXML
    private void retourner(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceCours.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);  
        InterfaceCoursController Cour = loader.getController();
    }
    
}
