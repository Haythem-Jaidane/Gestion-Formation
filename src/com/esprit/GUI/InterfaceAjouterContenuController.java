/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import com.esprit.entities.Contenu;
import com.esprit.entities.Cours;
import com.esprit.services.ServiceContenu;
import com.esprit.services.ServiceCours;
import java.util.UUID;

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
    @FXML
    private TextField duree;
    @FXML
    private ComboBox<String> type;
    
    File selectedFile;
    String id_cours;
    String id_chapitre;
    
    ServiceContenu spContenu = new ServiceContenu();
    ServiceCours spCours = new ServiceCours();

    public void setId_chapitre(String id_chapitre) {
        this.id_chapitre = id_chapitre;
    }

    public void setId_cours(String id_cours) {
        this.id_cours = id_cours;
    }
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> items = type.getItems();
        items.addAll("Texte","Video");
    }    

    @FXML
    private void fileChoseClick(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filechose.setText(filechose.getText()+" ... "+selectedFile);
        }
    }

    @FXML
    private void autreContenu(MouseEvent event) throws IOException {
        Contenu Con = new Contenu(type.getValue(),Integer.parseInt(duree.getText()),"",id_chapitre);
        Con.setId(UUID.randomUUID().toString());
        
        spContenu.ajouter(Con);
        spCours.incrementer(new Cours(id_cours),Con);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAjouterContenu.fxml"));
        Parent root = loader.load();
        filechose.getScene().setRoot(root);  
        InterfaceAjouterContenuController Contenu = loader.getController();
        Contenu.setId_chapitre(id_chapitre);
        Contenu.setId_cours(id_cours);
    }

    @FXML
    private void NouveauCapitre(MouseEvent event) throws IOException {
        Contenu Con = new Contenu(type.getValue(),Integer.parseInt(duree.getText()),"",id_chapitre);
        Con.setId(UUID.randomUUID().toString());
        
        spContenu.ajouter(Con);
        spCours.incrementer(new Cours(id_cours),Con);
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAjouterChapitre.fxml"));
        Parent root = loader.load();
        filechose.getScene().setRoot(root);  
        InterfaceAjouterChapitreController cahpitre = loader.getController();
        
        cahpitre.setId_Cours(id_cours);
    }

    @FXML
    private void terminerAjout(MouseEvent event) throws IOException {
        Contenu Con = new Contenu(type.getValue(),Integer.parseInt(duree.getText()),"",id_chapitre);
        Con.setId(UUID.randomUUID().toString());
        
        spContenu.ajouter(Con);
        spCours.incrementer(new Cours(id_cours),Con);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceCours.fxml"));
        Parent root = loader.load();
        filechose.getScene().setRoot(root);  
        InterfaceCoursController ajouter = loader.getController();
    }
    
}
