/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.esprit.entities.*;
import com.esprit.services.*;
        
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
    private TableView<AfficahageMainInterface> tab_cour;
    @FXML
    private TableColumn<AfficahageMainInterface, String> id_titre;
    @FXML
    private TableColumn<AfficahageMainInterface, String> id_tuteur;
    @FXML
    private TableColumn<AfficahageMainInterface, String> id_categorie;
    @FXML
    private TableColumn<AfficahageMainInterface, Integer> id_duree;
    @FXML
    private TableColumn<AfficahageMainInterface, Integer> progre;
    
    
    
    ServiceCours spCours = new ServiceCours(); 

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Cours> lCour = spCours.afficher();
        
        List<AfficahageMainInterface> u = new ArrayList<>();
        
        for(Cours C:lCour){
            //tab_cour.getItems().add(C);
            Utilisateur Utli = spCours.getNomTuteurAvecId(C);
            u.add(new AfficahageMainInterface(C.getTitre(),Utli.getNom(),C.getCategorie(),C.getDuree()));
        }
        
        System.out.println(u);

        
        
        id_titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        id_tuteur.setCellValueFactory(new PropertyValueFactory<>("Tuteur_nom"));
        id_categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        id_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        
        ObservableList<AfficahageMainInterface> data = FXCollections.observableArrayList(u);
        tab_cour.getItems().addAll(u);
    }    
    
}
