/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.dropbox.core.DbxException;
import com.esprit.entities.AffichageConsulter;
import com.esprit.entities.Chapitre;
import com.esprit.entities.Contenu;
import com.esprit.entities.Cours;
import com.esprit.entities.Progres;
import com.esprit.services.ServiceAPIDropbox;
import com.esprit.services.ServiceChapitre;
import com.esprit.services.ServiceContenu;
import com.esprit.services.ServiceCours;
import com.esprit.services.ServiceProgres;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
    @FXML
    private TableColumn<AffichageConsulter, Button> chap;
    
    ServiceContenu spContenu = new ServiceContenu();
    ServiceChapitre spChapitre = new ServiceChapitre();
    ServiceCours spCours = new ServiceCours();
    ServiceProgres spProgres = new ServiceProgres();
    ServiceAPIDropbox dropbox ;
    
    private String id_tuto;
    private List<Cours> u;
    

    public void setId_tuto(String id_tuto) {
        this.id_tuto = id_tuto;
    }
    
    public void setTableView() throws DbxException{
        
        List<AffichageConsulter> k = new ArrayList<>();
        
        dropbox = new ServiceAPIDropbox(); 
        
        u = spCours.afficherParTuteur(id_tuto);
        
                
        Titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        Titre.setCellFactory(TextFieldTableCell.forTableColumn());
        
        Categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        supp.setCellValueFactory(new PropertyValueFactory<>("Supprimer"));
        chap.setCellValueFactory(new PropertyValueFactory<>("voir_chapitre"));
        

        
        for(Cours i:u){
            Button B = new Button("Supprimer");
            Button Chap = new Button("Voir les Chapitre");
            
            B.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            
                            public void handle(ActionEvent e) {
                                
                                List<Chapitre> C = spChapitre.getChapterByCours(i.getId());
                                
                                Dialog<String> dialog = new Dialog<String>();
                                dialog.setTitle("Cours Supprimer");
                                ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                                dialog.setContentText("le Cours "+ i.getTitre()+" à été supprimer");
                                dialog.getDialogPane().getButtonTypes().add(type);
                                
                                for(Chapitre j:C){
                                    List<Contenu> Con = spContenu.getContenuByChapitre(j.getId());
                                    for(Contenu ii:Con){
                                        spContenu.supprimer(ii);
                                        System.out.println(ii);
                                    }
                                                  
                                    System.out.println(j);
                                    spChapitre.supprimer(j); 
                                }
                                try {
                                    dropbox.supprimerFolder(i.getId());
                                } catch (DbxException ex) {
                                    Logger.getLogger(InterfaceConsulterCourController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                spProgres.supprimerParCours(new Progres(i.getId()));
                                spCours.supprimer(i);
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceConsulterCour.fxml"));
                                Parent root;
                                try {
                                    root = loader.load();
                                    retourButton.getScene().setRoot(root);  
                                    InterfaceConsulterCourController consultarCour = loader.getController();
                                    consultarCour.setId_tuto(id_tuto);
                                    consultarCour.setTableView();
                                    dialog.showAndWait();
                                } catch (IOException ex) {
                                    Logger.getLogger(InterfaceConsulterCourController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (DbxException ex) {
                                    Logger.getLogger(InterfaceConsulterCourController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                         });
            
            Chap.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            
                            public void handle(ActionEvent e) {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceConsulterChapitre.fxml"));
                                Parent root;
                                try {
                                    root = loader.load();
                                    retourButton.getScene().setRoot(root);  
                                    InterfaceConsulterChapitreController consultarChapitre = loader.getController();
                                    consultarChapitre.setIdCours(i.getId());
                                    consultarChapitre.setId_tuto(id_tuto);
                                    try {
                                        consultarChapitre.setTableView();
                                    } catch (DbxException ex) {
                                        Logger.getLogger(InterfaceConsulterCourController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(InterfaceConsulterCourController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                         });
           
            AffichageConsulter ajou = new AffichageConsulter(i.getTitre(),i.getCategorie(),B,Chap,i.getId());
            k.add(ajou);
        }
        
        

        
        tab_cours.getItems().addAll(k);
        tab_cours.setEditable(true);
        
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        //Iterator<Cours> itC = u.iterator();
       //tab_cours.setEditable(true);
        
        
        
        
    }    

    @FXML
    private void RetournerP(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceCours.fxml"));
        Parent root = loader.load();
        retourButton.getScene().setRoot(root);  
        InterfaceCoursController ajouter = loader.getController();
    }

    @FXML
    private void modifierTitre(CellEditEvent<AffichageConsulter, String> event) {
        
        Cours C = new Cours(event.getTableView().getItems().get(event.getTablePosition().getRow()).getId_cours(),
                            event.getNewValue(),
                            id_tuto,
                            Categorie.getText());
                
        spCours.modifier(C);
        
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setTitre(event.getNewValue());
        
    }


   

    
}
