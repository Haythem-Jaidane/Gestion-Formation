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
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
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
    
    @FXML
    private TableColumn<AfficahageMainInterface, Integer> startid;
    
    
    
    ServiceCours spCours = new ServiceCours(); 
    ServiceProgres spProgres = new ServiceProgres();
 

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Utilisateur Login = new Utilisateur("gtt");
        
        List<Cours> lCour = spCours.afficher();
        
        List<AfficahageMainInterface> u = new ArrayList<>();
        
        for(Cours C:lCour){
            //tab_cour.getItems().add(C);
            Utilisateur Utli = spCours.getNomTuteurAvecId(C);
            Progres Prog = spProgres.getProgresUtlisateurParCours(C,Login);
            ProgressBar progBar = new ProgressBar(Prog.getProgres());
            u.add(new AfficahageMainInterface(C.getTitre(),Utli.getNom(),C.getCategorie(),C.getDuree(),progBar,new Button("commancer")));
        }
        
        System.out.println(u);

        
        
        id_titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        id_tuteur.setCellValueFactory(new PropertyValueFactory<>("Tuteur_nom"));
        id_categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        id_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        progre.setCellValueFactory(new PropertyValueFactory<>("progress"));
        startid.setCellValueFactory(new PropertyValueFactory<>("B"));
        
        tab_cour.getItems().addAll(u);
    }    
    
}
