/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.esprit.entities.AffichageConsulter;
import com.esprit.entities.AffichageConsulterContenu;
import com.esprit.entities.Chapitre;
import com.esprit.entities.Contenu;
import com.esprit.services.ServiceContenu;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceConsulterContenuController implements Initializable {

    @FXML
    private TableView<AffichageConsulterContenu> chapitreContainer;
    @FXML
    private TableColumn<AffichageConsulterContenu, String> titre;
    @FXML
    private TableColumn<AffichageConsulterContenu, String> Type;
    @FXML
    private TableColumn<AffichageConsulterContenu, Integer> Duree;
    @FXML
    private TableColumn<AffichageConsulterContenu, Button> supp;
    @FXML
    private Button retourButton;
    @FXML
    private Button ajouterContenu;
    @FXML
    private Button RetourCours;
    
    private String id_tuto;
    private String id_chapitre;
    
    private ServiceContenu spContenu = new ServiceContenu();
    private List<Contenu> u;
    

    public void setId_tuto(String id_tuto) {
        this.id_tuto = id_tuto;
    }

    public void setId_chapitre(String id_chapitre) {
        this.id_chapitre = id_chapitre;
    }
    
    
    public void setTableView(){
        
        List<AffichageConsulterContenu> k = new ArrayList<>();
        
        
        
        u = spContenu.getContenuByChapitre(id_chapitre);
        
                
        titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        titre.setCellFactory(TextFieldTableCell.forTableColumn());
        
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
       
        Duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        

        
        
        supp.setCellValueFactory(new PropertyValueFactory<>("Supp"));

        

        
        for(Contenu i:u){
            Button B = new Button("Supprimer");
            
            B.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            
                            public void handle(ActionEvent e) {
                                spContenu.supprimer(i);
                            }
                         });
         
           
            AffichageConsulterContenu ajou = new AffichageConsulterContenu(i.getTitre(),i.getType(),i.getDuree(),B);
            k.add(ajou);
        }
        
        

        
        chapitreContainer.getItems().addAll(k);
        chapitreContainer.setEditable(true);
        
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RetournerP(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceCours.fxml"));
        Parent root = loader.load();
        retourButton.getScene().setRoot(root);  
        InterfaceCoursController ajouter = loader.getController();
    }

    @FXML
    private void ajouterUnContenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceAjouterContenu.fxml"));
        Parent root = loader.load();
        chapitreContainer.getScene().setRoot(root);  
        InterfaceAjouterChapitreController Chapitre = loader.getController();
        Chapitre.setId_Cours(id_chapitre);
        
    }

    @FXML
    private void RetournerChapitre(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceConsulterChapitre.fxml"));
        Parent root = loader.load();
        chapitreContainer.getScene().setRoot(root);  
        InterfaceConsulterChapitreController ajouter = loader.getController();
        ajouter.setId_tuto(id_tuto);
        ajouter.setTableView();
    }
    
}
