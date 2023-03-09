/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.dropbox.core.DbxException;
import com.esprit.entities.Cours;
import com.esprit.entities.Utilisateur;
import com.esprit.services.ServiceAPIDropbox;
import com.esprit.services.ServiceCours;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
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
    @FXML
    private Label error;

    ServiceCours spCours = new ServiceCours(); 
    private String id_Login ;
    

     private ServiceAPIDropbox dropbox;

    public void setId_Login(String id_Login) {
        System.out.println(id_Login);
        this.id_Login = id_Login;
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            dropbox = new ServiceAPIDropbox();
        } catch (DbxException ex) {
            Logger.getLogger(InterfaceAjouterCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<String> items = categorie.getItems();
        items.addAll("Web", "Cloud", "Dev","Reseau");
    }    

    @FXML
    private void ajouterNouveauChapitre(MouseEvent event) throws IOException, GeneralSecurityException, DbxException {
        
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Cours Ajouter");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("le Cours "+ nom.getText()+" à été ajoutée");
        dialog.getDialogPane().getButtonTypes().add(type);
        
        if(!(nom.getText().equals("")) && !(categorie.getValue().equals(""))){
            
            
            Cours C = new Cours(nom.getText(),id_Login,categorie.getValue(),0);
            C.setId(UUID.randomUUID().toString());
        
            dropbox.ajouterCoursFolderDropbox(C.getId());
            
            spCours.ajouter(C);
            
            dialog.showAndWait();
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceAjouterChapitre.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);  
            InterfaceAjouterChapitreController Chapitre = loader.getController();
            Chapitre.setId_Cours(C.getId());
        }
        else{
            error.setText("tu n'as pas enter le nom ou la categorie");
        }
    }

    @FXML
    private void retourner(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceCours.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root);  
        InterfaceCoursController Cour = loader.getController();
    }
    
}
