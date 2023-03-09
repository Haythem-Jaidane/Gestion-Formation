/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.dropbox.core.DbxException;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

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
    
    @FXML
    private TextField recharcheText;
    
    
    ServiceCours spCours = new ServiceCours(); 
    ServiceProgres spProgres = new ServiceProgres();
    private Utilisateur Login;    

    public TextField getRecharcheText() {
        return recharcheText;
    }

    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    

        
        Login = new Utilisateur("gtt");
        
        List<Cours> lCour = spCours.afficher();
        
        List<AfficahageMainInterface> u = affichageDeCoursDUneList(lCour);
        
        System.out.println(u);

        
        
        id_titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        id_tuteur.setCellValueFactory(new PropertyValueFactory<>("Tuteur_nom"));
        id_categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        id_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        progre.setCellValueFactory(new PropertyValueFactory<>("progress"));
        startid.setCellValueFactory(new PropertyValueFactory<>("B"));
        
        tab_cour.getItems().addAll(u);
    }
    
    private List<AfficahageMainInterface> affichageDeCoursDUneList(List<Cours> lCour){
        List<AfficahageMainInterface> u = new ArrayList<>();
        
        for(Cours C:lCour){
            //tab_cour.getItems().add(C);
            Utilisateur Utli = spCours.getNomTuteurAvecId(C);
            Progres Prog = spProgres.getProgresUtlisateurParCours(C,Login);
            ProgressBar progBar = new ProgressBar();
            progBar.setProgress(Prog.getProgres()/100.0f);
            Button But;
            if(!spProgres.checkProgresUtlisateurParCours(C,Login)){
                But = new Button("Commancer");
                welcomeCours w = new welcomeCours();
                w.setId_utilisateur(Login.getId_utilisateur());
                w.setC(C);
                w.setCn(this);
                But.setOnAction(w);
                
            }
            else{
                But = new Button("Poursuivre");
                ContinuCours conti = new ContinuCours();
                conti.setId_utilisateur(Login.getId_utilisateur());
                conti.setC(C);
                conti.setCn(this);
                But.setOnAction(conti);
            }
            u.add(new AfficahageMainInterface(C.getTitre(),Utli.getNom(),C.getCategorie(),C.getDuree(),progBar,But));
        }
        
        return u;
    }
   

    @FXML
    private void ajouterCoursT(MouseEvent event) throws IOException{ 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAjouterCours.fxml"));
        Parent root = loader.load();
        tab_cour.getScene().setRoot(root);  
        //InterfaceAjouterCoursController ajouter = new InterfaceAjouterCoursController(Login.getId_utilisateur()) ;
        InterfaceAjouterCoursController ajouter = loader.getController();
        ajouter.setId_Login(Login.getId_utilisateur());
    }


    @FXML
    private void consulterMesCours(MouseEvent event) throws IOException, DbxException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceConsulterCour.fxml"));
        Parent root = loader.load();
        tab_cour.getScene().setRoot(root);  
        InterfaceConsulterCourController ajouter = loader.getController();
        ajouter.setId_tuto(Login.getId_utilisateur());
        ajouter.setTableView();
    }

    @FXML
    private void Recherche(KeyEvent event) {
        List<Cours> T=spCours.Rechercher(new Cours("5",recharcheText.getText()));
        
        List<AfficahageMainInterface> u = affichageDeCoursDUneList(T);
        
        tab_cour.getItems().clear();
        tab_cour.getItems().addAll(u);
    }
    
}

