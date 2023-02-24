/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.esprit.entities.AffichageConsulter;
import com.esprit.entities.Chapitre;
import com.esprit.entities.Contenu;
import com.esprit.entities.Cours;
import com.esprit.entities.Progres;
import com.esprit.services.ServiceChapitre;
import com.esprit.services.ServiceContenu;
import com.esprit.services.ServiceCours;
import com.esprit.services.ServiceProgres;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceConsulterCourController implements Initializable {

    @FXML
    private Button retourButton;
    @FXML
    private TableView<AffichageConsulter> tab_cours;
    @FXML
    private TableColumn<AffichageConsulter, String> Titre;
    @FXML
    private TableColumn<AffichageConsulter, String> Categorie;
    @FXML
    private TableColumn<AffichageConsulter, Button> supp;
    
    ServiceContenu spContenu = new ServiceContenu();
    ServiceChapitre spChapitre = new ServiceChapitre();
    ServiceCours spCours = new ServiceCours();
    ServiceProgres spProgres = new ServiceProgres();
    
    private String id_tuto;

    public void setId_tuto(String id_tuto) {
        this.id_tuto = id_tuto;
    }
    
    public void setTableView(){
        
        List<AffichageConsulter> k = new ArrayList<>();
        List<Cours> u = spCours.afficherParTuteur(id_tuto);
                
        Titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        supp.setCellValueFactory(new PropertyValueFactory<>("Supprimer"));
        
        for(Cours i:u){
            Button B = new Button("Supprimer");
            B.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            
                            public void handle(ActionEvent e) {
                                List<Chapitre> C = spChapitre.getChapterByCours(i.getId());
                                for(Chapitre j:C){
                                    List<Contenu> Con = spContenu.getContenuByChapitre(j.getId());
                                    for(Contenu ii:Con){
                                        spContenu.supprimer(ii);
                                        System.out.println(ii);
                                    }
                                                  
                                    System.out.println(j);
                                    spChapitre.supprimer(j); 
                                }
                                spProgres.supprimerParCours(new Progres(i.getId()));
                                spCours.supprimer(i);
                            }
                         });
            AffichageConsulter ajou = new AffichageConsulter(i.getTitre(),i.getCategorie(),B);
            k.add(ajou);
        }
        
        tab_cours.getItems().addAll(k);
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void RetournerP(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceCours.fxml"));
        Parent root = loader.load();
        retourButton.getScene().setRoot(root);  
        InterfaceCoursController ajouter = loader.getController();
    }
    
}
