/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.esprit.entities.Chapitre;
import com.esprit.services.ServiceChapitre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
    
    private String id_Cours;
    ServiceChapitre spChapitre = new ServiceChapitre();
    

    public void setId_Cours(String id_Cours) {
        this.id_Cours = id_Cours;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterNouveauContenu(MouseEvent event) throws IOException {
        
        Chapitre chap = new Chapitre(CapitreText.getText(),id_Cours);
        chap.setId(UUID.randomUUID().toString());
        
        spChapitre.ajouter(chap);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAjouterContenu.fxml"));
        Parent root = loader.load();
        ajouterConutenu.getScene().setRoot(root);  
        InterfaceAjouterContenuController Contenu = loader.getController();
        Contenu.setId_chapitre(chap.getId());
        Contenu.setId_cours(id_Cours);
    }
    
}
