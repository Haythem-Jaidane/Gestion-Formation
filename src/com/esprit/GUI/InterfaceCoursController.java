/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.esprit.entities.*;
import com.esprit.services.*;
        
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceCoursController implements Initializable {

    @FXML
    private TableView<Cours> tab_cour;
    @FXML
    private TableColumn<Cours, String> id_titre;
    @FXML
    private TableColumn<Tuteur, String> id_tuteur;
    @FXML
    private TableColumn<Cours, String> id_categorie;
    @FXML
    private TableColumn<Cours, Integer> id_duree;
    @FXML
    private TableColumn<Progres, Integer> progre;
    
    ServiceCours spCours = new ServiceCours(); 

    List lCour = spCours.afficher();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id_titre.setCellValueFactory(new PropertyValueFactory<Cours,String>("titre"));
        id_categorie.setCellValueFactory(new PropertyValueFactory<Cours,String>("categorie"));
        id_duree.setCellValueFactory(new PropertyValueFactory<Cours,Integer>("duree"));
        tab_cour.setItems(FXCollections.observableList(lCour));
    }    
    
}
