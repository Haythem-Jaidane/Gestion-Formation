/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.esprit.entities.Chapitre;
import com.esprit.services.ServiceChapitre;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceLireCoursController implements Initializable {

    @FXML
    private VBox chapter_continer;
    private String id_cours;
    
    ServiceChapitre spChapitre = new ServiceChapitre();

    public void setId_cours(String id_cours) {
        this.id_cours = id_cours;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Chapitre> L = spChapitre.getChapterByCours(id_cours);
        for(Chapitre C:L){
            TitledPane T = new TitledPane();
            T.setText(C.getTitre());
            
            chapter_continer.getChildren().add(T);
        }
        
    }    
    
}
