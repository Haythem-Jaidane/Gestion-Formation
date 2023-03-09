/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.dropbox.core.DbxException;
import com.esprit.entities.Chapitre;
import com.esprit.services.ServiceAPIDropbox;
import com.esprit.services.ServiceChapitre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceAjouterChapitreController implements Initializable {

    @FXML
    private Button ajouterConutenu;
    
    @FXML
    private TextField CapitreText;
    
    @FXML
    private Label error;
    
    private String id_Cours;
    ServiceChapitre spChapitre = new ServiceChapitre();
    private ServiceAPIDropbox dropbox;
    

    public void setId_Cours(String id_Cours) {
        this.id_Cours = id_Cours;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dropbox = new ServiceAPIDropbox();
        } catch (DbxException ex) {
            Logger.getLogger(InterfaceAjouterCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterNouveauContenu(MouseEvent event) throws IOException, DbxException {
        
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Chapitre Ajouter");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("le Chapitre "+ CapitreText.getText()+" à été ajoutée");
        dialog.getDialogPane().getButtonTypes().add(type);
        
        if(!CapitreText.getText().equals("")){
            Chapitre chap = new Chapitre(CapitreText.getText(),id_Cours);
            chap.setId(UUID.randomUUID().toString());
            
            dropbox.ajouterChaptireFolderDropbox(id_Cours,chap.getId());
        
            spChapitre.ajouter(chap);
            
            dialog.showAndWait();
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAjouterContenu.fxml"));
            Parent root = loader.load();
            ajouterConutenu.getScene().setRoot(root);  
            InterfaceAjouterContenuController Contenu = loader.getController();
            Contenu.setId_chapitre(chap.getId());
            Contenu.setId_cours(id_Cours);
        }
        else{
           error.setText("tu n'as pas enter le nom");
        }
    }
    
}
