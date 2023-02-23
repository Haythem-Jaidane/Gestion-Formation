/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.esprit.entities.*;
import com.esprit.services.*;
import java.io.IOException;
        
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    
    @FXML
    private Button ajouter;
    @FXML
    private Button consulter;
    
    
    
    ServiceCours spCours = new ServiceCours(); 
    ServiceProgres spProgres = new ServiceProgres();
    private Utilisateur Login;    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Login = new Utilisateur("gtt");
        
        List<Cours> lCour = spCours.afficher();
        
        List<AfficahageMainInterface> u = new ArrayList<>();
        
        for(Cours C:lCour){
            //tab_cour.getItems().add(C);
            Utilisateur Utli = spCours.getNomTuteurAvecId(C);
            Progres Prog = spProgres.getProgresUtlisateurParCours(C,Login);
            ProgressBar progBar = new ProgressBar();
            progBar.setProgress(Prog.getProgres()/100.0f);
            Button But;
            if(Prog.getProgres()==0){
                But = new Button("Commancer");
                But.setOnAction(this::welcomeCours);
            }
            else{
                But = new Button("Poursuivre");
                But.setOnAction(this::continueCours);
            }
            u.add(new AfficahageMainInterface(C.getTitre(),Utli.getNom(),C.getCategorie(),C.getDuree(),progBar,But));
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
    
    
    private void welcomeCours(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceWelcomeCours.fxml"));
            Parent root = loader.load();
            tab_cour.getScene().setRoot(root);
            
            InterfaceWelcomeCoursController Welcome = loader.getController();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void continueCours(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceLireCours.fxml"));
            Parent root = loader.load();
            tab_cour.getScene().setRoot(root);
            
            InterfaceLireCoursController Lire = loader.getController();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ajouterCoursT(MouseEvent event) throws IOException{ 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAjouterCours.fxml"));
        Parent root = loader.load();
        tab_cour.getScene().setRoot(root);  
        InterfaceAjouterCoursController ajouter = loader.getController();
 }
    
}
